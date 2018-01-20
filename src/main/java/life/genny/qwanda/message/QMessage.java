package life.genny.qwanda.message;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public abstract class QMessage implements Serializable , QMessageIntf{

	@Override
	public String toString() {
		return "QMessage [msg_type=" + msg_type + "]";
	}

	@Expose
	private String msg_type;
	
	  @Expose
	  private String token;
	
	public String getMsg_type() {
		return msg_type;
	}
	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}
	private QMessage(){}
	public QMessage(String msg_type){
		this.msg_type = msg_type;
	}
	
	public class MessageData{
		
		@Override
		public String toString() {
			return " MessageData [code=" + code +"   "+id +"]";
		}
		@Expose
		private String code;
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		@Expose
		private Long id;
		@Expose
		private String value;
		
		public MessageData(String code){
			this.code = code;
		}
		
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
	
	
}
