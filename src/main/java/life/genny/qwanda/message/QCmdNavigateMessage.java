package life.genny.qwanda.message;

import com.google.gson.annotations.Expose;

public class QCmdNavigateMessage extends QCmdMessage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String MESSAGE_TYPE = "CMD_MSG";
	private static final String MESSAGE_CODE = "ROUTE_CHANGE";
	
	@Expose
	private String route;
	
	
	public QCmdNavigateMessage(String route) {
		super(MESSAGE_TYPE, MESSAGE_CODE);
		this.route = route;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}
	
}
