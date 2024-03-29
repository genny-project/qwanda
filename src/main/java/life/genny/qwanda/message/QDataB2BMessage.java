package life.genny.qwanda.message;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

import life.genny.qwanda.AttributeCodeValueString;
import life.genny.qwanda.GennyItem;

public class QDataB2BMessage extends QDataMessage {
	
	
	private static final long serialVersionUID = 1L;

	 @Expose
	 @JsonProperty
	  private GennyItem[] items;
	  private static final String DATATYPE_ITEM = GennyItem.class.getSimpleName();

	  // For json parameters
	  public QDataB2BMessage() {
		  super(DATATYPE_ITEM);
	  }


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
	
	  public String getValue(final String attributeCode) {
		  AttributeCodeValueString value = null;
		  if ((items != null) && (items.length>0) ) {
			for (GennyItem item : items) {
				Optional<AttributeCodeValueString> acvs = item.get(attributeCode);
					
				if (acvs.isPresent()) {
					// break out upon first match
					return acvs.get().getValue();
				}
			}
		  }
		  return null;
	  }
	  
}
