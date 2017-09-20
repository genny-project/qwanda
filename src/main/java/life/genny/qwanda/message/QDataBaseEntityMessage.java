package life.genny.qwanda.message;


import life.genny.qwanda.entity.BaseEntity;

public class QDataBaseEntityMessage extends QDataMessage {
  private static final long serialVersionUID = 1L;
  private BaseEntity[] items;
  private static final String DATATYPE_BASEENTITY = BaseEntity.class.getSimpleName();
  private String parentCode;
  private String linkCode;

  public QDataBaseEntityMessage(final BaseEntity[] items) {
    super(DATATYPE_BASEENTITY);
    setItems(items);

  }

  public QDataBaseEntityMessage(final BaseEntity[] items, final String parentCode,
      final String linkCode) {
    super(DATATYPE_BASEENTITY);
    setItems(items);
    this.linkCode = linkCode;
    this.parentCode = parentCode;
  }

  public BaseEntity[] getItems() {
    return items;
  }

  public void setItems(final BaseEntity[] items) {
    this.items = items;
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



}
