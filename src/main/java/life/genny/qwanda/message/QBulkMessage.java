package life.genny.qwanda.message;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;

public class QBulkMessage implements Serializable {

	
	  @Expose
	  private String token;
	  
	  @Expose
	  private String data_type=	 QBulkMessage.class.getSimpleName();

	  
	  @Expose
	  private QDataBaseEntityMessage[] messages;
	  
	  @Expose
	  private String[] recipientCodeArray;

	
	private QBulkMessage(){}
	public QBulkMessage(QDataBaseEntityMessage[] qMessages){
		this.messages = qMessages;
	}
	
	public QBulkMessage(QDataBaseEntityMessage qMessage){
		messages = new QDataBaseEntityMessage[1];
		this.messages[0] = qMessage;
	}
	
	public QBulkMessage(List<QDataBaseEntityMessage> qMessages){
		this.messages = new QDataBaseEntityMessage[qMessages.size()];
		this.messages = qMessages.toArray(this.messages);
	}

	public void add(List<QDataBaseEntityMessage> qMessageList) {
		int newSize = messages.length+qMessageList.size();
		    QDataBaseEntityMessage[] extended = new QDataBaseEntityMessage[newSize];

		   for (int index=messages.length;index<newSize;index++) {
			   extended[index] = qMessageList.get(index-messages.length);
		   }

		    System.arraycopy(messages, 0, extended, 0, messages.length);

	}
	
	public void add(QDataBaseEntityMessage qMessage) {
		int newSize = messages.length+1;
	    QDataBaseEntityMessage[] extended = new QDataBaseEntityMessage[newSize];

		   extended[newSize-1] = qMessage;

	    System.arraycopy(messages, 0, extended, 0, messages.length);


	}
	
	/**
	 * @return the messages
	 */
	public QDataBaseEntityMessage[] getMessages() {
		return messages;
	}
	/**
	 * @param messages the messages to set
	 */
	public void setMessages(QDataBaseEntityMessage[] messages) {
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
