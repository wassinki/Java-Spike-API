package ritse.spike;

public enum ButtonEnum {
	LEFT("left"),
	RIGHT("right"),
	CENTER("center");

	public String asString;

	ButtonEnum(String value) {
		this.asString = value;
	}

	public String getAsString() {
		return asString;
	}
}
