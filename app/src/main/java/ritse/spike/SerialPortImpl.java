package ritse.spike;

import static com.fazecast.jSerialComm.SerialPort.getCommPorts;
import static com.google.common.collect.Streams.concat;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortMessageListener;

public class SerialPortImpl implements LegoSerialPort {

	private final SerialPort serialPort;

	public SerialPortImpl(final String port, final int baudrate, final Consumer<byte[]> packetHandler) {
		serialPort = SerialPort.getCommPort(port);
		serialPort.setBaudRate(baudrate);

		serialPort.addDataListener(new SerialPortMessageListener() {
			@Override
			public byte[] getMessageDelimiter() {
				return "%".getBytes();
			}

			@Override
			public boolean delimiterIndicatesEndOfMessage() {
				return true;
			}

			@Override
			public int getListeningEvents() {
				return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
			}

			@Override
			public void serialEvent(SerialPortEvent event) {
				packetHandler.accept(event.getReceivedData());
			}
		});
	}

	@Override
	public boolean isPortOpen() {
		return serialPort.isOpen();
	}

	@Override
	public String getPortName() {
		return serialPort.getSystemPortName();
	}

	@Override
	public void open() throws IOException {
		serialPort.openPort();
	}

	@Override
	public Optional<InputStream> getInputStream() {
		return Optional.of(serialPort.getInputStream());
	}

	@Override
	public Optional<OutputStream> getOutputStream() {
		return Optional.of(serialPort.getOutputStream());
	}

	@Override
	public void close() {
		serialPort.closePort();
	}

	/**
	 * Returns the list of serial ports
	 *
	 * @return the list of serial ports
	 */
	public static List<String> getSerialPorts() {
		// the rpmsg serial ports are virtual ports, so add them manually
		final List<String> rpmsgFiles = new ArrayList<>();
		try (final DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get("/dev"), file -> file.getFileName().toString().startsWith("ttyRPMSG"))) {
			directoryStream.forEach(serialPortPath -> rpmsgFiles.add(serialPortPath.toFile().getName()));
		} catch (IOException e) {
			// directory not available, skip rpmsg files
		}
		return concat(stream(getCommPorts()).map(com.fazecast.jSerialComm.SerialPort::getSystemPortName), rpmsgFiles.stream())
				.filter(n -> !n.startsWith("cu"))
				.collect(toList());
	}
}
