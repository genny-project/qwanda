package life.genny.qwanda.message;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import java.util.HashMap;


public class QCmdTabViewMessage extends QCmdMessage {
  
  private static final String CMD_TYPE = "CMD_VIEW";
  private static final String VIEW_TYPE = "TAB_VIEW";
  private static final long serialVersionUID = 1L;

  @Expose 
  private QTabView[] views;

  public QCmdTabViewMessage(final QTabView[] views) {
    super(CMD_TYPE, VIEW_TYPE);
    this.views = views;
  }

  public void setViews(QTabView[] views) {
    this.views = views;
  }

  public QTabView[] getViews() {
    return this.views;
  }
}
