package ritse.spike;

import java.io.IOException;
import java.util.function.Consumer;

public interface SpikeCommandExecutor {

	void extract(byte[] bytes);

	public String execute(String command) throws IOException, InterruptedException;

	void executeVoid(String command) throws IOException;

	public int getMessageNumber();

	void addCallback(final String command, Consumer<String> callback) throws IOException, InterruptedException;
}
