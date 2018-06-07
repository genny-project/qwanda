package life.genny.qwanda.message;

public class QCmdSplitViewMessage extends QCmdMessage {

    private static final String CMD_TYPE = "CMD_VIEW";
	private static final String CODE = "SPLIT_VIEW";
	
	private QCmdViewMessage[] views;
	
	public QCmdSplitViewMessage(QCmdViewMessage[] views) {
		super(CMD_TYPE, CODE);
		this.views = views;
	}
	
	public QCmdViewMessage[] getViews() {
		return this.views;
	}
	
	public void setViews(QCmdViewMessage[] views) {
		this.views = views;
	}

	private static final long serialVersionUID = 1L;
}
