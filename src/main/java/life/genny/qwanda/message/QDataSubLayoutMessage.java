package life.genny.qwanda.message;

import com.google.gson.annotations.Expose;

import life.genny.qwanda.QuestionSourceTarget;

public class QDataSubLayoutMessage extends QDataMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Expose
	private String[] items;
	
	private MessageData data;
	;
	private static final String DATATYPE_LAYOUT = "SUB_LAYOUT";

	public QDataSubLayoutMessage(final String code, final String[] layoutItems)
	{
		this(code, layoutItems, "DUMMY");
	}

	public QDataSubLayoutMessage(final String code, final String layoutItem, final String token) {
		super(DATATYPE_LAYOUT);
		this.data.setCode(code);
		String[] layoutItems = new String[1];
		layoutItems[0] = layoutItem;
		this.items = layoutItems;
		setToken(token);
	}
	public QDataSubLayoutMessage(final String code, final String[] layoutItems, final String token) {
		super(DATATYPE_LAYOUT);
		this.data.setCode(code);
		this.items = layoutItems;
		setToken(token);
	}

	/**
	 * @return the items
	 */
	public String[] getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(String[] items) {
		this.items = items;
	}

	/**
	 * @return the data
	 */
	public MessageData getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(MessageData data) {
		this.data = data;
	}



}
