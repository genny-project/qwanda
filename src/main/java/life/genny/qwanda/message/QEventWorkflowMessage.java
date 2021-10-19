package life.genny.qwanda.message;

public class QEventWorkflowMessage extends QEventMessage {
	
	private static final long serialVersionUID = 1L;

	private static final String EVENT_TYPE_WORKFLOW = "WF";

	public QEventWorkflowMessage(String fieldCode) {
		super(EVENT_TYPE_WORKFLOW, fieldCode);
	}

}
