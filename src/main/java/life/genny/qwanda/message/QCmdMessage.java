package life.genny.qwanda.message;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class QCmdMessage extends QMessage{
	
	private static final String MESSAGE_TYPE = "CMD_MSG";
	@Expose
	private String cmd_type;
	@Expose
	private String code;
	
	

	
	public QCmdMessage(String cmd_type, String code){
		super(MESSAGE_TYPE);
		this.code = code;
		this.cmd_type = cmd_type;
	}
	public String getCmd_type() {
		return cmd_type;
	}

	public void setCmd_type(String cmd_type) {
		this.cmd_type = cmd_type;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
