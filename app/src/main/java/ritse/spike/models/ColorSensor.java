package ritse.spike.models;

import java.io.IOException;

public class ColorSensor {

	public ColorSensor() {
	}

	/** ACTIONS */
	public  void light_up_all (int brightness) {
		try {
			MindstormsHub.writeString("color_sensor.light_up_all(" + brightness + ")");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public  void light_up (int brightness_light_1, int brightness_light_2, int brightness_light_3) {
		try {
			MindstormsHub.writeString(String.format("color_sensor.light_up(%d, %d, %d)", brightness_light_1, brightness_light_2, brightness_light_3));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/** EVENTS */
	public  void wait_until_color (String color) {
		try {
			MindstormsHub.writeString(String.format("color_sensor.wait_until_color('%s')", color));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public  void wait_for_new_color (String color) {
		try {
			MindstormsHub.writeString(String.format("color_sensor.wait_for_new_color('%s')", color));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/** MEASUREMENTS */
	public String getColor () {
		try {
			MindstormsHub.writeString("color_sensor.get_color()");
			return null;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public int get_ambient_light () {
		try {
			MindstormsHub.writeString("color_sensor.get_ambient_light()");
			return 0;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public int get_reflected_light () {
		try {
			MindstormsHub.writeString("color_sensor.get_reflected_light()");
			return 0;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}


}
