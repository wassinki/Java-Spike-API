package ritse.spike;

import java.io.IOException;

import ritse.spike.models.ButtonEnum;
import ritse.spike.models.MindstormsHub;
import ritse.spike.models.MotorEnum;

public class App {
	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("Starting");
		MindstormsHub hub = new MindstormsHub("COM5");
		hub.initialize();


		/** To test distance sensor */
//		hub.createDistanceSensor("C");
//		hub.getDistanceSensor().lightUpAll();
//		hub.getDistanceSensor().getDistanceCm();

		/** To test motor */
//		hub.createMotor(MotorEnum.A);
//		hub.getMotorByMotorEnum(MotorEnum.A).getDefaultSpeed();

		hub.getButtonByEnum(ButtonEnum.LEFT).wasPressed();
//		hub.getMotorByMotorEnum(MotorEnum.A).getPosition();
//		hub.getMotorByMotorEnum(MotorEnum.A).getSpeed();


		/** To test color sensor */
//		hub.createColorSensor("C");
//		hub.getColorSensor().getColor();
//		hub.getColorSensor().getReflectedLight();
//		hub.getColorSensor().getAmbientLight();
//		System.out.println("done");
	}
}
