package life.genny.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import javax.money.CurrencyUnit;
import javax.money.Monetary;

import org.javamoney.moneta.Money;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import life.genny.qwanda.MoneyDeserializer;
import life.genny.test.qwanda.util.JsonUtils;

public class MoneyTest {
	
	public static final String DEFAULT_CURRENCY_AUD = "AUD";
	
@Test
public void moneyTest()
{
	final CurrencyUnit AUD = Monetary.getCurrency("AUD");


	Money money = Money.of(new BigDecimal("12.34"), AUD);
	
	String moneyJson = JsonUtils.set(money);
	
	System.out.println(moneyJson);
	
	GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(Money.class, new MoneyDeserializer());
	Gson gson = gsonBuilder.create();
	
	String moneyJson2 = gson.toJson(money);
	System.out.println(moneyJson2);
	
	String test = "{\"amount\":12.34,\"currency\":\"AUD\"}";
	
	Money testMoney = gson.fromJson(test, Money.class);
	
	System.out.println("Money = "+testMoney);
}


public Money calculateFeesMoney(Money answerPrice) {
	
	Money stringToMoney = Money.of(Integer.parseInt("1000"), DEFAULT_CURRENCY_AUD);
	System.out.println("string to money ::"+stringToMoney);
	String stringvalue = String.valueOf(stringToMoney);
	System.out.println("string value of money ::"+stringvalue);
	
	
	Money fees = Money.of(0, DEFAULT_CURRENCY_AUD);	
			
	if (answerPrice.compareTo(Money.of(0, DEFAULT_CURRENCY_AUD)) > 0 && answerPrice.compareTo(Money.of(1000, DEFAULT_CURRENCY_AUD)) <= 0) {
		
		/* 15% of price if price less than or equal to 1000 */
		fees = answerPrice.multiply(0.15);
		
	} else if (answerPrice.compareTo(Money.of(1000, DEFAULT_CURRENCY_AUD)) > 0 && answerPrice.compareTo(Money.of(3000, DEFAULT_CURRENCY_AUD)) <= 0) {
		/*
			* 15% + 10% of remaining price if price greater than 1000 and less
			* than or equal to 3000
			*/
		Money initialFee = Money.of(150, DEFAULT_CURRENCY_AUD);
		Money negatedAmount = answerPrice.subtract(Money.of(1000, DEFAULT_CURRENCY_AUD));
		fees = initialFee.add(negatedAmount.multiply(0.1));
		
	} else if (answerPrice.compareTo(Money.of(3000, DEFAULT_CURRENCY_AUD)) > 0 && answerPrice.compareTo(Money.of(5000, DEFAULT_CURRENCY_AUD)) <= 0) {
		/*
			* 15% + 10% + (7.5% of remaining amount) if price is greater than
			* 3000 and less than or equal to 5000
			*/
		Money initialFee = Money.of(350, DEFAULT_CURRENCY_AUD);
		Money negatedAmount = answerPrice.subtract(Money.of(3000, DEFAULT_CURRENCY_AUD));
		fees = initialFee.add(negatedAmount.multiply(0.075));
		
	} else if (answerPrice.compareTo(Money.of(5000, DEFAULT_CURRENCY_AUD)) > 0) {
		/*
			* 15% + 10% + 7.5% + (5% of remaining amount) if price is greater
			* than 5000
			*/
		Money initialFee = Money.of(500, DEFAULT_CURRENCY_AUD);
		Money negatedAmount = answerPrice.subtract(Money.of(5000, DEFAULT_CURRENCY_AUD));
		fees = initialFee.add(negatedAmount.multiply(0.05));
	}
	
	return fees;
	
}

@Test
public void calculateFee() {
	Money testMoney = Money.of(10000, DEFAULT_CURRENCY_AUD);
	assertEquals("AUD 750", String.valueOf(calculateFeesMoney(testMoney)));
	
	Money testMoney1 = Money.of(8000, DEFAULT_CURRENCY_AUD);
	assertEquals("AUD 650", String.valueOf(calculateFeesMoney(testMoney1)));
}



}
