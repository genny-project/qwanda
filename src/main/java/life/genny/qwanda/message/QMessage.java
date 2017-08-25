package life.genny.qwanda.message;

import java.io.Serializable;

public abstract class QMessage implements Serializable , QMessageIntf{

	@Override
	public String toString() {
		return "QMessage [msg_type=" + msg_type + "]";
	}

	private String msg_type;
	
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
		private Long id;
		private String value;
		
		public MessageData(String code){
			this.code = code;
		}
		
	}
}
