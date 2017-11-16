package life.genny.qwanda.message;

public class QEventLinkChangeMessage extends QEventMessage {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String EVENT_TYPE_LINK_CHANGE = "EVT_LINK_CHANGE";
	  private String sourceBaseEntityCode;
	  private String targetBaseEntityCode;
	  private String linkCode;
	  private String token;

	public QEventLinkChangeMessage(String sourceBaseEntityCode,String targetBaseEntityCode, String code, String linkCode, String token) {
		super(EVENT_TYPE_LINK_CHANGE, code);
		this.sourceBaseEntityCode = sourceBaseEntityCode;
		this.targetBaseEntityCode = targetBaseEntityCode;
		this.linkCode = linkCode;
		this.token = token;
	}


	

	public String getLinkCode() {
		return linkCode;
	}




	public void setLinkCode(String linkCode) {
		this.linkCode = linkCode;
	}




	public String getTargetBaseEntityCode() {
		return targetBaseEntityCode;
	}

	public void setTargetBaseEntityCode(String targetBaseEntityCode) {
		this.targetBaseEntityCode = targetBaseEntityCode;
	}

	
	
	public String getSourceBaseEntityCode() {
		return sourceBaseEntityCode;
	}

	public void setSourceBaseEntityCode(String sourceBaseEntityCode) {
		this.sourceBaseEntityCode = sourceBaseEntityCode;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "QEventLinkChangeMessage [sourceCode="+sourceBaseEntityCode+",targetCode="+targetBaseEntityCode+", linkCodee=" +linkCode 
				+ ", event_type=" + getEvent_type() + ", msg_type=" + getMsg_type() + "]";
	}

	
	
}
