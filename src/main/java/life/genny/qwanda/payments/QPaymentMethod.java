package life.genny.qwanda.payments;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class QPaymentMethod implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum PaymentType {
		CARD,
		BANK_ACCOUNT
	}
	
	
	/* type of payment method */
	@Expose
	private PaymentType type;
		
	/* Credit card number */
	@Expose
	private String number;
	
	/* BSB of bank account */
	@Expose
	private String bsb;
	
	/* payment method ID */
	@Expose
	private String id;
	
	/* account number of bank account */
	@Expose
	private String accountNumber;
	
	
	public QPaymentMethod() {
		super();
	}
	

	/**
	 * @return the type
	 */
	public PaymentType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(PaymentType type) {
		this.type = type;
	}


	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the bsb
	 */
	public String getBsb() {
		return bsb;
	}

	/**
	 * @param bsb the bsb to set
	 */
	public void setBsb(String bsb) {
		this.bsb = bsb;
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
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}


	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PaymentMethod [type=" + type + ", number=" + number + ", bsb=" + bsb + ", id=" + id + ", accountNumber="
				+ accountNumber + "]";
	}


	public QPaymentMethod(PaymentType type, String number, String bsb, String id, String accountNumber) {
		super();
		this.type = type;
		this.number = number;
		this.bsb = bsb;
		this.id = id;
		this.accountNumber = accountNumber;
	}


}
