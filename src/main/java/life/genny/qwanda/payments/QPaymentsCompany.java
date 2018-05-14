package life.genny.qwanda.payments;

import com.google.gson.annotations.Expose;

public class QPaymentsCompany {
	
	/**
	 * company id
	 */
	@Expose
	private String id;

	/**
	 * Name of company
	 */
	@Expose
	private String name;
	
	/**
	 * Legal name of company
	 */
	@Expose
	private String legalName;
	
	/**
	 * ACN/ABN number of company
	 */
	@Expose
	private String taxNumber;
	
	/**
	 * 
	 */
	@Expose
	private Boolean chargesTax;  
	
	/**
	 * Company location info
	 */
	@Expose
	private QPaymentsLocationInfo location;
	
	/**
	 * company user info
	 */
	@Expose
	private QPaymentsUser user;
	
	/**
	 * company contact info
	 */
	@Expose
	private QPaymentsCompanyContactInfo contactInfo;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the legalName
	 */
	public String getLegalName() {
		return legalName;
	}

	/**
	 * @param legalName the legalName to set
	 */
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	/**
	 * @return the taxNumber
	 */
	public String getTaxNumber() {
		return taxNumber;
	}

	/**
	 * @param taxNumber the taxNumber to set
	 */
	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}

	/**
	 * @return the chargesTax
	 */
	public Boolean getChargesTax() {
		return chargesTax;
	}

	/**
	 * @param chargesTax the chargesTax to set
	 */
	public void setChargesTax(Boolean chargesTax) {
		this.chargesTax = chargesTax;
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

	/**
	 * @return the user
	 */
	public QPaymentsUser getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(QPaymentsUser user) {
		this.user = user;
	}

	/**
	 * @return the contactInfo
	 */
	public QPaymentsCompanyContactInfo getContactInfo() {
		return contactInfo;
	}

	/**
	 * @param contactInfo the contactInfo to set
	 */
	public void setContactInfo(QPaymentsCompanyContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}

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
	 * @param name
	 * @param legalName
	 * @param taxNumber
	 * @param chargesTax
	 * @param location
	 * @param user
	 * @param contactInfo
	 * This constructor is used for payments-company creation
	 */
	public QPaymentsCompany(String name, String legalName, String taxNumber, Boolean chargesTax,
			QPaymentsLocationInfo location, QPaymentsUser user, QPaymentsCompanyContactInfo contactInfo) {
		super();
		this.name = name;
		this.legalName = legalName;
		this.taxNumber = taxNumber;
		this.chargesTax = chargesTax;
		this.location = location;
		this.user = user;
		this.contactInfo = contactInfo;
	}

	/**
	 * 
	 */
	public QPaymentsCompany() {
		super();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QPaymentsCompany [id=" + id + ", name=" + name + ", legalName=" + legalName + ", taxNumber=" + taxNumber
				+ ", chargesTax=" + chargesTax + ", location=" + location + ", user=" + user + ", contactInfo="
				+ contactInfo + "]";
	}

	/**
	 * @param id
	 */
	public QPaymentsCompany(String id) {
		super();
		this.id = id;
	}

	
}
