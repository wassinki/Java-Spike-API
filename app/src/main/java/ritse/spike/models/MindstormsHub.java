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
			spikeCommandExecutor.executeVoid("\003");
			spikeCommandExecutor.executeVoid("from spike import PrimeHub, LightMatrix, Button, StatusLight, MotionSensor, Speaker, ColorSensor, App, DistanceSensor, Motor");
			spikeCommandExecutor.executeVoid("import hub");
			spikeCommandExecutor.executeVoid("hub = PrimeHub()");
			buttonMap.put(ButtonEnum.LEFT, new Button(ButtonEnum.LEFT, spikeCommandExecutor));
			buttonMap.put(ButtonEnum.RIGHT, new Button(ButtonEnum.RIGHT, spikeCommandExecutor));
			buttonMap.put(ButtonEnum.CENTER, new Button(ButtonEnum.CENTER, spikeCommandExecutor));
			initializeEvalFunction();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void displayText(String text) {
		try {
			spikeCommandExecutor.executeVoid(String.format("hub.display.show(\"%s\")", text));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void createMotor(MotorEnum motorEnum) throws IOException {
		spikeCommandExecutor.executeVoid(String.format("motor%s = Motor('" + motorEnum.asString + "')", motorEnum.asString));
		handleAddMotor(motorEnum);
	}

	public Motor getMotorByMotorEnum(MotorEnum motorEnum) {
		return motorMap.get(motorEnum);
	}

	public void createDistanceSensor(String portChar) throws IOException {
		distanceSensor = new DistanceSensor(spikeCommandExecutor);
		spikeCommandExecutor.executeVoid(String.format("distance_sensor = DistanceSensor('%s')", portChar));
	}

	public DistanceSensor getDistanceSensor() {
		return distanceSensor;
	}

	public void createColorSensor(String portChar) throws IOException {
		colorSensor = new ColorSensor(spikeCommandExecutor);
		spikeCommandExecutor.executeVoid(String.format("color_sensor = ColorSensor('%s')", portChar));
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
		spikeCommandExecutor.executeVoid("def evaluator(msgType, counter, fn):\n " +
				"return \"!{}:{}:{}%\".format(msgType, counter, eval(fn))\r\n");
	}

//	private void initializeButtonPressedFunction() throws IOException, InterruptedException {
//		spikeCommandExecutor.executeVoid("def buttonPressed(msgType, counter, fn):\n " +
//				"return \"!{}:{}:{}%\".format(msgType, counter, eval(fn))");
////		spikeCommandExecutor.executeVoid("def buttonPressed(msgType, counter, fn):\n" +
////				"    exit = True\n" +
////				"    while exit:\n" +
////				"        result = eval(fn)\n" +
////				"        if result:\n" +
////				"            exit = False\n" +
////				"            return \"!{}:{}:{}%\".format(msgType, counter, \"984000001112578\")\r\n");
//	}
}




