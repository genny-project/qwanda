package life.genny.qwanda.message;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;

public class QCmdViewMessage extends QCmdMessage {
  private static final String CMD_TYPE = "CMD_VIEW";
  
  @Expose
  private String root;
  @Expose
  private String view_type;
  @Expose
  private String data;


  public QCmdViewMessage(final String view_type, final String root) {
    super(CMD_TYPE, view_type);
    setRoot(root);
    setView_type(view_type);
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

  public String getRoot() {
	return root;
  }
	
public void setRoot(String root) {
	this.root = root;
}

public String getView_type() {
	return view_type;
}

public void setView_type(String view_type) {
	this.view_type = view_type;
}

  



}
