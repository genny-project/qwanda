package life.genny.qwanda.message;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

import life.genny.qwanda.Ask;
import life.genny.qwanda.entity.BaseEntity;

public class QDataAskMessage extends QDataMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Expose
	private Ask[] items;
	private static final String DATATYPE_ASK = Ask.class.getSimpleName();

	public QDataAskMessage(Ask[] items) {
		super(DATATYPE_ASK);
		if ((items == null)||(items.length == 0)) {
			setItems(new Ask[0]);
		} else {
			setItems(items);
		}

	}
	
	public QDataAskMessage(Ask ask) {
		super(DATATYPE_ASK);
		Ask[] asks = new Ask[1];
		asks[0] = ask;
		setItems(asks);
	}

	public Ask[] getItems() {
		return this.items;
	}

	public void setItems(Ask[] asks) {
		if ((items == null)||(items.length == 0)) {
			this.items = new Ask[0];
		} else {
			this.items = asks;
		}

	}

}
