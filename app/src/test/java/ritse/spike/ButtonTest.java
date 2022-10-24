package ritse.spike;

import java.io.IOException;

import org.easymock.EasyMockExtension;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(EasyMockExtension.class)
public class ButtonTest extends EasyMockSupport {

	private final String desiredResult = "9888000012345";

	@Mock
	private SpikeCommandExecutor spikeCommandExecutor;

	private Button button;

	@BeforeEach
	public void setUp () {
		replayAll();
		button = new Button(ButtonEnum.CENTER, spikeCommandExecutor);
		verifyAll();
		resetAll();
	}

	@Test
	public void testExecuteWhenPressed () throws IOException {
		spikeCommandExecutor.executeVoid(String.format("hub.button.%s.callback(lambda x: print(\"!{}:{}:{}%s\".format(\"CB\",1, %s)))", ButtonEnum.CENTER.asString, "%", desiredResult));
		replayAll();

		button.executeWhenPressed(desiredResult);
		verifyAll();
	}
}
