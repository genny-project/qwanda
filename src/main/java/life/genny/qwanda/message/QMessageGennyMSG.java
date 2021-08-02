package life.genny.qwanda.message;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.google.gson.annotations.Expose;
import java.util.concurrent.CopyOnWriteArrayList;

import life.genny.qwanda.entity.BaseEntity;

public class QMessageGennyMSG extends QMessage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String MESSAGE_TYPE = "MSG_MESSAGE";
	
	@Expose
	private String templateCode;
	
	@Expose
	private QBaseMSGMessageType[] messageTypeArr;
	
	@Expose
	private String[] recipientArr;
	
	@Expose
	private Map<String, String> messageContextMap;
	
	@Expose
	private List<QBaseMSGAttachment> attachmentList;
	
	@Expose
	private String[] to;
	/**
	 * @return the templateCode
	 */
	public String getTemplateCode() {
		return templateCode;
	}

	/**
	 * @param templateCode the templateCode to set
	 */
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	/**
	 * @return the messageTypeArr
	 */
	public QBaseMSGMessageType[] getMessageTypeArr() {
		return messageTypeArr;
	}

	/**
	 * @param messageTypeArr the messageTypeArr to set
	 */
	public void setMessageTypeArr(QBaseMSGMessageType[] messageTypeArr) {
		this.messageTypeArr = messageTypeArr;
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
	
	/**
	 * @return the attachmentList
	 */
	public List<QBaseMSGAttachment> getAttachmentList() {
		return attachmentList;
	}

	/**
	 * @param attachmentList the attachmentList to set
	 */
	public void setAttachmentList(List<QBaseMSGAttachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

	/**
	 * @return the to
	 */
	public String[] getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(String[] to) {
		this.to = to;
	}

	public QMessageGennyMSG() {
		super("COM_MSG");
		this.messageTypeArr = new QBaseMSGMessageType[0];
		this.messageContextMap = new HashMap<String, String>();
		this.recipientArr = new String[0];
	}

	public QMessageGennyMSG(String templateCode) {
		super("COM_MSG");
		this.templateCode = templateCode;
		this.messageTypeArr = new QBaseMSGMessageType[0];
		this.messageContextMap = new HashMap<String, String>();
		this.recipientArr = new String[0];
	}

	public QMessageGennyMSG(QBaseMSGMessageType messageType) {
		super("COM_MSG");
		this.messageTypeArr = new QBaseMSGMessageType[]{ messageType };
		this.messageContextMap = new HashMap<String, String>();
		this.recipientArr = new String[0];
	}

	public QMessageGennyMSG(String msg_type, QBaseMSGMessageType[] messageType, String templateCode, Map<String, String> contextMap, String[] recipientArr) {
		super(msg_type);
		this.templateCode = templateCode;
		this.messageTypeArr = messageType;
		this.messageContextMap = contextMap;
		this.recipientArr = recipientArr;
	}
	
	
	public QMessageGennyMSG(String msg_type, QBaseMSGMessageType[] messageType, String templateCode, Map<String, String> contextMap, String[] recipientArr, List<QBaseMSGAttachment> attachmentList) {
		super(msg_type);
		this.templateCode = templateCode;
		this.messageTypeArr = messageType;
		this.messageContextMap = contextMap;
		this.recipientArr = recipientArr;
		this.attachmentList = attachmentList;
	}
	
	

	public QMessageGennyMSG(String msg_type, String templateCode, QBaseMSGMessageType[] messageTypeArr,
			Map<String, String> messageContextMap, List<QBaseMSGAttachment> attachmentList, String[] to) {
		super(msg_type);
		this.templateCode = templateCode;
		this.messageTypeArr = messageTypeArr;
		this.messageContextMap = messageContextMap;
		this.attachmentList = attachmentList;
		this.to = to;
	}
	
	

	public QMessageGennyMSG(String msg_type, String templateCode, QBaseMSGMessageType[] messageTypeArr,
			Map<String, String> messageContextMap, String[] to) {
		super(msg_type);
		this.templateCode = templateCode;
		this.messageTypeArr = messageTypeArr;
		this.messageContextMap = messageContextMap;
		this.to = to;
	}

	public void addMessageType(QBaseMSGMessageType messageType) {
		
		List<QBaseMSGMessageType> list = this.getMessageTypeArr() != null ? new CopyOnWriteArrayList<>(Arrays.asList(this.getMessageTypeArr())) : new CopyOnWriteArrayList<>();
		list.add(messageType);
		this.setMessageTypeArr(list.toArray(new QBaseMSGMessageType[0]));
	}

	public void addRecipient(BaseEntity recipient) {
		addRecipient("[\""+recipient.getCode()+"\"]");
	}

	public void addContext(String key, Object value) {
		if (value.getClass().equals(BaseEntity.class)) {
			this.messageContextMap.put(key, ((BaseEntity) value).getCode());
		} else {
			this.messageContextMap.put(key, value.toString());
		}
	}

	public void addRecipient(String recipient) {
		
		List<String> list = this.getRecipientArr() != null ? new CopyOnWriteArrayList<>(Arrays.asList(this.getRecipientArr())) : new CopyOnWriteArrayList<>();
		list.add(recipient);
		this.setRecipientArr(list.toArray(new String[0]));
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QMessageGennyMSG [templateCode=" + templateCode + ", messageTypeArr=" + messageTypeArr
				+ ", recipientArr=" + Arrays.toString(recipientArr) + ", messageContextMap=" + messageContextMap
				+ ", attachmentList=" + attachmentList + ", to=" + Arrays.toString(to) + "]";
	}

}
