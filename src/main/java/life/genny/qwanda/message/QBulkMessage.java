package life.genny.qwanda.message;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;

public class QBulkMessage implements Serializable {

	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Expose
	  private String token;
	  
	  @Expose
	  private String data_type=	 QBulkMessage.class.getSimpleName();

	  
	  @Expose
	  private QMessage[] messages;
	  
	  @Expose
	  private String[] recipientCodeArray;

	
	public QBulkMessage(){
		messages = new QMessage[0];
	}
	
	public QBulkMessage(QMessage[] qMessages){
		this.messages = qMessages;
	}
	
	public QBulkMessage(QMessage qMessage){
		messages = new QMessage[1];
		this.messages[0] = qMessage;
	}
	
	public QBulkMessage(List<QMessage> qMessages){
		this.messages = new QMessage[qMessages.size()];
		this.messages = qMessages.toArray(this.messages);
	}

	public void add(QMessage[] qMessageArray) {
		int newSize = messages.length+qMessageArray.length;
		    QMessage[] extended = new QMessage[newSize];

		    System.arraycopy(qMessageArray, 0, extended, messages.length, qMessageArray.length);

		    System.arraycopy(messages, 0, extended, 0, messages.length);
		    setMessages(extended);
	}
	public void add(List<QMessage> qMessageList) {
		int newSize = messages.length+qMessageList.size();
		    QMessage[] extended = new QMessage[newSize];

		   for (int index=messages.length;index<newSize;index++) {
			   extended[index] = qMessageList.get(index-messages.length);
		   }

		    System.arraycopy(messages, 0, extended, 0, messages.length);
		    setMessages(extended);

	}
	
	public void add(QDataBaseEntityMessage qMessage) {
		int newSize = messages.length+1;
	    QMessage[] extended = new QMessage[newSize];

		 extended[newSize-1] = qMessage;

	    System.arraycopy(messages, 0, extended, 0, messages.length);
	    setMessages(extended);
	}
	
	/**
	 * @return the messages
	 */
	public QMessage[] getMessages() {
		return messages;
	}
	/**
	 * @param messages the messages to set
	 */
	public void setMessages(QMessage[] messages) {
		this.messages = messages;
	}
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * @return the data_type
	 */
	public String getData_type() {
		return data_type;
	}
	/**
	 * @param data_type the data_type to set
	 */
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
	/**
	 * @return the recipientCodeArray
	 */
	public String[] getRecipientCodeArray() {
		return recipientCodeArray;
	}
	/**
	 * @param recipientCodeArray the recipientCodeArray to set
	 */
	public void setRecipientCodeArray(String[] recipientCodeArray) {
		this.recipientCodeArray = recipientCodeArray;
	}
	
	
}
