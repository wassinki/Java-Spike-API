package ritse.spike;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpikeCommandExecutorImpl implements SpikeCommandExecutor {
	// <type>:<message number>:result
	private static final Pattern RESULT_PATTERN = Pattern.compile("^RC:(\\d+):(.*)");

	private static final Pattern CALLBACK_PATTERN = Pattern.compile(".*CB:(\\d+):(.*)");
	private SerialPortImpl serialPort;
	private String result = "";
	private Map<Integer, Exchanger<String>> handlerMap = new ConcurrentHashMap<>();

	private int messageNumber = 0;

	private Map<Integer, Consumer<String>> callbackHandler = new ConcurrentHashMap<>();

	public SpikeCommandExecutorImpl(String comport) {
		this.serialPort = new SerialPortImpl(comport, 115200, this::extract);
		try {
			serialPort.open();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void extract(byte[] bytes) {
		try {
			String message = new String(bytes);
			String noExclaimationMark = message.substring(message.lastIndexOf("!") + 1);
			String answer = noExclaimationMark.substring(0, noExclaimationMark.length() - 1);
			final Matcher resultMatcher = RESULT_PATTERN.matcher(answer);
			if (resultMatcher.matches()) {

				final int counter = Integer.parseInt(resultMatcher.group(1));
				final String res = resultMatcher.group(2);
				final Exchanger<String> exchanger = handlerMap.remove(counter);
				try {
					exchanger.exchange(res);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
			final String answerToMatch = answer.trim();
			final Matcher callbackMatcher = CALLBACK_PATTERN.matcher(answerToMatch);
			if (callbackMatcher.matches()) {
				final int counter = Integer.parseInt(callbackMatcher.group(1));
				final String res = callbackMatcher.group(2);
				callbackHandler.getOrDefault(counter, (s) -> {}).accept(res);
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	@Override
	public String execute(String command) throws IOException, InterruptedException {
		final int messageCounter = messageNumber++;
		try {

			final Exchanger<String> exchanger = new Exchanger<>();
			// put in map
			handlerMap.put(messageCounter, exchanger);


			final String messageToSend = String.format("evaluator(\"%s\", %d, \"" + command + "\")\r\n", "RC", messageCounter);
			serialPort.getOutputStream().get().write(messageToSend.getBytes(StandardCharsets.UTF_8));
			try {
				result = exchanger.exchange("", 3, TimeUnit.SECONDS);
			} catch (TimeoutException e) {
				e.printStackTrace();
			}

			if (result != null) {
				final Matcher matcher = RESULT_PATTERN.matcher(result);
			} else {
				System.out.println("RESULT IS NULL");
			}
		} finally {
			handlerMap.remove(messageCounter);
		}
		return result;

	}

	@Override
	public void executeVoid(String command) throws IOException {
		serialPort.getOutputStream().get().write((command + "\r\n").getBytes(StandardCharsets.UTF_8));
	}

	public int getMessageNumber() {
		return messageNumber;
	}

	@Override
	public void addCallback(final String method, final Consumer<String> callback) throws IOException, InterruptedException {
		final int nextId = callbackHandler.keySet().stream()
				.mapToInt(Integer::intValue)
				.max()
				.orElse(0) + 1;
		System.out.println(nextId);
		callbackHandler.put(nextId, callback);
		final String command = String.format("%s(lambda x: print(\"CB:%d:\" + str(x) + \"%s\"))\r\n", method, nextId, '%');

		executeVoid(command);
	}
}
