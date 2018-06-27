package life.genny.qwanda.message;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class QCmdTableMessage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Expose
	private String title;
	
	@Expose
	private String icon;
	
	@Expose
	private String code;
	
	@Expose
	private String color;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QCmdTableMessage [title=" + title + ", icon=" + icon + ", code=" + code + ", color=" + color + "]";
	}

	public QCmdTableMessage(String title, String code) {
		super();
		this.title = title;
		this.code = code;
	}

	public QCmdTableMessage(String title, String icon, String code, String color) {
		super();
		this.title = title;
		this.icon = icon;
		this.code = code;
		this.color = color;
	}

	public QCmdTableMessage(String code) {
		super();
		this.code = code;
	}
	
}
