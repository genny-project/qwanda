package life.genny.qwanda.message;

import com.google.gson.annotations.Expose;

public class QCmdViewMessageAction {
	
	@Expose
	private String actionCode;
	
	@Expose 
	private String title;
	
	@Expose
	private String icon;
	
	public QCmdViewMessageAction(String actionCode, String title, String icon) {
		
		this.actionCode = actionCode;
		this.title = title;
		this.icon = icon;
	}
	
	public QCmdViewMessageAction(String actionCode, String title) {
		
		this.actionCode = actionCode;
		this.title = title;
	}
	
	public QCmdViewMessageAction(String actionCode) {
		
		this.actionCode = actionCode;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
