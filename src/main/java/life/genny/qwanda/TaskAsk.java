package life.genny.qwanda;

import java.io.Serializable;

public class TaskAsk implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Ask ask;
	private String formCode;
	private Boolean answered = false;
	private Boolean tableRow = false;
	private Boolean formTrigger = false;
	
	public TaskAsk(Ask ask, String formCode, Boolean answered, Boolean tableRow, Boolean formTrigger) {
		super();
		this.ask = ask;
		this.formCode = formCode;
		this.answered = answered;
		this.tableRow = tableRow;
		this.formTrigger = formTrigger;
	}

	/**
	 * @return the ask
	 */
	public Ask getAsk() {
		return ask;
	}

	/**
	 * @param ask the ask to set
	 */
	public void setAsk(Ask ask) {
		this.ask = ask;
	}

	/**
	 * @return the formCode
	 */
	public String getFormCode() {
		return formCode;
	}

	/**
	 * @param formCode the formCode to set
	 */
	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}

	/**
	 * @return the answered
	 */
	public Boolean getAnswered() {
		return answered;
	}

	/**
	 * @param answered the answered to set
	 */
	public void setAnswered(Boolean answered) {
		this.answered = answered;
	}

	/**
	 * @return the tableRow
	 */
	public Boolean getTableRow() {
		return tableRow;
	}

	/**
	 * @param tableRow the tableRow to set
	 */
	public void setTableRow(Boolean tableRow) {
		this.tableRow = tableRow;
	}

	/**
	 * @return the formTrigger
	 */
	public Boolean getFormTrigger() {
		return formTrigger;
	}

	/**
	 * @param formTrigger the formTrigger to set
	 */
	public void setFormTrigger(Boolean formTrigger) {
		this.formTrigger = formTrigger;
	}

	@Override
	public String toString() {
		return "TaskAsk [ask=" + ask + ", formCode=" + formCode + ", answered=" + answered + ", tableRow=" + tableRow
				+ ", formTrigger=" + formTrigger + "]";
	}
	
	
	

}
