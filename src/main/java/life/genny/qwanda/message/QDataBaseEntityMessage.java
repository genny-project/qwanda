package life.genny.qwanda.message;


import life.genny.qwanda.entity.BaseEntity;

public class QDataBaseEntityMessage extends QDataMessage {
  private static final long serialVersionUID = 1L;
  private BaseEntity[] items;
  private static final String DATATYPE_BASEENTITY = BaseEntity.class.getSimpleName();
  private String parentCode;
  private String linkCode;
  private Long total = -1L;
  private Long returnCount;
  private String alias;

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
   * @return the alias
   */
  public String getAlias() {
    return alias;
  }

  /**
   * @param alias the alias to set
   */
  public void setAlias(final String alias) {
    this.alias = alias;
  }



}
