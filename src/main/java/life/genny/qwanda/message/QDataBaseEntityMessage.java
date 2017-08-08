package life.genny.qwanda.message;


import life.genny.qwanda.entity.BaseEntity;

public class QDataBaseEntityMessage extends QDataMessage{
	private static final long serialVersionUID = 1L;
	private BaseEntity[] items;
	private static final String DATATYPE_BASEENTITY = BaseEntity.class.getSimpleName();

	public QDataBaseEntityMessage(BaseEntity[] items) {
		super(DATATYPE_BASEENTITY);
		setItems(items);
	}

	public BaseEntity[] getItems() {
		return items;
	}

	public void setItems(BaseEntity[] items) {
		this.items = items;
	}

}
