package life.genny.qwanda.message;

import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.annotations.Expose;

import life.genny.qwanda.Answer;
import life.genny.qwanda.attribute.EntityAttribute;
import life.genny.qwanda.entity.BaseEntity;

public class QEventAttributeValueChangeMessage extends QEventMessage {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String EVENT_TYPE_EVT_ATTRIBUTE_VALUE_CHANGE = "EVT_ATTRIBUTE_VALUE_CHANGE";
	@Expose
	private Answer answer;
	
	@Expose
	@Transient
	private EntityAttribute ea;
	
	@Expose
	@Transient
	private BaseEntity be;
	
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

	/**
	 * @return the ea
	 */
	public EntityAttribute getEa() {
		return ea;
	}

	/**
	 * @param ea the ea to set
	 */
	public void setEa(EntityAttribute ea) {
		this.ea = ea;
	}

	/**
	 * @return the be
	 */
	public BaseEntity getBe() {
		return be;
	}

	/**
	 * @param be the be to set
	 */
	public void setBe(BaseEntity be) {
		this.be = be;
	}

	
	
}
