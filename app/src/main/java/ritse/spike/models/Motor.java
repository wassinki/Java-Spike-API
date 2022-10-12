package ritse.spike.models;

import java.io.IOException;

public class Motor {

	private MotorEnum motorEnum;
	private SpikeCommandExecutor spikeCommandExecutor;

	public Motor(MotorEnum motorEnum, SpikeCommandExecutor executor) {
		this.spikeCommandExecutor = executor;
		this.motorEnum = motorEnum;
	}

	/** ACTIONS */
	public void runForSeconds(int time) {
		try {
			spikeCommandExecutor.execute(String.format("evaluator(\"%s\", %d, \"motor%s.run_for_seconds(" + time + ")\")","RC", spikeCommandExecutor.getMessageNumber(), motorEnum.asString));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void runForDegrees(int degrees) {
		try {
			spikeCommandExecutor.execute(String.format("evaluator(\"%s\", %d, \"motor%s.run_for_degrees(" + degrees + ")\")","RC", spikeCommandExecutor.getMessageNumber(), motorEnum.asString));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void runToPosition(int position) throws IOException, InterruptedException {
		spikeCommandExecutor.execute(String.format("evaluator(\"%s\", %d, \"motor%s.run_to_position(" + position + ")\")","RC", spikeCommandExecutor.getMessageNumber(), motorEnum.asString));
	}

	public void runToDegreesCounted(int degrees) {
		try {
			spikeCommandExecutor.execute(String.format("evaluator(\"%s\", %d, \"motor%s.run_to_degrees_counted(" + degrees + ")\")","RC", spikeCommandExecutor.getMessageNumber(), motorEnum.asString));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void runForRotations(float rotations) {
		try {
			spikeCommandExecutor.execute(String.format("evaluator(\"%s\", %d, \"motor%s.run_for_rotations(" + rotations + ")\")","RC", spikeCommandExecutor.getMessageNumber(), motorEnum.asString));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void start(int speed) {
		try {
			spikeCommandExecutor.execute(String.format("evaluator(\"%s\", %d, \"motor%s.start(" + speed + ")\")","RC", spikeCommandExecutor.getMessageNumber(), motorEnum.asString));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void start() {
		try {
			spikeCommandExecutor.execute(String.format("evaluator(\"%s\", %d, \"motor%s.start()\")", "RC", spikeCommandExecutor.getMessageNumber(), motorEnum.asString));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void stop() {
		try {
			spikeCommandExecutor.execute(String.format("evaluator(\"%s\", %d, \"motor%s.stop()\")","RC", spikeCommandExecutor.getMessageNumber(), motorEnum.asString));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

	}

	public void startAtPower(int power) {
		try {
			spikeCommandExecutor.execute(String.format("evaluator(\"%s\", %d, \"motor%s.start_at_power(" + power + ")\")","RC", spikeCommandExecutor.getMessageNumber(), motorEnum.asString));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}


	/**
	 * GETTERS / MEASUREMENTS
	 */
	public int getSpeed() {
		try {
			spikeCommandExecutor.execute(String.format("evaluator(\"%s\", %d, \"motor%s.get_speed()\")","RC", spikeCommandExecutor.getMessageNumber(), motorEnum.asString));
			return 0;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public int getPosition() {
		try {
			spikeCommandExecutor.execute(String.format("evaluator(\"%s\", %d, \"motor%s.get_position()\")","RC", spikeCommandExecutor.getMessageNumber(), motorEnum.asString));
			System.out.println(spikeCommandExecutor.getResult());
			return 0;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public int getDegreesCounted() {
		try {
			spikeCommandExecutor.execute(String.format("evaluator(\"%s\", %d, \"motor%s.get_degrees_counted()\")","RC", spikeCommandExecutor.getMessageNumber(), motorEnum.asString));
			return 0;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public int getDefaultSpeed() {
		try {
			spikeCommandExecutor.execute(String.format("evaluator(\"%s\", %d, \"motor%s.get_default_speed()\")","RC", spikeCommandExecutor.getMessageNumber(), motorEnum.asString));
			return 0;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * EVENTS
	 */
	public boolean wasInterupted() {
		try {
			spikeCommandExecutor.execute(String.format("evaluator(\"%s\", %d, \"motor%s.was_interrupted()\")","RC", spikeCommandExecutor.getMessageNumber(), motorEnum.asString));
			return false;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean wasStalled() {
		try {
			spikeCommandExecutor.execute(String.format("evaluator(\"%s\", %d, \"motor%s.was_stalled()\")","RC", spikeCommandExecutor.getMessageNumber(), motorEnum.asString));
			return false;
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Settings
	 */
	public void setDegreesCounted(int degrees_counted) {
		try {
			spikeCommandExecutor.execute(String.format("evaluator(\"%s\", %d, \"motor%s.set_degrees_counted(" + degrees_counted + ")\")","RC", spikeCommandExecutor.getMessageNumber(), motorEnum.asString));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void setDefaultSpeed(int default_speed) {
		try {
			spikeCommandExecutor.execute(String.format("evaluator(\"%s\", %d, \"motor%s.set_default_speed(" + default_speed + ")\")","RC", spikeCommandExecutor.getMessageNumber(), motorEnum.asString));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void setStopAction(int stop_action) {
		try {
			spikeCommandExecutor.execute(String.format("evaluator(\"%s\", %d, \"motor%s.set_stop_action(" + stop_action + ")\")","RC", spikeCommandExecutor.getMessageNumber(), motorEnum.asString));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void setDefaultSpeed(boolean stall_detection) {
		try {
			if (stall_detection) {
				spikeCommandExecutor.execute(String.format("evaluator(\"%s\", %d, \"motor%s.set_stall_detection(True)\")","RC", spikeCommandExecutor.getMessageNumber(), motorEnum.asString));
			}
			else {
				spikeCommandExecutor.execute(String.format("evaluator(\"%s\", %d, \"motor%s.set_stall_detection(False)\")","RC", spikeCommandExecutor.getMessageNumber(), motorEnum.asString));
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
