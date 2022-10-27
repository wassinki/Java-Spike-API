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
	public void lightUpAll(int brightness) throws IOException {
		spikeCommandExecutor.executeVoid(String.format("color_sensor.light_up_all(" + brightness + ")"));
	}

	public void lightUp(int brightness_light_1, int brightness_light_2, int brightness_light_3) throws IOException {
		spikeCommandExecutor.executeVoid(String.format("color_sensor.light_up(%d, %d, %d)", brightness_light_1, brightness_light_2, brightness_light_3));
	}

	/**
	 * EVENTS
	 */
	public void waitUntilColor(String color) throws IOException {
		spikeCommandExecutor.executeVoid(String.format("color_sensor.wait_until_color('%s')", color));
	}

	public void waitForNewColor(String color) throws IOException {
		spikeCommandExecutor.executeVoid(String.format("color_sensor.wait_for_new_color('%s')", color));
	}

	/**
	 * MEASUREMENTS
	 */
	public String getColor() throws InterruptedException, IOException {
		return spikeCommandExecutor.execute(String.format("color_sensor.get_color()"));
	}

	public int getAmbientLight() throws IOException, InterruptedException {
		return Integer.parseInt(spikeCommandExecutor.execute(String.format("color_sensor.get_ambient_light()")));
	}

	public int getReflectedLight() throws IOException, InterruptedException {
		return Integer.parseInt(spikeCommandExecutor.execute(String.format("color_sensor.get_reflected_light()")));
	}
}
