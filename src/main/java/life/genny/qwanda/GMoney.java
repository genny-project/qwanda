package life.genny.qwanda;


import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.logging.log4j.Logger;
import org.javamoney.moneta.Money;
import org.javamoney.moneta.spi.MoneyUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;

import com.querydsl.core.annotations.QueryExclude;

@Embeddable
@QueryExclude
public class GMoney implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9173037948143861476L;

	protected static final Logger log =org.apache.logging.log4j.LogManager.getLogger(MethodHandles.lookup().lookupClass().getCanonicalName());

	static public final String REGEX_CURRENCY = "[A-Z]{3}";

	   private static final Currency AUD = Currency.getInstance("AUD");
	   private static final Currency USD = Currency.getInstance("USD");
	   private static final RoundingMode DEFAULT_ROUNDING = RoundingMode.HALF_EVEN;

	    @Column(precision=10, scale=2)
		@Expose
	    private BigDecimal amount;
	    
		@NotNull
//		@Size(max = 3)
//		@Pattern(regexp = REGEX_CURRENCY, message = "Must be valid Currency Code!")
//		@Column(name = "code", updatable = false, nullable = false, unique = false)
		@Expose
	    private Currency currency;   
	    // This value is used for magnitude sorting between monies of different currency
		@JsonIgnore
	    @Column(precision=10, scale=2)
	    private BigDecimal base;

	    
	    public static GMoney dollars(BigDecimal amount) {
	        return new GMoney(amount, AUD);
	    }

	    public static GMoney of(Money money) {
	    		return new GMoney(money.getNumberStripped(),Currency.getInstance(money.getCurrency().getCurrencyCode()));
	    }
	    
	    GMoney(BigDecimal amount, Currency currency) {
	        this(amount, currency, DEFAULT_ROUNDING);
	    }

	   GMoney(BigDecimal amount, Currency currency, RoundingMode rounding) {
	        this.amount = amount;
	        this.currency = currency;

	        this.amount = amount.setScale(currency.getDefaultFractionDigits(), rounding);
	        this.base = this.amount;
	        Money mon = Money.of(amount, "AUD");

	    }

	    public BigDecimal getAmount() {
	        return amount;
	    }

	    public Currency getCurrency() {
	        return currency;
	    }

	    @Override
	    public String toString() {
	        return getCurrency().getSymbol() + " " + getAmount();
	    }

	    public String toString(Locale locale) {
	        return getCurrency().getSymbol(locale) + " " + getAmount();
	    }
	    
//	    public GMoney multiply(Number multiplicand) {
////	    	NumberVerifier.checkNoInfinityOrNaN(multiplicand);
//	        BigDecimal multiplicandBD = MoneyUtils.getBigDecimal(multiplicand);
//	        if (multiplicandBD.equals(BigDecimal.ONE)) {
//	            return this;
//	        }
//	        MathContext mc = MoneyUtils.getMathContext(monetaryContext, RoundingMode.HALF_EVEN);
//	        BigDecimal dec = this.amount.multiply(multiplicandBD, mc);
//	        return new GMoney(dec, getCurrency(), monetaryContext);
//	    }
		/**
		 * @return the base
		 */
		public BigDecimal getBase() {
			return base;
		}

		/**
		 * @param base the base to set
		 */
		public void setBase(BigDecimal base) {
			this.base = base;
		}  
	    
	    
}
