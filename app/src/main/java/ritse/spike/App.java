package ritse.spike;

import java.io.IOException;

import ritse.spike.models.MindstormsHub;

public class App {
	public static void main(String[] args) throws IOException, InterruptedException {
		MindstormsHub hub = new MindstormsHub("COM5");
		hub.initialize();
		hub.createMotor("E");
		hub.motor.get_position();

	}


//        finally {
//            port.close();
//        }

}

//}
