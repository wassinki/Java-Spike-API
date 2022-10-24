package ritse.spike;

import java.io.IOException;

public class ColorSensor {

	private SpikeCommandExecutor spikeCommandExecutor;

	public ColorSensor(SpikeCommandExecutor spikeCommandExecutor) {
		this.spikeCommandExecutor = spikeCommandExecutor;
	}

	/**
	 * ACTIONS
	 */
	public void lightUpAll(int brightness) {
		try {
			spikeCommandExecutor.executeVoid(String.format("color_sensor.light_up_all(" + brightness + ")"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void lightUp(int brightness_light_1, int brightness_light_2, int brightness_light_3) {
		try {
			spikeCommandExecutor.executeVoid(String.format("color_sensor.light_up(%d, %d, %d)", brightness_light_1, brightness_light_2, brightness_light_3));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * EVENTS
	 */
	public void waitUntilColor(String color) {
		try {
			spikeCommandExecutor.executeVoid(String.format("color_sensor.wait_until_color('%s')", color));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void waitForNewColor(String color) {
		try {
			spikeCommandExecutor.executeVoid(String.format("color_sensor.wait_for_new_color('%s')", color));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * MEASUREMENTS
	 */
	public String getColor() {
		try {
			return spikeCommandExecutor.execute(String.format("color_sensor.get_color()"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public int getAmbientLight() {
		try {
			return Integer.parseInt(spikeCommandExecutor.execute(String.format("color_sensor.get_ambient_light()")));

		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public int getReflectedLight() {
		try {
			return Integer.parseInt(spikeCommandExecutor.execute(String.format("color_sensor.get_reflected_light()")));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
