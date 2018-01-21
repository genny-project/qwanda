package life.genny.qwanda.message;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.annotations.Expose;

import life.genny.qwanda.Answer;

public class QEventAttributeValueChangeMessage extends QEventMessage {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String EVENT_TYPE_EVT_ATTRIBUTE_VALUE_CHANGE = "EVT_ATTRIBUTE_VALUE_CHANGE";
	@Expose
	private Answer answer;
	@Expose
	  private String oldValue;

		public QEventAttributeValueChangeMessage(final Answer answer, String oldValue, String token) {
			super(EVENT_TYPE_EVT_ATTRIBUTE_VALUE_CHANGE, answer.getAttributeCode());
			this.answer  = answer;
			this.oldValue = oldValue;
			setToken(token);
		}
		
	public QEventAttributeValueChangeMessage(String sourceBaseEntityCode,String targetBaseEntityCode, String code, String oldValue, String newValue, String token) {
		super(EVENT_TYPE_EVT_ATTRIBUTE_VALUE_CHANGE, code);
		answer  = new Answer(sourceBaseEntityCode,targetBaseEntityCode, code,newValue);
		this.oldValue = oldValue;
		setToken(token);
	}
	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}


	@Override
	public String toString() {
		return getAnswer().getSourceCode()+":"+getAnswer().getTargetCode() + ":"+ getAnswer().getAttributeCode()+": old->"+oldValue+": new->"
				+ getAnswer().getValue() + " token=" + StringUtils.abbreviateMiddle(getToken(), "...", 30);

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
