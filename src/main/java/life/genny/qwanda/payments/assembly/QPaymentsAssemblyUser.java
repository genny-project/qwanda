package life.genny.qwanda.payments.assembly;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

import life.genny.qwanda.payments.QPaymentsLocationInfo;
import life.genny.qwanda.payments.QPaymentsUserContactInfo;

public class QPaymentsAssemblyUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Expose
	private String id;
	
	@Expose
	private String fullName;
	
	@Expose
	private QPaymentsUserContactInfo contactInfo;
	
	@Expose
	private QPaymentsLocationInfo location;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the contactInfo
	 */
	public QPaymentsUserContactInfo getContactInfo() {
		return contactInfo;
	}

	/**
	 * @param contactInfo the contactInfo to set
	 */
	public void setContactInfo(QPaymentsUserContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}

	/**
	 * @return the location
	 */
	public QPaymentsLocationInfo getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(QPaymentsLocationInfo location) {
		this.location = location;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QPaymentsAssemblyBuyer [id=" + id + ", fullName=" + fullName + ", contactInfo=" + contactInfo
				+ ", location=" + location + "]";
	}


}
