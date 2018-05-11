package life.genny.qwanda.message;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class QCmdNavigateMessage extends QMessage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String MESSAGE_TYPE = "CMD_MSG";
	
	@Expose
	private String route;
	
	
	public QCmdNavigateMessage(String route) {
		super(MESSAGE_TYPE);
		this.route = route;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}
	
}
