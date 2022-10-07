package ritse.spike.models;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MindstormsHub {

	private static SerialPortImpl serialPort;
	public Motor motor;

	public MindstormsHub(String port) {
		this.serialPort = new SerialPortImpl(port, 115200, bytes -> System.out.println(" * " + new String(bytes)) );
		try {
			serialPort.open();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		System.out.println(serialPort.getPortName());
	}

	public void initialize() {
		try {
			writeString("\003");
			writeString("print(\"Hello Hub\")");
			writeString("from spike import PrimeHub, LightMatrix, Button, StatusLight, ForceSensor, MotionSensor, Speaker, ColorSensor, App, DistanceSensor, Motor, MotorPair");
			writeString("import hub");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}


	public void displayText(String text) {
		try {
			writeString(String.format("hub.display.show(\"%s\")", text));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void createMotor (String motorChar) {
		motor = new Motor(motorChar);
		motor.createMotor(motorChar);
	}

	public static void writeString(final String str) throws IOException {
		serialPort.getOutputStream().get().write((str + "\r\n").getBytes(StandardCharsets.UTF_8));
	}

//	public static void read() throws IOException {
//		System.out.println(serialPort.getInputStream().get().readAllBytes());
//	}
}




