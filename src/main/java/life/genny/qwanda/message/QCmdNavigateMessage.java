package life.genny.qwanda.message;

import com.google.gson.annotations.Expose;

import life.genny.qwanda.entity.NavigationType;

public class QCmdNavigateMessage extends QCmdMessage {

	private static final long serialVersionUID = 1L;

    private static final String CMD_TYPE = "ROUTE_CHANGE";
	private static final String MESSAGE_CODE = "ROUTE_CHANGE";

	@Expose
	private String route;


	public QCmdNavigateMessage(NavigationType navigationType, String route) {
		super(navigationType.getNavigationType(), MESSAGE_CODE);
		this.route = route;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}
}
