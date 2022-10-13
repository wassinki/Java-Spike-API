package ritse.spike.models;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Strings;

public class SpikeCommandExecutor {
	// RC:<message number>:result
	private static final Pattern RESULT_PATTERN = Pattern.compile("^RC:(\\d+):(.*)");
	private SerialPortImpl serialPort;
	private final AtomicReference<String> result = new AtomicReference<>();
	private CountDownLatch latch = new CountDownLatch(1);

	//private Map<Integer, MessageBox> handerlMap = new ConcurrentHashMap<>();

	private int messageNumber = 0;

	public SpikeCommandExecutor(String comport) {
		this.serialPort = new SerialPortImpl(comport, 115200, bytes -> {
			// do patern matchin


			String message = new String(bytes);
			String noExclaimationMark = message.substring(message.lastIndexOf("!") +1);
			String answer = noExclaimationMark.substring(0, noExclaimationMark.length() -1);
//			final Matcher matcher = RESULT_PATTERN.matcher(answer);
//			if (matcher.matches()) {
//
//					final int counter = Integer.parseInt(matcher.group(1));
//					final String result = matcher.group(2);
//					final Consumer<String> handler = handerlMap.remove(counter);
//					final Exchanger<String> exchanger = handler.remove(counter);
//					// null check
//					exchanger.exchange(result);
//					//messagebox.put(result);
//
//			}

			result.set(answer);
			latch.countDown();
		});

		try {
			serialPort.open();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String execute(String command) throws IOException, InterruptedException {
		latch = new CountDownLatch(1);
		final int messageCounter = messageNumber++;
		try {

			// maak message box aan voor message counter
			//put message box in handler map
			// MessageBox m = new MessageBox();
			// handlerMap.put(messageCounter, m);

//			final Exchanger<String> exchanger = new Exchanger<>();
			// put in map

			// mijn message id = x
			serialPort.getOutputStream().get().write((String.format("evaluator(\"%s\", %d, \"" + command + "\")\r\n", "RC", messageCounter)).getBytes(StandardCharsets.UTF_8));
//			exchanger.exchange("", 1, TimeUnit.SECONDS);


			latch.await(1, TimeUnit.SECONDS);
			// final String result = m.waitForMessage(timeout);


			if (result.get() != null) {
				// doe je ding
				System.out.println("Result: " + result.get());
				final Matcher matcher = RESULT_PATTERN.matcher(result.get());
			} else {
				System.out.println("RESULT IS NULL");
			}
		} finally {
//			handlermap.remove(messageCounter);
		}
		return result.get();

	}

	public void executeVoid (String command) throws IOException {
		serialPort.getOutputStream().get().write((command + "\r\n").getBytes(StandardCharsets.UTF_8));
	}

	public String getResult() {
		return result.get();
	}

	public int getMessageNumber() {
		return messageNumber;
	}
}
