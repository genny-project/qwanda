package life.genny.qwanda.message;

abstract class QDataMessage extends QMessage implements QDataMessageIntf {

  @Override
  public String getData_type() {
    return data_type;
  }



  public void setData_type(final String data_type) {
    this.data_type = data_type;
  }



  /**
   * 
   */
  private static final String MESSAGE_TYPE = "DATA_MSG";
  private String data_type;
  private Boolean delete = false;
  private String aliasCode;



  public QDataMessage(final String data_type) {
    super(MESSAGE_TYPE);
    this.data_type = data_type;

  }



  @Override
  public Boolean getDelete() {
    return delete;
  }



  public void setDelete(final Boolean delete) {
    this.delete = delete;
  }



  @Override
  public String toString() {
    return "QDataMessage [data_type=" + data_type + ", delete=" + delete + "]";
  }



  /**
   * @return the aliasCode
   */
  public String getAliasCode() {
    return aliasCode;
  }



  /**
   * @param aliasCode the aliasCode to set
   */
  public void setAliasCode(final String aliasCode) {
    this.aliasCode = aliasCode;
  }



}
