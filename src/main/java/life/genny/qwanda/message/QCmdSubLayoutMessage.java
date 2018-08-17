package life.genny.qwanda.message;

import com.google.gson.annotations.Expose;

public class QCmdSubLayoutMessage extends QCmdMessage {

	/**
	   * 
	   */
	private static final long serialVersionUID = 1L;

	private static final String CMD_SUBLAYOUT = "CMD_SUBLAYOUT";
	private static final String CMD_POPUP = "CMD_POPUP";
	/*
	 * @Expose private String data;
	 * 
	 * @Expose private Boolean visible;
	 */

	@Expose
	private String layoutCode;

	@Expose
	private String root;

	@Expose
	private String items;
	
	@Expose
	private Boolean isPopup;

	public QCmdSubLayoutMessage(final String layoutCode, Boolean isPopup) {
		super(isPopup ? CMD_POPUP : CMD_SUBLAYOUT, layoutCode);
	}
	
	public QCmdSubLayoutMessage(final String layoutCode, String items, Boolean isPopup, String root) {
		super(isPopup ? CMD_POPUP : CMD_SUBLAYOUT, layoutCode);
		setItems(items);
		setRoot(root);
	}

	/**
	 * @return the layoutCode
	 */
	public String getLayoutCode() {
		return layoutCode;
	}

	/**
	 * @param layoutCode the layoutCode to set
	 */
	public void setLayoutCode(String layoutCode) {
		this.layoutCode = layoutCode;
	}

	/**
	 * @return the root
	 */
	public String getRoot() {
		return root;
	}

	/**
	 * @param root the root to set
	 */
	public void setRoot(String root) {
		this.root = root;
	}

	/**
	 * @return the items
	 */
	public String getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(String items) {
		this.items = items;
	}

	/**
	 * @return the isPopup
	 */
	public Boolean getIsPopup() {
		return isPopup;
	}

	/**
	 * @param isPopup the isPopup to set
	 */
	public void setIsPopup(Boolean isPopup) {
		this.isPopup = isPopup;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QCmdSubLayoutMessage [layoutCode=" + layoutCode + ", root=" + root + ", items=" + items + ", isPopup="
				+ isPopup + "]";
	}


}
