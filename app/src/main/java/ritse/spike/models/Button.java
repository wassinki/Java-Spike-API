package ritse.spike.models;

import java.io.IOException;

public class Button {

	private ButtonEnum buttonEnum;
	private SpikeCommandExecutor spikeCommandExecutor;

	public Button(ButtonEnum buttonEnum, SpikeCommandExecutor executor) {
		this.buttonEnum = buttonEnum;
		this.spikeCommandExecutor = executor;
	}

	public boolean isPressed() {
		try {
			return Boolean.parseBoolean(spikeCommandExecutor.execute(String.format("hub.%s_button.is_pressed()", buttonEnum.asString)).toLowerCase());
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void wasPressed() throws InterruptedException {
		try {
			spikeCommandExecutor.executeVoid(String.format("hub.button.%s.callback(lambda x: print(\"!{}:{}:{}%s\".format(\"CB\",900, 12342143)))", buttonEnum.asString, "%"));
//			spikeCommandExecutor.executeVoid(String.format("hub.button.%s.callback(lambda x: hub.display.show(\"HET WERKT\"))", buttonEnum.asString));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
