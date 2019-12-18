package life.genny.qwanda.datatype;

public enum CapabilityMode {
	NONE ("NONE"),
	VIEW("VIEW"),
	ADD ("ADD"),
	EDIT ("EDIT"),
	DELETE ("DELETE");



	private final String name;

	private CapabilityMode(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		// (otherName == null) check is not needed because name.equals(null) returns
		// false
		return name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}
}