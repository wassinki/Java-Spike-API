package ritse.spike.models;

public enum MotorEnum {
	A("A"),
	B("B"),
	C("C"),
	D("D"),
	E("E"),
	F("F");

	public String asString;

	MotorEnum(String f) {
		this.asString = f;
	}
}
