package life.genny.qwanda.message;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

import life.genny.qwanda.Ask;

public class QDataAskMessage extends QDataMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Expose
	private Ask[] items;
	private static final String DATATYPE_ASK = Ask.class.getSimpleName();

	public QDataAskMessage(Ask[] asks) {
		super(DATATYPE_ASK);
		setItems(asks);
	}

	public Ask[] getItems() {
		return this.items;
	}

	public void setItems(Ask[] asks) {
		this.items = asks;
	}

}
