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
			spikeCommandExecutor.execute(String.format("hub.{}_button_is_pressed()", buttonEnum.asString));
			return false;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
