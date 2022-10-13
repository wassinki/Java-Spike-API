package ritse.spike;

import java.io.IOException;

import ritse.spike.models.MindstormsHub;
import ritse.spike.models.MotorEnum;

public class App {
	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("Starting");
		MindstormsHub hub = new MindstormsHub("COM5");
		hub.initialize();


//		hub.createDistanceSensor("C");
//		hub.getDistanceSensor().lightUpAll();
//		hub.getDistanceSensor().getDistanceCm();

		hub.createMotor(MotorEnum.A);
		hub.getMotorByMotorEnum(MotorEnum.A).getDefaultSpeed();
		hub.getMotorByMotorEnum(MotorEnum.A).getPosition();
		hub.getMotorByMotorEnum(MotorEnum.A).getSpeed();


//		hub.createColorSensor("C");
//		hub.getColorSensor().getColor();
//		hub.getColorSensor().getReflectedLight();
//		hub.getColorSensor().getAmbientLight();
//		System.out.println("done");
	}
}
