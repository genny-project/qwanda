package life.genny.qwanda.message;

public class QCmdViewFormMessage extends QCmdViewMessage {

	private static final String CODE = "FORM_VIEW";
	
	public QCmdViewFormMessage(String root) {
		super(CODE, root);
	}

	private static final long serialVersionUID = 1L;
}