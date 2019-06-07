package life.genny.qwanda.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.gson.annotations.Expose;

import javax.persistence.Embedded;
import javax.validation.Valid;

import life.genny.qwanda.ContextList;

public class QCmdMessage extends QMessage {

	private static final String MESSAGE_TYPE = "CMD_MSG";
	@Expose
	private String cmd_type;
	@Expose
	private String code;

	@Embedded
	@Valid
	@JsonInclude(Include.NON_NULL)
	@Expose
	private ContextList contextList;

	public QCmdMessage(String cmd_type, String code) {
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

	/**
	 * @return the contextList
	 */
	public ContextList getContextList() {
		return contextList;
	}

	/**
	 * @param contextList the contextList to set
	 */
	public void setContextList(final ContextList contextList) {
		this.contextList = contextList;
	}

}
