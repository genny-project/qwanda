package life.genny.qwanda.message;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;

public class QCmdViewMessage extends QCmdMessage {
	
	private static final String CMD_TYPE = "CMD_VIEW";

	@Expose
	private Object root;	
	@Expose
	private String view_type;
	@Expose
	private String data;
	@Expose
	private Boolean isPopup;
	@Expose 
	private QCmdViewMessageAction[] actions;

	

	public QCmdViewMessage(final String view_type, final Object root) {
		super(CMD_TYPE, view_type);
		setRoot(root);
		setView_type(view_type);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	private void setData(final String data) {
		this.data = data;
	}

	public Object getRoot() {
		return root;
	}

	public void setRoot(Object root) {
		this.root = root;
	}

	public String getView_type() {
		return view_type;
	}

	public void setView_type(String view_type) {
		this.view_type = view_type;
	}

	public Boolean getIsPopup() {
		return this.isPopup;
	}

	public void setIsPopup(Boolean isPopup) {
		this.isPopup = isPopup;
		if(isPopup) {
			this.setCmd_type("CMD_POPUP");
		}
		else {
			this.setCmd_type("CMD_VIEW");
		}
	}
	
	private void setAction(QCmdViewMessageAction action) {
		
		if(this.actions == null) {
			this.actions = new QCmdViewMessageAction[1];
		}
		else {
			this.actions = new QCmdViewMessageAction[this.actions.length + 1];
		}
		
		this.actions[this.actions.length - 1] = action;
	}
	
	public void addAction(QCmdViewMessageAction action) {
		this.setAction(action);
	}
		
	public void addAction(String actionCode, String title, String icon) {
		
		QCmdViewMessageAction action = new QCmdViewMessageAction(actionCode, title, icon);
		this.setAction(action);
	}
	
	public void addAction(String actionCode, String title) {
		QCmdViewMessageAction action = new QCmdViewMessageAction(actionCode, title);
		this.setAction(action);
	}
	
	public void addAction(String actionCode) {
		QCmdViewMessageAction action = new QCmdViewMessageAction(actionCode);
		this.setAction(action);
	}

}
