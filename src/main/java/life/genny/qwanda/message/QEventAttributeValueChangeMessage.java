package life.genny.qwanda.message;

public class QEventAttributeValueChangeMessage extends QEventMessage {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String EVENT_TYPE_EVT_ATTRIBUTE_VALUE_CHANGE = "EVT_ATTRIBUTE_VALUE_CHANGE";
	  private String sourceBaseEntityCode;
	  private String targetBaseEntityCode;
	  private String oldValue;
	  private String newValue;
	  private String token;

	public QEventAttributeValueChangeMessage(String sourceBaseEntityCode,String targetBaseEntityCode, String code, String oldValue, String newValue, String token) {
		super(EVENT_TYPE_EVT_ATTRIBUTE_VALUE_CHANGE, code);
		this.sourceBaseEntityCode = sourceBaseEntityCode;
		this.targetBaseEntityCode = targetBaseEntityCode;
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.token = token;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
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
		return "QEventAttributeValueChangeMessage [sourceCode="+sourceBaseEntityCode+",targetCode="+targetBaseEntityCode+", oldValue=" + oldValue + ", newValue=" + newValue
				+ ", event_type=" + getEvent_type() + ", msg_type=" + getMsg_type() + "]";
	}

	
	
}
