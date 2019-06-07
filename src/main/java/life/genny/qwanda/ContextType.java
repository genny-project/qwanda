package life.genny.qwanda;

public enum ContextType {
	THEME("THEME"),
	ICON("ICON");

	private final String type;
	
	ContextType(String value) {
		this.type = value;
	}
	
	public String contextType() {
		return type;
	}	
}
