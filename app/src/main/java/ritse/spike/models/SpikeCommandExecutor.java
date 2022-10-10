package ritse.spike.models;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SpikeCommandExecutor {
	private SerialPortImpl serialPort;

	public SpikeCommandExecutor(String comport) {
		this.serialPort = new SerialPortImpl(comport, 115200, bytes -> System.out.println(" * " + new String(bytes)) );

		try {
			serialPort.open();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void execute(String command) throws IOException {
		serialPort.getOutputStream().get().write((command + "\r\n").getBytes(StandardCharsets.UTF_8));
	}

}
