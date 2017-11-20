package life.genny.qwanda.message;

import java.util.List;

public class QMSGMessage extends QMessage {
	
	private static final String MESSAGE_TYPE = "MSG_MESSAGE";
	private List<QBaseMSGMessage> msgMessageData;
	
	
	public List<QBaseMSGMessage> getMsgMessageData() {
		return msgMessageData;
	}

	public void setMsgMessageData(List<QBaseMSGMessage> msgMessageData) {
		this.msgMessageData = msgMessageData;
	}

	public QMSGMessage(String msg_type) {
		super(MESSAGE_TYPE);
		//this.msgMessageData = new QBaseMSGMessage();
	}

	@Override
	public String toString() {
		return "QMSGMessage [msgMessageData=" + msgMessageData + "]";
	}
	
	
	
}
