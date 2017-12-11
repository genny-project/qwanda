package life.genny.qwanda.message;

public class QDataDeleteMessage extends QDataMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Long[] ids;
	
	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public QDataDeleteMessage(String dataType,Long[] ids) {
		super(dataType);
		this.ids = ids;
		setDelete(true);
		// TODO Auto-generated constructor stub
	}
	
	
}
