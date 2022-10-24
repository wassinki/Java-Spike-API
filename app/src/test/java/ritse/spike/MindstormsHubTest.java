package ritse.spike;

import static org.easymock.EasyMock.expect;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ritse.spike.MotorEnum.A;
import static ritse.spike.MotorEnum.C;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.easymock.EasyMock;
import org.easymock.EasyMockExtension;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(EasyMockExtension.class)
public class MindstormsHubTest extends EasyMockSupport {

	@Mock
	private SpikeCommandExecutor spikeCommandExecutor;

	private MindstormsHub hub;

	private Map<MotorEnum, Motor> motorMap;
	private Map<ButtonEnum, Button> buttonMap;

	private final String portChar = "C";

	private final String text = "displayText";

	@BeforeEach
	public void setUp() {
		replayAll();
		hub = new MindstormsHub(spikeCommandExecutor);
		motorMap = new HashMap<>();
		buttonMap = new HashMap<>();
		verifyAll();
		resetAll();
	}

	@Test
	public void createMotorTest() throws IOException {
		// arrange
		spikeCommandExecutor.executeVoid("motorA = Motor('A')");
		replayAll();

		// act
		hub.createMotor(A);
		final Motor motor = hub.getMotorByMotorEnum(A);

		// assert
		assertEquals(new Motor(A, spikeCommandExecutor), motor);

		verifyAll();
	}

	@Test
	public void testInitialize() throws IOException {
		// arrange
		spikeCommandExecutor.executeVoid("\003");
		spikeCommandExecutor.executeVoid("from spike import PrimeHub, LightMatrix, Button, StatusLight, MotionSensor, Speaker, ColorSensor, App, DistanceSensor, Motor");
		spikeCommandExecutor.executeVoid("import hub");
		spikeCommandExecutor.executeVoid("primeHub = PrimeHub()");
		buttonMap.put(ButtonEnum.LEFT,new Button(ButtonEnum.LEFT, spikeCommandExecutor));
		buttonMap.put(ButtonEnum.RIGHT, new Button(ButtonEnum.RIGHT, spikeCommandExecutor));
		buttonMap.put(ButtonEnum.CENTER, new Button(ButtonEnum.CENTER, spikeCommandExecutor));
		hub.initializeEvalFunction();
		replayAll();

		// act
		hub.initialize();

		Assertions.assertNotNull(hub.getButtonByEnum(ButtonEnum.LEFT));
		Assertions.assertNotNull(hub.getButtonByEnum(ButtonEnum.RIGHT));
		Assertions.assertNotNull(hub.getButtonByEnum(ButtonEnum.CENTER));
		verifyAll();
	}

	@Test
	public void testDisplayText() throws IOException {
		// arrange
		spikeCommandExecutor.executeVoid(String.format("hub.display.show(\"%s\")", text));
		replayAll();

		// act
		hub.displayText(text);
		verifyAll();
	}

	@Test
	public void testCreateDistanceSensor() throws IOException {
		// arrange
		spikeCommandExecutor.executeVoid(String.format("distance_sensor = DistanceSensor('%s')", portChar));
		replayAll();

		// act
		hub.createDistanceSensor(portChar);
		verifyAll();
	}

	@Test
	public void testCreateColorSensor() throws IOException {
		// arrange
		spikeCommandExecutor.executeVoid(String.format("color_sensor = ColorSensor('%s')", portChar));
		replayAll();

		// act
		hub.createColorSensor(portChar);
		verifyAll();
	}

}
