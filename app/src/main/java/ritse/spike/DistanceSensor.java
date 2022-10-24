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
	public void lightUpAll(int brightness) {
		try {
			spikeCommandExecutor.executeVoid(String.format("distance_sensor.light_up_all(%d)", brightness));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void lightUpAll() {
		try {
			spikeCommandExecutor.executeVoid(String.format("distance_sensor.light_up_all()"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}


	public void lightUp(int right_top, int right_bottom, int left_bottom, int left_top) {
		try {
			spikeCommandExecutor.executeVoid(String.format("distance_sensor.light_up(%d, %d, %d, %d)", right_top, right_bottom, left_bottom, left_top));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * GETTERS
	 */
	public int getDistanceCm() {
		try {
			return Integer.parseInt(spikeCommandExecutor.execute(String.format("distance_sensor.get_distance_cm()")));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public int getDistanceInches() {
		try {
			return Integer.parseInt(spikeCommandExecutor.execute(String.format("distance_sensor.get_distance_inches()")));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public int getDistancePercentage() {
		try {
			return Integer.parseInt(spikeCommandExecutor.execute(String.format("distance_sensor.get_distance_percentage()")));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * EVENTS
	 */
	public void waitForDistanceFartherThan(float distance, String unit) {
		try {
			spikeCommandExecutor.execute(String.format("evaluator(\"%s\", %d, \"distance_sensor.wait_for_distance_farther_than(%d, '%s')\")", "RC", spikeCommandExecutor.getMessageNumber(), distance, unit));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void waitForDistanceCloserThan(float distance, String unit) {
		try {
			spikeCommandExecutor.execute(String.format("evaluator(\"%s\", %d, \"distance_sensor.wait_for_distance_closer_than(%d, '%s')\")", "RC", spikeCommandExecutor.getMessageNumber(), distance, unit));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
