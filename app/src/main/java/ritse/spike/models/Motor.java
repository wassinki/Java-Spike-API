package ritse.spike.models;

import java.io.IOException;

public class Motor {

	private String motor;

	public Motor(String motorChar) {
		this.motor = motorChar;
	}

	public void createMotor(String letter) {
		try {
			MindstormsHub.writeString("motor = Motor('" + letter + "')");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/** ACTIONS */
	public void run_for_seconds(int time) {
		try {
			MindstormsHub.writeString("motor.run_for_seconds(" + time + ")");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void run_for_degrees(int degrees) {
		try {
			MindstormsHub.writeString("motor.run_for_degrees(" + degrees + ")");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void run_to_position(int position) throws IOException {
		MindstormsHub.writeString("motor.run_to_position(" + position + ")");
	}

	public void run_to_degrees_counted(int degrees) {
		try {
			MindstormsHub.writeString("motor.run_to_degrees_counted(" + degrees + ")");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void run_for_rotations(float rotations) {
		try {
			MindstormsHub.writeString("motor.run_for_rotations(" + rotations + ")");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void start(int speed) {
		try {
			MindstormsHub.writeString("motor.start(" + speed + ")");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void start() {
		try {
			MindstormsHub.writeString("motor.start()");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void stop() {
		try {
			MindstormsHub.writeString("motor.stop()");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public void start_at_power(int power) {
		try {
			MindstormsHub.writeString("motor.start_at_power(" + power + ")");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}


	/**
	 * GETTERS / MEASUREMENTS
	 */
	public int get_speed() {
		try {
			MindstormsHub.writeString("motor.get_speed()");
			return 0;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public int get_position() {
		try {
			MindstormsHub.writeString("motor.get_position()");
			return 0;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public int get_degrees_counted() {
		try {
			MindstormsHub.writeString("motor.get_degrees_counted()");
			return 0;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public int get_default_speed() {
		try {
			MindstormsHub.writeString("motor.get_default_speed()");
			return 0;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * EVENTS
	 */
	public boolean was_interupted() {
		try {
			MindstormsHub.writeString("motor.was_interrupted()");
			return false;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean was_stalled() {
		try {
			MindstormsHub.writeString("motor.was_stalled()");
			return false;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Settings
	 */
	public void set_degrees_counted(int degrees_counted) {
		try {
			MindstormsHub.writeString("motor.set_degrees_counted(" + degrees_counted + ")");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void set_default_speed(int default_speed) {
		try {
			MindstormsHub.writeString("motor.set_default_speed(" + default_speed + ")");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void set_stop_action(int stop_action) {
		try {
			MindstormsHub.writeString("motor.set_stop_action(" + stop_action + ")");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void set_default_speed(boolean stall_detection) {
		try {
			if (stall_detection) {
				MindstormsHub.writeString("motor.set_stall_detection(True)");
			}
			else {
				MindstormsHub.writeString("motor.set_stall_detection(False)");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
