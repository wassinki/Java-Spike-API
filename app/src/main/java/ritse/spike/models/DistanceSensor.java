package ritse.spike.models;

import java.io.IOException;

public class DistanceSensor {

	private SpikeCommandExecutor spikeCommandExecutor;
	public DistanceSensor(SpikeCommandExecutor executor) {
		this.spikeCommandExecutor = executor;
	}

	/** ACTIONS */
	public void lightUpAll(int brightness) {
		try {
			spikeCommandExecutor.execute(String.format("evaluator(\"%s\", %d, \"distance_sensor.light_up_all(%d)\")","RC", spikeCommandExecutor.getMessageNumber(), brightness));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void lightUpAll() {
		try {
			try {
				spikeCommandExecutor.execute(String.format("evaluator(\"%s\", %d, \"distance_sensor.light_up_all()\")", "RC", spikeCommandExecutor.getMessageNumber()));
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void lightUp(int right_top, int right_bottom, int left_bottom, int left_top) {
		try {
			spikeCommandExecutor.execute(String.format("evaluator(\"%s\", %d, \"distance_sensor.light_up(%d, %d, %d, %d)\")","RC", spikeCommandExecutor.getMessageNumber(), right_top, right_bottom, left_bottom, left_top));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	/** GETTERS */
	public void getDistanceCm() {
		try {
			spikeCommandExecutor.execute(String.format("evaluator(\"%s\", %d, \"distance_sensor.get_distance_cm()\")", "RC", spikeCommandExecutor.getMessageNumber()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	public void getDistanceInches() {
		try {
			spikeCommandExecutor.execute(String.format("evaluator(\"%s\", %d, \"distance_sensor.get_distance_inches()\")", "RC", spikeCommandExecutor.getMessageNumber()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void getDistancePercentage() {
		try {
			spikeCommandExecutor.execute(String.format("evaluator(\"%s\", %d, \"distance_sensor.get_distance_percentage()\")", "RC", spikeCommandExecutor.getMessageNumber()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	/** EVENTS */
	public void waitForDistanceFartherThan(float distance, String unit) {
		try {
			spikeCommandExecutor.execute(String.format("evaluator(\"%s\", %d, \"distance_sensor.wait_for_distance_farther_than(%d, '%s')\")","RC", spikeCommandExecutor.getMessageNumber(), distance, unit));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void waitForDistanceCloserThan(float distance, String unit) {
		try {
			spikeCommandExecutor.execute(String.format("evaluator(\"%s\", %d, \"distance_sensor.wait_for_distance_closer_than(%d, '%s')\")","RC", spikeCommandExecutor.getMessageNumber(), distance, unit));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
