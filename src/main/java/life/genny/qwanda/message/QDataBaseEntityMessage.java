package life.genny.qwanda.message;


import com.google.gson.annotations.Expose;

import life.genny.qwanda.WeightedItem;
import life.genny.qwanda.entity.BaseEntity;

public class QDataBaseEntityMessage extends QDataMessage {
  private static final long serialVersionUID = 1L;
  @Expose
  private BaseEntity[] items;
  
  @Expose
  private WeightedItem[] weightedItems;
  
  private static final String DATATYPE_BASEENTITY = BaseEntity.class.getSimpleName();
  @Expose
  private String parentCode;
  @Expose
  private String linkCode;
  @Expose
  private Long total = -1L;
  @Expose
  private Long returnCount;

  public QDataBaseEntityMessage(final BaseEntity item, final String alias) {
	    super(DATATYPE_BASEENTITY);
	    items = new BaseEntity[1];
	    items[0] = item;
	    setItems(items);
	    setAliasCode(alias);
	    setTotal(1L);
	  }

  public QDataBaseEntityMessage(final BaseEntity item) {
	   this(item,null);
	  }
  
  public QDataBaseEntityMessage(final BaseEntity[] items) {
    super(DATATYPE_BASEENTITY);
    setItems(items);
    setTotal(-1L);
  }

  public QDataBaseEntityMessage(final BaseEntity[] items, final String parentCode,
      final String linkCode) {
    this(items, parentCode, linkCode, -1L);
  }

  public QDataBaseEntityMessage(final BaseEntity[] items, final String parentCode,
      final String linkCode, final Long total) {
    super(DATATYPE_BASEENTITY);
    setItems(items);
    this.linkCode = linkCode;
    this.parentCode = parentCode;
    setTotal(total);
  }
  
  public QDataBaseEntityMessage(final WeightedItem item, final String alias) {
	    super(DATATYPE_BASEENTITY);
	    weightedItems = new WeightedItem[1];
	    weightedItems[0] = item;
	    setAliasCode(alias);
	    setTotal(1L);
	  }

public QDataBaseEntityMessage(final WeightedItem item) {
	   this(item,null);
	  }

public QDataBaseEntityMessage(final WeightedItem[] items) {
  super(DATATYPE_BASEENTITY);
  setWeightedItems(items);
  setTotal(-1L);
}

public QDataBaseEntityMessage(final WeightedItem[] items, final String parentCode,
    final String linkCode) {
  this(items, parentCode, linkCode, -1L);
}

public QDataBaseEntityMessage(final WeightedItem[] items, final String parentCode,
    final String linkCode, final Long total) {
  super(DATATYPE_BASEENTITY);
  setWeightedItems(items);
  this.linkCode = linkCode;
  this.parentCode = parentCode;
  setTotal(total);
}
  

  public BaseEntity[] getItems() {
    return items;
  }

  public void setItems(final BaseEntity[] items) {
    this.items = items;
    setReturnCount(new Long(items.length));
  }

  /**
   * @return the parentCode
   */
  public String getParentCode() {
    return parentCode;
  }

  /**
   * @param parentCode the parentCode to set
   */
  public void setParentCode(final String parentCode) {
    this.parentCode = parentCode;
  }

  /**
   * @return the linkCode
   */
  public String getLinkCode() {
    return linkCode;
  }

  /**
   * @param linkCode the linkCode to set
   */
  public void setLinkCode(final String linkCode) {
    this.linkCode = linkCode;
  }

  /**
   * @return the total
   */
  public Long getTotal() {
    return total;
  }

  /**
   * @param total the total to set
   */
  public void setTotal(final Long total) {
    this.total = total;
  }

  /**
   * @return the returnCount
   */
  public Long getReturnCount() {
    return returnCount;
  }

  /**
   * @param returnCount the returnCount to set
   */
  public void setReturnCount(final Long returnCount) {
    this.returnCount = returnCount;
  }

/**
 * @return the weightedItems
 */
public WeightedItem[] getWeightedItems() {
	return weightedItems;
}

/**
 * @param weightedItems the weightedItems to set
 */
public void setWeightedItems(WeightedItem[] weightedItems) {
	this.weightedItems = weightedItems;
}



}
