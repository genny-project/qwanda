package life.genny.qwanda.message;

import life.genny.qwanda.GPS;

public class QCmdGeofenceMessage extends QCmdMessage{
	private static final String CMD_TYPE = "CMD_GPS";
	private static final String CODE = "GEOFENCE";

	//private GPS[] items;
	private GPS gpsLocation;
	double radius;
	private String enterCode;
	private String exitCode;

	
	public QCmdGeofenceMessage(GPS gps, double radius, String enterCode, String exitCode) {
		super(CMD_TYPE, CODE);
		this.gpsLocation = gps;
		this.radius = radius;
		this.enterCode = enterCode;
		this.exitCode = exitCode;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	/**
	 * @return the gpsLocation
	 */
	public GPS getGpsLocation() {
		return gpsLocation;
	}

	/**
	 * @param gpsLocation the gpsLocation to set
	 */
	public void setGpsLocation(GPS gpsLocation) {
		this.gpsLocation = gpsLocation;
	}

	
	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
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
