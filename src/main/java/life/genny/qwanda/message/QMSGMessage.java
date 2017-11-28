package life.genny.qwanda.message;

import java.util.Arrays;

public class QMSGMessage extends QMessage {
	
	private static final String MESSAGE_TYPE = "MSG_MESSAGE";
	
	private static final String event_type = "message";
	
	private String template_code;
	private String[] msgMessageData;
	private QBaseMSGMessageType msgMessageType;
	private String[] attachments;
	
	//private List<QBaseMSGMessage> msgMessageData;
	
	
	/*public List<QBaseMSGMessage> getMsgMessageData() {
		return msgMessageData;
	}

	public void setMsgMessageData(List<QBaseMSGMessage> msgMessageData) {
		this.msgMessageData = msgMessageData;
	}*/

	public String getTemplate_code() {
		return template_code;
	}


	public void setTemplate_code(String template_code) {
		this.template_code = template_code;
	}

	public String[] getMsgMessageData() {
		return msgMessageData;
	}

	public void setMsgMessageData(String[] msgMessageData) {
		this.msgMessageData = msgMessageData;
	}

	public QBaseMSGMessageType getMsgMessageType() {
		return msgMessageType;
	}

	public void setMsgMessageType(QBaseMSGMessageType msgMessageType) {
		this.msgMessageType = msgMessageType;
	}

	public static String getEventType() {
		return event_type;
	}
	
	public String[] getAttachments() {
		return attachments;
	}

	public void setAttachments(String[] attachments) {
		this.attachments = attachments;
	}



	public QMSGMessage(String msg_type) {
		super(MESSAGE_TYPE);
		//this.msgMessageData = new QBaseMSGMessage();
	}


	@Override
	public String toString() {
		return "QMSGMessage [template_code=" + template_code + ", msgMessageData=" + Arrays.toString(msgMessageData)
				+ ", msgMessageType=" + msgMessageType + "]";
	}
	
	
	
}
