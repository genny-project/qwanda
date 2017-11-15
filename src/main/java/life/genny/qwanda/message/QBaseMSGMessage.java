package life.genny.qwanda.message;

public class QBaseMSGMessage {
	
	private String source;
	private String target;
	private String priority;
	private String subject;
	private String msgMessageData;
	
	private QBaseMSGMessageType msgMessageType;
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public QBaseMSGMessageType getMsgMessageType() {
		return msgMessageType;
	}
	public void setMsgMessageType(QBaseMSGMessageType msgMessageType) {
		this.msgMessageType = msgMessageType;
	}
	
	public String getMsgMessageData() {
		return msgMessageData;
	}
	public void setMsgMessageData(String msgMessageData) {
		this.msgMessageData = msgMessageData;
	}
	@Override
	public String toString() {
		return "QBaseMSGMessage [source=" + source + ", target=" + target + ", priority=" + priority + ", subject="
				+ subject + ", msgMessageData=" + msgMessageData + ", msgMessageType=" + msgMessageType + "]";
	}
	
	
	
}
