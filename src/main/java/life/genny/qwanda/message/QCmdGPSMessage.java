package life.genny.qwanda.message;

public class QCmdGPSMessage extends QCmdMessage{
	private static final String CMD_TYPE = "CMD_GPS";
	private static final String CODE = "REQUEST";

	
	public QCmdGPSMessage(String redirectUrl) {
		super(CMD_TYPE, CODE);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
}
