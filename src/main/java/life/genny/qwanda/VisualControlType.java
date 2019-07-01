package life.genny.qwanda;

public enum VisualControlType {
	VCL_WRAPPER ("VCL_WRAPPER"),
	VCL_INPUT ("VCL_INPUT"),
	VCL_ICON ("VCL_ICON"),
	VCL_LABEL ("VCL_LABEL"),
	VCL_DESCRIPTION ("VCL_DESCRIPTION"),
	VCL_HINT ("VCL_HINT"),
	VCL_ERROR ("VCL_ERROR"),
	VCL_REQUIRED ("VCL_REQUIRED"),
	VCL_DELIMITER ("VCL_DELIMITER");


	private final String name;

	private VisualControlType(String s) {
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