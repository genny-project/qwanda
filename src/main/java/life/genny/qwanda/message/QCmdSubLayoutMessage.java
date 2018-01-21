package life.genny.qwanda.message;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;

public class QCmdSubLayoutMessage extends QCmdMessage {
  private static final String CMD_TYPE = "CMD_SUBLAYOUT";
  @Expose
  private String data;
  @Expose
  private Boolean visible;



  public QCmdSubLayoutMessage(final String subLayoutCode, final String subLayout) {
    super(CMD_TYPE, subLayoutCode);
    setData(subLayout);
  }

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * @return the data
   */
  public String getData() {
    return data;
  }

  /**
   * @param data the data to set
   */
  private void setData(final String data) {
    this.data = data;
  }

  /**
   * @return the visible
   */
  public Boolean getVisible() {
    return visible;
  }

  /**
   * @param visible the visible to set
   */
  public void setVisible(final Boolean visible) {
    this.visible = visible;
  }



}
