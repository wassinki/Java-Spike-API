package ritse.spike.models;

import java.io.IOException;

public class ColorSensor {

	private SpikeCommandExecutor spikeCommandExecutor;
	public ColorSensor(SpikeCommandExecutor spikeCommandExecutor) {
		this.spikeCommandExecutor = spikeCommandExecutor;
	}

	/** ACTIONS */
	public  void lightUpAll(int brightness) {
		try {
			spikeCommandExecutor.execute("color_sensor.light_up_all(" + brightness + ")");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public  void lightUp(int brightness_light_1, int brightness_light_2, int brightness_light_3) {
		try {
			spikeCommandExecutor.execute(String.format("color_sensor.light_up(%d, %d, %d)", brightness_light_1, brightness_light_2, brightness_light_3));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/** EVENTS */
	public  void waitUntilColor(String color) {
		try {
			spikeCommandExecutor.execute(String.format("color_sensor.wait_until_color('%s')", color));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public  void waitForNewColor(String color) {
		try {
			spikeCommandExecutor.execute(String.format("color_sensor.wait_for_new_color('%s')", color));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/** MEASUREMENTS */
	public String getColor () {
		try {
			spikeCommandExecutor.execute("color_sensor.get_color()");
			return null;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public int getAmbientLight() {
		try {
			spikeCommandExecutor.execute("color_sensor.get_ambient_light()");
			return 0;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public int getReflectedLight() {
		try {
			spikeCommandExecutor.execute("color_sensor.get_reflected_light()");
			return 0;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
