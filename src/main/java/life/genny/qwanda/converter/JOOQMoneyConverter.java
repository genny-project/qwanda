package life.genny.qwanda.converter;


import org.jooq.Converter;

import org.javamoney.moneta.Money;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import life.genny.qwanda.MoneyDeserializer;

public class JOOQMoneyConverter implements Converter<String, Money> {

    public Money from(String moneyStr) {
    	GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(Money.class, new MoneyDeserializer());
		Gson gson = gsonBuilder.create();
		Money money = gson.fromJson(moneyStr, Money.class);
		return money;
    }

    public String to(Money money) {
    	String ret = "";
		GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(Money.class, new MoneyDeserializer());
		Gson gson = gsonBuilder.create();
		
		ret = gson.toJson(money);


		return ret;
    }

    public Class<String> fromType() {
        return String.class;
    }

    public Class<Money> toType() {
        return Money.class;
    }
}
