package life.genny.qwanda.message;

import com.google.gson.annotations.Expose;

import life.genny.qwanda.GennyItem;

public class QDataB2BMessage extends QDataMessage {
	
	
	private static final long serialVersionUID = 1L;

	 @Expose
	  private GennyItem[] items;
	  private static final String DATATYPE_ITEM = GennyItem.class.getSimpleName();


	  public QDataB2BMessage(final GennyItem[] items) {
	    super(DATATYPE_ITEM);
	    setItems(items);
	  }

	  public GennyItem[] getItems() {
	    return items;
	  }

	  public void setItems(final GennyItem[] items) {
	    this.items = items;
	  }
	
}
