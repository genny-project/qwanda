package life.genny.qwanda.payments;

import com.google.gson.annotations.Expose;

public class QPaymentsUserContactInfo {
	
	@Expose
	private String email;
	
	@Expose
	private String mobile;

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QPaymentsContactInfo [email=" + email + ", mobile=" + mobile + "]";
	}
	

	/**
	 * 
	 */
	public QPaymentsUserContactInfo() {
		super();
	}

	public QPaymentsUserContactInfo(String email, String mobile) {
		super();
		this.email = email;
		this.mobile = mobile;
	}

	public QPaymentsUserContactInfo(String email) {
		super();
		
		if(email != null && !email.trim().isEmpty()) {
			this.email = email;
		} else {
			throw new IllegalArgumentException(String.format("email cannot be [%d]", email));
		}
		
	}


}
