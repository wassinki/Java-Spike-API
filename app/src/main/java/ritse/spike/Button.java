package ritse.spike;

import static java.lang.Integer.parseInt;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.logging.Logger;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Button {

	private ButtonEnum buttonEnum;
	private SpikeCommandExecutor spikeCommandExecutor;


	public Button(ButtonEnum buttonEnum, SpikeCommandExecutor executor) {
		this.buttonEnum = buttonEnum;
		this.spikeCommandExecutor = executor;
	}

	public void callback(final Consumer<Integer> callback) throws IOException, InterruptedException {
		spikeCommandExecutor.addCallback(String.format("hub.button.%s.callback", buttonEnum.asString), args -> {
			callback.accept(parseInt(args));
		});
	}

	public void executeWhenPressed(String desiredResult) throws IOException {
		spikeCommandExecutor.executeVoid(String.format("hub.button.%s.callback(lambda x: print(\"!{}:{}:{}%s\".format(\"CB\",1, %s)))", buttonEnum.asString, "%", desiredResult));
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
