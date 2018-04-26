package life.genny.qwanda.message;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;

public class QBulkMessage implements Serializable {

	
	  @Expose
	  private String token;
	  
	  private String data_type=	 QBulkMessage.class.getSimpleName();

	  
	  @Expose
	  private QMessage[] messages;
	
	private QBulkMessage(){}
	public QBulkMessage(QMessage[] qMessages){
		this.messages = qMessages;
	}
	
	public QBulkMessage(List<QMessage> qMessages){
		this.messages = new QMessage[qMessages.size()];
		this.messages = qMessages.toArray(this.messages);
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
	
	
}
