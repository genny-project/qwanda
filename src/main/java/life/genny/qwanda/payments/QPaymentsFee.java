package life.genny.qwanda.payments;

import java.io.Serializable;

import javax.money.NumberValue;

import com.google.gson.annotations.Expose;

public class QPaymentsFee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public enum FEETYPE{
		FIXED(1),
		PERCENTAGE(2),
		PERCENTAGE_WITH_CAP(3),
		PERCENTAGE_WITH_MIN(4);

        private final int value;

        FEETYPE(final int newValue) {
            value = newValue;
        }

        public int getValue() { return value; }
    }
	
	public enum PAYMENT_TO {
		buyer,
		seller,
		cc,
		int_wire;
	}
	
	@Expose
	private String id;
	
	@Expose
	private String name;
	
	/* Type of fee */
	@Expose
	private int type;
	
	@Expose
	private NumberValue amount;
	
	@Expose
	private String cap;
	
	@Expose
	private NumberValue min;
	
	@Expose
	private NumberValue max;
	
	@Expose 
	private PAYMENT_TO to;

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
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the amount
	 */
	public NumberValue getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(NumberValue amount) {
		this.amount = amount;
	}

	/**
	 * @return the cap
	 */
	public String getCap() {
		return cap;
	}

	/**
	 * @param cap the cap to set
	 */
	public void setCap(String cap) {
		this.cap = cap;
	}

	/**
	 * @return the min
	 */
	public NumberValue getMin() {
		return min;
	}

	/**
	 * @param min the min to set
	 */
	public void setMin(NumberValue min) {
		this.min = min;
	}

	/**
	 * @return the max
	 */
	public NumberValue getMax() {
		return max;
	}

	/**
	 * @param max the max to set
	 */
	public void setMax(NumberValue max) {
		this.max = max;
	}

	/**
	 * @return the to
	 */
	public PAYMENT_TO getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(PAYMENT_TO to) {
		this.to = to;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QPaymentsFee [id=" + id + ", name=" + name + ", type=" + type + ", amount=" + amount + ", cap=" + cap
				+ ", min=" + min + ", max=" + max + ", to=" + to + "]";
	}

	public QPaymentsFee(String name, FEETYPE feeType, NumberValue amount, PAYMENT_TO to) {
		super();
		
		if(name != null && !name.trim().isEmpty()) {
			this.name = name;
		} else {
			throw new IllegalArgumentException("Name of job cannot be empty");
		}
		
		if(feeType.getValue() > 0 && feeType.getValue() <= 4) {
			this.type = feeType.getValue();
		} else {
			throw new IllegalArgumentException("Fee type cannot be invalid. The valid fee types are FIXED, PERCENTAGE, PERCENTAGE_WITH_CAP and PERCENTAGE_WITH_MIN");
		}
		
		Double limitAmount = 100.00;
		if(amount.doubleValue() >= limitAmount) {
			this.amount = amount;
		} else {
			throw new IllegalArgumentException("Item amount needs to be greater than 100 cents");
		}
		
		if(to != null) {
			this.to = to;
		} else {
			throw new IllegalArgumentException("Payment recipient type cannot be empty");
		}
		
	}
	
}
