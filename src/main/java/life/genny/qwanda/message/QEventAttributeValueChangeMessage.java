package life.genny.qwanda.message;

import life.genny.qwanda.Answer;

public class QEventAttributeValueChangeMessage extends QEventMessage {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String EVENT_TYPE_EVT_ATTRIBUTE_VALUE_CHANGE = "EVT_ATTRIBUTE_VALUE_CHANGE";
	private Answer answer;
	  private String oldValue;
	  private String token;

		public QEventAttributeValueChangeMessage(final Answer answer, String oldValue, String token) {
			super(EVENT_TYPE_EVT_ATTRIBUTE_VALUE_CHANGE, answer.getAttributeCode());
			this.answer  = answer;
			this.oldValue = oldValue;
			this.token = token;
		}
		
	public QEventAttributeValueChangeMessage(String sourceBaseEntityCode,String targetBaseEntityCode, String code, String oldValue, String newValue, String token) {
		super(EVENT_TYPE_EVT_ATTRIBUTE_VALUE_CHANGE, code);
		answer  = new Answer(sourceBaseEntityCode,targetBaseEntityCode, code,newValue);
		this.oldValue = oldValue;
		this.token = token;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "QEventAttributeValueChangeMessage ["+answer+", oldValue=" + oldValue 
				+ ", event_type=" + getEvent_type() + ", msg_type=" + getMsg_type() + "]";
	}

	/**
	 * @return the answer
	 */
	public Answer getAnswer() {
		return answer;
	}

	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	
	
}
