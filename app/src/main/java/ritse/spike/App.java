package ritse.spike;

import java.io.IOException;

public class App {
	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("Starting");
		MindstormsHub hub = new MindstormsHub("COM5");
		hub.initialize();

		hub.getButtonByEnum(ButtonEnum.LEFT).executeWhenPressed("980000053281");

		/** To test distance sensor */
		hub.createDistanceSensor("E");
		hub.getDistanceSensor().lightUpAll();
		hub.getDistanceSensor().getDistanceCm();

		/** To test motor */
		hub.createMotor(MotorEnum.A);
		hub.getMotorByMotorEnum(MotorEnum.A).getDefaultSpeed();
		hub.getMotorByMotorEnum(MotorEnum.A).getPosition();
		hub.getMotorByMotorEnum(MotorEnum.A).getSpeed();


		/** To test color sensor */
		hub.createColorSensor("C");
		hub.getColorSensor().getColor();
		hub.getColorSensor().getReflectedLight();
		hub.getColorSensor().getAmbientLight();
		System.out.println("done");
	}
}
