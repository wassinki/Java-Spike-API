package ritse.spike;

import java.io.IOException;

import ritse.spike.models.MindstormsHub;
import ritse.spike.models.MotorEnum;

public class App {
	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("Starting");
		MindstormsHub hub = new MindstormsHub("COM5");
		hub.initialize();

		hub.createColorSensor("C");
		hub.getColorSensor().lightUpAll(100);
		hub.getColorSensor().getReflectedLight();
		hub.getColorSensor().getAmbientLight();

//		hub.createMotor(MotorEnum.E);
////		hub.getMotorByMotorEnum(MotorEnum.E).setDefaultSpeed(5);
//		hub.getMotorByMotorEnum(MotorEnum.E).getPosition();
////		hub.getMotorByMotorEnum(MotorEnum.E).start();
//		hub.getMotorByMotorEnum(MotorEnum.E).start();
//		Thread.sleep(150);
//		hub.getMotorByMotorEnum(MotorEnum.E).stop();
//
//
//		hub.createMotor(MotorEnum.A);
//		hub.getMotorByMotorEnum(MotorEnum.A).runForSeconds(2);

		System.out.println("done");
	}
}
