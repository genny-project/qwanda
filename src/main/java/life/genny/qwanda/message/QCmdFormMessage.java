package life.genny.qwanda.message;

public class QCmdFormMessage extends QCmdMessage {

    private static final String CMD_TYPE = "CMD_FORM";
	private static final String CODE = "FORM";
	
	private String questionGroupCode;

	public QCmdFormMessage(String questionGroupCode) {
		super(CMD_TYPE, CODE);
		this.questionGroupCode = questionGroupCode;
	}

	private static final long serialVersionUID = 1L;
}
