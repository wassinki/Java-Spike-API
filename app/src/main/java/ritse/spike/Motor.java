package ritse.spike;

import java.io.IOException;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Motor {

	private MotorEnum motorEnum;
	private SpikeCommandExecutor spikeCommandExecutor;

	public Motor(MotorEnum motorEnum, SpikeCommandExecutor executor) {
		this.spikeCommandExecutor = executor;
		this.motorEnum = motorEnum;
	}

	/**
	 * ACTIONS
	 */
	public void runForSeconds(int time) {
		try {
			spikeCommandExecutor.executeVoid(String.format("motor%s.run_for_seconds(" + time + ")", motorEnum.asString));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void runForDegrees(int degrees) {
		try {
			spikeCommandExecutor.executeVoid(String.format("motor%s.run_for_degrees(" + degrees + ")", motorEnum.asString));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void runToPosition(int position) throws IOException, InterruptedException {
		spikeCommandExecutor.executeVoid(String.format("motor%s.run_to_position(" + position + ")", motorEnum.asString));
	}

	public void runToDegreesCounted(int degrees) {
		try {
			spikeCommandExecutor.executeVoid(String.format("motor%s.run_to_degrees_counted(" + degrees + ")", motorEnum.asString));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void runForRotations(float rotations) {
		try {
			spikeCommandExecutor.executeVoid(String.format("motor%s.run_for_rotations(" + rotations + ")", motorEnum.asString));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void start(int speed) {
		try {
			spikeCommandExecutor.executeVoid(String.format("motor%s.start(" + speed + ")", motorEnum.asString));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void start() {
		try {
			spikeCommandExecutor.executeVoid(String.format("motor%s.start()", motorEnum.asString));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void stop() {
		try {
			spikeCommandExecutor.executeVoid(String.format("motor%s.stop()", motorEnum.asString));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void startAtPower(int power) {
		try {
			spikeCommandExecutor.executeVoid(String.format("motor%s.start_at_power(" + power + ")", motorEnum.asString));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}


	/**
	 * GETTERS / MEASUREMENTS
	 */
	public int getSpeed() {
		try {
			return Integer.parseInt(spikeCommandExecutor.execute(String.format("motor%s.get_speed()", motorEnum.asString)));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public int getPosition() {
		try {
			return Integer.parseInt(spikeCommandExecutor.execute(String.format("motor%s.get_position()", motorEnum.asString)));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public int getDegreesCounted() {
		try {
			return Integer.parseInt(spikeCommandExecutor.execute(String.format("motor%s.get_degrees_counted()", motorEnum.asString)));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public int getDefaultSpeed() {
		try {
			return Integer.parseInt(spikeCommandExecutor.execute(String.format("motor%s.get_default_speed()", motorEnum.asString)));
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
			return Boolean.parseBoolean(spikeCommandExecutor.execute(String.format("evaluator(\"motor%s.was_interrupted()\")", motorEnum.asString)));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean wasStalled() {
		try {
			return Boolean.parseBoolean(spikeCommandExecutor.execute(String.format("evaluator(\"motor%s.was_stalled()\")", motorEnum.asString)));
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Settings
	 */
	public void setDegreesCounted(int degrees_counted) {
		try {
			spikeCommandExecutor.executeVoid(String.format("motor%s.set_degrees_counted(" + degrees_counted + ")", motorEnum.asString));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void setDefaultSpeed(int default_speed) {
		try {
			spikeCommandExecutor.executeVoid(String.format("motor%s.set_default_speed(" + default_speed + ")", motorEnum.asString));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void setStopAction(int stop_action) {
		try {
			spikeCommandExecutor.executeVoid(String.format("motor%s.set_stop_action(" + stop_action + ")", motorEnum.asString));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}