package life.genny.qwanda.message;

import life.genny.qwanda.Answer;

public class QDataAnswerMessage extends QDataMessage {

	private static final long serialVersionUID = 1L;
	private Answer[] items;
	private static final String DATATYPE_ANSWER = Answer.class.getSimpleName();

	public QDataAnswerMessage(Answer[] items) {
		super(DATATYPE_ANSWER);
		setItems(items);
	}

	public Answer[] getItems() {
		return items;
	}

	public void setItems(Answer[] items) {
		this.items = items;
	}
}
