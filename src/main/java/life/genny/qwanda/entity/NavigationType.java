package life.genny.qwanda.entity;

public enum NavigationType {
	
	Back("ROUTE_BACK"),
	RouteChange("ROUTE_CHANGE");
	
	String navigationType;
	NavigationType(String type) {
		this.navigationType = type;
	}
	
	public String getNavigationType() {
		return this.navigationType;
	}
}
