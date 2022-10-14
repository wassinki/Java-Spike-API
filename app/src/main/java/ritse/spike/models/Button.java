package ritse.spike.models;

import java.io.IOException;

public class Button {

	private String buttonEnum;
	private SpikeCommandExecutor spikeCommandExecutor;

	public Button(ButtonEnum buttonEnum, SpikeCommandExecutor executor) {
		this.buttonEnum = buttonEnum.asString;
		this.spikeCommandExecutor = executor;
	}

	public boolean isPressed() {
		try {
			return Boolean.parseBoolean(spikeCommandExecutor.execute(String.format("hub.%s_button.is_pressed()", buttonEnum)).toLowerCase());
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean wasPressed() throws InterruptedException {
		try {
			return Boolean.parseBoolean(spikeCommandExecutor.execute(String.format("hub.%s_button.was_pressed()", buttonEnum)).toLowerCase());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
