package ritse.spike;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpikeCommandExecutorImpl implements SpikeCommandExecutor {
	// <type>:<message number>:result
	private static final Pattern RESULT_PATTERN = Pattern.compile("^[a-zA-Z]*:(\\d+):(.*)");
	private SerialPortImpl serialPort;
	private String result = "";
	private Map<Integer, Exchanger<String>> handlerMap = new ConcurrentHashMap<>();

	private int messageNumber = 0;

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
		String message = new String(bytes);
		String noExclaimationMark = message.substring(message.lastIndexOf("!") + 1);
		String answer = noExclaimationMark.substring(0, noExclaimationMark.length() - 1);
		System.out.println("result from command: " + answer);
		final Matcher matcher = RESULT_PATTERN.matcher(answer);
		if (matcher.matches()) {

			final int counter = Integer.parseInt(matcher.group(1));
			final String res = matcher.group(2);
			final Exchanger<String> exchanger = handlerMap.remove(counter);

			try {
				exchanger.exchange(res);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public String execute(String command) throws IOException, InterruptedException {

		final int messageCounter = messageNumber++;
		try {

			final Exchanger<String> exchanger = new Exchanger<>();
			// put in map
			handlerMap.put(messageCounter, exchanger);


			serialPort.getOutputStream().get().write((String.format("evaluator(\"%s\", %d, \"" + command + "\")\r\n", "RC", messageCounter)).getBytes(StandardCharsets.UTF_8));
			try {
				result = exchanger.exchange("", 3, TimeUnit.SECONDS);
			} catch (TimeoutException e) {
				System.out.println(e.getCause() + " " + e.getMessage());
			}

			if (result != null) {
				System.out.println("Result: " + result);
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
}
