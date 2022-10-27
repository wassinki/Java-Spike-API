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
	public void runForSeconds(int time) throws IOException {
		spikeCommandExecutor.executeVoid(String.format("motor%s.run_for_seconds(" + time + ")", motorEnum.asString));
	}

	public void runForDegrees(int degrees) throws IOException {
		spikeCommandExecutor.executeVoid(String.format("motor%s.run_for_degrees(" + degrees + ")", motorEnum.asString));
	}

	public void runToPosition(int position) throws IOException, InterruptedException {
		spikeCommandExecutor.executeVoid(String.format("motor%s.run_to_position(" + position + ")", motorEnum.asString));
	}

	public void runToDegreesCounted(int degrees) throws IOException {
		spikeCommandExecutor.executeVoid(String.format("motor%s.run_to_degrees_counted(" + degrees + ")", motorEnum.asString));
	}

	public void runForRotations(float rotations) throws IOException {
		spikeCommandExecutor.executeVoid(String.format("motor%s.run_for_rotations(" + rotations + ")", motorEnum.asString));
	}

	public void start(int speed) throws IOException {
		spikeCommandExecutor.executeVoid(String.format("motor%s.start(" + speed + ")", motorEnum.asString));
	}

	public void start() throws IOException {
		spikeCommandExecutor.executeVoid(String.format("motor%s.start()", motorEnum.asString));
	}

	public void stop() throws IOException {
		spikeCommandExecutor.executeVoid(String.format("motor%s.stop()", motorEnum.asString));
	}

	public void startAtPower(int power) throws IOException {
		spikeCommandExecutor.executeVoid(String.format("motor%s.start_at_power(" + power + ")", motorEnum.asString));
	}


	/**
	 * GETTERS / MEASUREMENTS
	 */
	public int getSpeed() throws IOException, InterruptedException {
		return Integer.parseInt(spikeCommandExecutor.execute(String.format("motor%s.get_speed()", motorEnum.asString)));
	}

	public int getPosition() throws IOException, InterruptedException {
		return Integer.parseInt(spikeCommandExecutor.execute(String.format("motor%s.get_position()", motorEnum.asString)));
	}

	public int getDegreesCounted() throws IOException, InterruptedException {
		return Integer.parseInt(spikeCommandExecutor.execute(String.format("motor%s.get_degrees_counted()", motorEnum.asString)));
	}

	public int getDefaultSpeed() throws IOException, InterruptedException {
		return Integer.parseInt(spikeCommandExecutor.execute(String.format("motor%s.get_default_speed()", motorEnum.asString)));
	}

	/**
	 * EVENTS
	 */
	public boolean wasInterupted() throws IOException, InterruptedException {
		return Boolean.parseBoolean(spikeCommandExecutor.execute(String.format("evaluator(\"motor%s.was_interrupted()\")", motorEnum.asString)));
	}

	public boolean wasStalled() throws IOException, InterruptedException {
		return Boolean.parseBoolean(spikeCommandExecutor.execute(String.format("evaluator(\"motor%s.was_stalled()\")", motorEnum.asString)));
	}

	/**
	 * Settings
	 */
	public void setDegreesCounted(int degrees_counted) throws IOException {
		spikeCommandExecutor.executeVoid(String.format("motor%s.set_degrees_counted(" + degrees_counted + ")", motorEnum.asString));
	}

	public void setDefaultSpeed(int default_speed) throws IOException {
		spikeCommandExecutor.executeVoid(String.format("motor%s.set_default_speed(" + default_speed + ")", motorEnum.asString));
	}

	public void setStopAction(int stop_action) throws IOException {
		spikeCommandExecutor.executeVoid(String.format("motor%s.set_stop_action(" + stop_action + ")", motorEnum.asString));
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