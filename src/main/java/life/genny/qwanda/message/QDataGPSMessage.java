package life.genny.qwanda.message;

import life.genny.qwanda.GPS;

public class QDataGPSMessage extends QDataMessage {

	private static final long serialVersionUID = 1L;
	private GPS[] items;
	private static final String DATATYPE_GPS = GPS.class.getSimpleName();

	public QDataGPSMessage(final GPS[] items) {
		super(DATATYPE_GPS);
		setItems(items);
	}

	public GPS[] getItems() {
		return items;
	}

	public void setItems(final GPS[] items) {
		this.items = items;
	}
}
