package ritse.spike.models;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MindstormsHub {


	private SpikeCommandExecutor spikeCommandExecutor;
	private DistanceSensor distanceSensor;
	private ColorSensor colorSensor;

	private Map<MotorEnum, Motor> motorMap;
	private Map<ButtonEnum, Button> buttonMap;

	public MindstormsHub(String port) {
		this.spikeCommandExecutor = new SpikeCommandExecutor(port);
		motorMap = new HashMap<>();
		buttonMap = new HashMap<>();
	}

	public void initialize() throws InterruptedException {
		try {
			System.out.println("initializing");
			spikeCommandExecutor.execute("\003");
			spikeCommandExecutor.execute("from spike import PrimeHub, LightMatrix, Button, StatusLight, MotionSensor, Speaker, ColorSensor, App, DistanceSensor, Motor");
			spikeCommandExecutor.execute("import hub");
			spikeCommandExecutor.execute("hub = PrimeHub()");
			buttonMap.put(ButtonEnum.LEFT, new Button(ButtonEnum.LEFT, spikeCommandExecutor));
			buttonMap.put(ButtonEnum.RIGHT, new Button(ButtonEnum.RIGHT, spikeCommandExecutor));
			buttonMap.put(ButtonEnum.CENTER, new Button(ButtonEnum.CENTER, spikeCommandExecutor));
//			initializeMethodTest();
			initializeEvalFunction();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void displayText(String text) {
		try {
			spikeCommandExecutor.execute(String.format("hub.display.show(\"%s\")", text));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void createMotor(MotorEnum motorEnum) throws IOException, InterruptedException {
		System.out.println("Creating motor");
		spikeCommandExecutor.execute(String.format("motor%s = Motor('" + motorEnum.asString + "')", motorEnum.asString));
		handleAddMotor(motorEnum);
	}

	public Motor getMotorByMotorEnum(MotorEnum motorEnum) {
		return motorMap.get(motorEnum);
	}

	public void createDistanceSensor(String portChar) throws IOException, InterruptedException {
		distanceSensor = new DistanceSensor(spikeCommandExecutor);
		spikeCommandExecutor.execute(String.format("distance_sensor = DistanceSensor('%s')", portChar));
	}

	public DistanceSensor getDistanceSensor() {
		return distanceSensor;
	}

	public void createColorSensor(String portChar) throws IOException, InterruptedException {
		colorSensor = new ColorSensor(spikeCommandExecutor);
		spikeCommandExecutor.execute(String.format("distance_sensor = DistanceSensor('%s')", portChar));
	}

	public ColorSensor getColorSensor() {
		return colorSensor;
	}


	public Button getButtonByEnum(ButtonEnum buttonEnum) {
		return buttonMap.get(buttonEnum);
	}

	private void handleAddMotor(MotorEnum motorEnum) {
		switch (motorEnum) {
			case A:
				motorMap.put(MotorEnum.A, new Motor(MotorEnum.A, spikeCommandExecutor));
				break;
			case B:
				motorMap.put(MotorEnum.B, new Motor(MotorEnum.B, spikeCommandExecutor));
				break;
			case C:
				motorMap.put(MotorEnum.C, new Motor(MotorEnum.C, spikeCommandExecutor));
				break;
			case D:
				motorMap.put(MotorEnum.D, new Motor(MotorEnum.D, spikeCommandExecutor));
				break;
			case E:
				motorMap.put(MotorEnum.E, new Motor(MotorEnum.E, spikeCommandExecutor));
				break;
			case F:
				motorMap.put(MotorEnum.F, new Motor(MotorEnum.F, spikeCommandExecutor));
				break;
		}
	}

	private void initializeEvalFunction() throws IOException, InterruptedException {
		spikeCommandExecutor.execute("def evaluator(msgType, counter, fn):\n " +
				"return \"{}:{}:{}%\".format(msgType, counter, eval(fn))\r\n");
	}

	public void testMethod() throws IOException, InterruptedException {
		spikeCommandExecutor.execute("motorA = Motor('A')\n");
		spikeCommandExecutor.execute("evaluator(\"RC\", 62, \"motorA.get_position()\n\")\r\n");
		String result = spikeCommandExecutor.getResult();
		System.out.println("This is the result :" + result);
	}
}




