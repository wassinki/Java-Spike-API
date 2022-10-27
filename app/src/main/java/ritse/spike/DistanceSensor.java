package ritse.spike;

import java.io.IOException;

public class DistanceSensor {

	private SpikeCommandExecutor spikeCommandExecutor;

	public DistanceSensor(SpikeCommandExecutor executor) {
		this.spikeCommandExecutor = executor;
	}

	/**
	 * ACTIONS
	 */
	public void lightUpAll(int brightness) throws IOException {
		spikeCommandExecutor.executeVoid(String.format("distance_sensor.light_up_all(%d)", brightness));
	}

	public void lightUpAll() throws IOException {
		spikeCommandExecutor.executeVoid(String.format("distance_sensor.light_up_all()"));
	}


	public void lightUp(int right_top, int right_bottom, int left_bottom, int left_top) throws IOException {
		spikeCommandExecutor.executeVoid(String.format("distance_sensor.light_up(%d, %d, %d, %d)", right_top, right_bottom, left_bottom, left_top));
	}

	/**
	 * GETTERS
	 */
	public int getDistanceCm() throws IOException, InterruptedException {
		return Integer.parseInt(spikeCommandExecutor.execute(String.format("distance_sensor.get_distance_cm()")));
	}

	public int getDistanceInches() throws IOException, InterruptedException {
		return Integer.parseInt(spikeCommandExecutor.execute(String.format("distance_sensor.get_distance_inches()")));
	}

	public int getDistancePercentage() throws InterruptedException, IOException {
		return Integer.parseInt(spikeCommandExecutor.execute(String.format("distance_sensor.get_distance_percentage()")));
	}

	/**
	 * EVENTS
	 */
	public void waitForDistanceFartherThan(float distance, String unit) throws IOException, InterruptedException {
		spikeCommandExecutor.execute(String.format("evaluator(\"%s\", %d, \"distance_sensor.wait_for_distance_farther_than(%d, '%s')\")", "RC", spikeCommandExecutor.getMessageNumber(), distance, unit));
	}

	public void waitForDistanceCloserThan(float distance, String unit) throws IOException, InterruptedException {
		spikeCommandExecutor.execute(String.format("evaluator(\"%s\", %d, \"distance_sensor.wait_for_distance_closer_than(%d, '%s')\")", "RC", spikeCommandExecutor.getMessageNumber(), distance, unit));
	}
}