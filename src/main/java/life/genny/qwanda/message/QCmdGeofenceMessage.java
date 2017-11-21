package life.genny.qwanda.message;

import life.genny.qwanda.GPS;

public class QCmdGeofenceMessage extends QCmdMessage{
	private static final String CMD_TYPE = "CMD_GPS";
	private static final String CODE = "GEOFENCE";

	private GPS[] items;
	private String enterCode;
	private String exitCode;

	
	public QCmdGeofenceMessage(GPS[] items, String enterCode, String exitCode) {
		super(CMD_TYPE, CODE);
		this.items = items;
		this.enterCode = enterCode;
		this.exitCode = exitCode;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * @return the items
	 */
	public GPS[] getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(GPS[] items) {
		this.items = items;
	}

	/**
	 * @return the enterCode
	 */
	public String getEnterCode() {
		return enterCode;
	}

	/**
	 * @param enterCode the enterCode to set
	 */
	public void setEnterCode(String enterCode) {
		this.enterCode = enterCode;
	}

	/**
	 * @return the exitCode
	 */
	public String getExitCode() {
		return exitCode;
	}

	/**
	 * @param exitCode the exitCode to set
	 */
	public void setExitCode(String exitCode) {
		this.exitCode = exitCode;
	}

	
	
}
