package life.genny.qwanda.message;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public abstract class QMessage implements Serializable, QMessageIntf {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum MsgOption {
		CACHE,  // cache this message as a response to a trigger event
		EXEC,   // execute this 
		EXEC_CACHE, // execute this AND set up as a cached response
		LOCAL,   // This message (if triggered, does not need to be sent through to the back end as well 
		IGNORE   // the front end can ignore and handling of this message (useful for testing)
	}

	@Override
	public String toString() {
		return "QMessage [msg_type=" + msg_type + "],"+option.toString();
	}

	@Expose
	private String msg_type;

	@Expose
	private String token;

	@Expose
	private String option = MsgOption.EXEC.toString();
	
	@Expose
	private String triggerCode;  // This can be used to trigger any option

	public String getMsg_type() {
		return msg_type;
	}

	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}

	private QMessage() {
	}

	public QMessage(String msg_type) {
		this.msg_type = msg_type;
	}

	public class MessageData {

		@Override
		public String toString() {
			return " MessageData [code=" + code + "   " + id + "]";
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

		public MessageData(String code) {
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

	/**
	 * @return the option
	 */
	public String getOption() {
		return option;
	}

	/**
	 * @param option the option to set
	 */
	public void setOption(MsgOption option) {
		this.option = option.toString();
	}

	/**
	 * @return the triggerCode
	 */
	public String getTriggerCode() {
		return triggerCode;
	}

	/**
	 * @param triggerCode the triggerCode to set
	 */
	public void setTriggerCode(String triggerCode) {
		this.triggerCode = triggerCode;
	}

	
	
}
