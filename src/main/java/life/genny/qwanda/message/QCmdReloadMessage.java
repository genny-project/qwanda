package life.genny.qwanda.message;

public class QCmdReloadMessage extends QCmdMessage {

    private static final String CMD_TYPE = "CMD_RELOAD";
	private static final String CODE = "RELOAD";

	public QCmdReloadMessage() {
		super(CMD_TYPE, CODE);
	}

	private static final long serialVersionUID = 1L;
}
