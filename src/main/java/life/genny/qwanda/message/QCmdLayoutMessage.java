package life.genny.qwanda.message;

public class QCmdLayoutMessage extends QCmdMessage{
	private static final String CMD_TYPE = "CMD_LAYOUT";
	private String data;
	
	public QCmdLayoutMessage(final String layoutCode, final String layout) {
		super(CMD_TYPE, layoutCode);
	    setData(layout);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

  /**
   * @return the data
   */
  public String getData() {
    return data;
  }

  /**
   * @param data the data to set
   */
  private void setData(final String data) {
    this.data = data;
  }


	
}
