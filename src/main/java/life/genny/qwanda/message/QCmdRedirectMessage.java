package life.genny.qwanda.message;

public class QCmdRedirectMessage extends QCmdMessage{
	private static final String CMD_TYPE = "CMD_REDIRECT";
	private static final String CODE = "REDIRECT";

	private String redirect_url = "";
	
	public QCmdRedirectMessage(String redirectUrl) {
		super(CMD_TYPE, CODE);
		this.redirect_url = redirectUrl;
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getRedirect_url() {
		return redirect_url;
	}

	public void setRedirect_url(String redirect_url) {
		this.redirect_url = redirect_url;
	}
	
}
