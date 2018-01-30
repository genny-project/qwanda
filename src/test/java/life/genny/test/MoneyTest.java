package life.genny.test;

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
}
