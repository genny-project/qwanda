package life.genny.qwanda.message;

import java.util.Arrays;
import java.util.Map;

import com.google.gson.annotations.Expose;

public class QMessageGennyMSG extends QMessage {
	
	private static final String MESSAGE_TYPE = "MSG_MESSAGE";
	
	@Expose
	private String template_code;
	
	@Expose
	private QBaseMSGMessageType msgMessageType;
	
	@Expose
	private String[] recipientArr;
	
	@Expose
	private Map<String, String> messageContextMap;

	/**
	 * @return the template_code
	 */
	public String getTemplate_code() {
		return template_code;
	}

	/**
	 * @param template_code the template_code to set
	 */
	public void setTemplate_code(String template_code) {
		this.template_code = template_code;
	}

	/**
	 * @return the msgMessageType
	 */
	public QBaseMSGMessageType getMsgMessageType() {
		return msgMessageType;
	}

	/**
	 * @param msgMessageType the msgMessageType to set
	 */
	public void setMsgMessageType(QBaseMSGMessageType msgMessageType) {
		this.msgMessageType = msgMessageType;
	}

	/**
	 * @return the recipientArr
	 */
	public String[] getRecipientArr() {
		return recipientArr;
	}

	/**
	 * @param recipientArr the recipientArr to set
	 */
	public void setRecipientArr(String[] recipientArr) {
		this.recipientArr = recipientArr;
	}

	/**
	 * @return the messageContextMap
	 */
	public Map<String, String> getMessageContextMap() {
		return messageContextMap;
	}

	/**
	 * @param messageContextMap the messageContextMap to set
	 */
	public void setMessageContextMap(Map<String, String> messageContextMap) {
		this.messageContextMap = messageContextMap;
	}
	
	public QMessageGennyMSG(String msg_type, QBaseMSGMessageType messageType, String templateCode, Map<String, String> contextMap, String[] recipientArr) {
		super(msg_type);
		this.template_code = templateCode;
		this.msgMessageType = messageType;
		this.messageContextMap = contextMap;
		this.recipientArr = recipientArr;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QMessageMSG [template_code=" + template_code + ", msgMessageType=" + msgMessageType + ", recipientArr="
				+ Arrays.toString(recipientArr) + ", messageContextMap=" + messageContextMap + "]";
	}
	
	
	

}
