package ritse.spike;

import java.io.IOException;

import ritse.spike.models.MindstormsHub;
import ritse.spike.models.MotorEnum;

public class App {
	public static void main(String[] args) throws IOException {
		System.out.println("Starting");
		MindstormsHub hub = new MindstormsHub("COM5");
		hub.initialize();
		hub.createMotor(MotorEnum.E);
		hub.getMotorByMotorEnum(MotorEnum.E).runForSeconds(2);


		hub.createMotor(MotorEnum.A);
		hub.getMotorByMotorEnum(MotorEnum.A).runForSeconds(2);
	}
}
