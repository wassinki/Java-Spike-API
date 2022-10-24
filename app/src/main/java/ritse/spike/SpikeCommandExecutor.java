package ritse.spike;

import java.io.IOException;

public interface SpikeCommandExecutor {

	void extract(byte[] bytes);

	public String execute(String command) throws IOException, InterruptedException;

	void executeVoid(String command) throws IOException;

	public int getMessageNumber();


}
