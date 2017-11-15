package life.genny.qwanda.message;

public class QMSGMessage extends QMessage {
	
	private static final String MESSAGE_TYPE = "MSG_MESSAGE";
	private QBaseMSGMessage msgMessageData;
	
	public QBaseMSGMessage getMsgMessageData() {
		return msgMessageData;
	}

	public void setMsgMessageData(QBaseMSGMessage msgMessageData) {
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
