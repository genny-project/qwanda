package life.genny.qwanda.message;
import life.genny.qwanda.utils.OutputParam;

public class QEventWorkflowMessage extends QEventMessage {
	
	private static final long serialVersionUID = 1L;

	private static final String EVENT_TYPE_WORKFLOW = "WF";

	OutputParam outputParam;

	public QEventWorkflowMessage(String fieldCode, OutputParam outputParam) {
		super(EVENT_TYPE_WORKFLOW, fieldCode);
		this.outputParam = outputParam;
	}

	public OutputParam getOutputParam() {
		return outputParam;
	}

	@Override
	public String toString() {
		return "QEventWorkflowMessage{" +
				"data=" + data +
				'}';
	}


}
