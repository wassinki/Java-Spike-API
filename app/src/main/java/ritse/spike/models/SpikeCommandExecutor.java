package ritse.spike.models;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SpikeCommandExecutor {
	private SerialPortImpl serialPort;
	private final AtomicReference<String> result = new AtomicReference<>();
	private CountDownLatch latch = new CountDownLatch(1);

	public SpikeCommandExecutor(String comport) {
		this.serialPort = new SerialPortImpl(comport, 115200, bytes -> {
			System.out.println("Bytes received: " + new String(bytes) + " end of bytes received");
			final String r = new String(bytes);
			// if message id in r = x
			result.set(r);
			latch.countDown();
		});

		try {
			serialPort.open();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void execute(String command) throws IOException, InterruptedException {
		latch = new CountDownLatch(1);
		// mijn message id = x
		serialPort.getOutputStream().get().write((command + "\r\n").getBytes(StandardCharsets.UTF_8));
		latch.await(1, TimeUnit.SECONDS);

		final String message = result.get();
		if (result.get() != null){
			// doe je ding
			System.out.println("Result: " + result.get());
		} else {
			System.out.println("RESULT IS NULL");
		}
	}

	public String getResult() {
		return result.get();
	}
}
