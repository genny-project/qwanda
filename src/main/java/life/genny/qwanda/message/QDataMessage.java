package life.genny.qwanda.message;

abstract class QDataMessage extends QMessage implements QDataMessageIntf {

	public String getData_type() {
		return data_type;
	}





	public void setData_type(String data_type) {
		this.data_type = data_type;
	}



	/**
	 * 
	 */
	private static final String MESSAGE_TYPE = "DATA_MSG";
	private String data_type;
    private Boolean delete = false;
	
	

	
	public QDataMessage(String data_type){
		super(MESSAGE_TYPE);
		this.data_type = data_type;
	
	}





	public Boolean getDelete() {
		return delete;
	}





	public void setDelete(Boolean delete) {
		this.delete = delete;
	}





	@Override
	public String toString() {
		return "QDataMessage [data_type=" + data_type + ", delete=" + delete + "]";
	}

	
	
}
