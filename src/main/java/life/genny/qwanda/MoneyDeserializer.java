package life.genny.qwanda;


import java.lang.reflect.Type;
import java.math.BigDecimal;

import javax.money.CurrencyUnit;
import javax.money.Monetary;

import org.javamoney.moneta.Money;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class MoneyDeserializer implements JsonSerializer<Money>, JsonDeserializer<Money> {

    
    @Override
    public JsonElement serialize(Money src, Type typeOfSrc, JsonSerializationContext context)
    {
    	JsonParser parser = new JsonParser();
    	JsonElement o = parser.parse("{\"amount\":"+src.getNumber()+",\"currency\":\""+src.getCurrency().getCurrencyCode()+"\"}");
      return o;
    }
    
    @Override
    public Money deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException
    {
    	final CurrencyUnit currency = Monetary.getCurrency(json.getAsJsonObject().get("currency").getAsString());


    	Money money = Money.of(new BigDecimal(json.getAsJsonObject().get("amount").getAsString()), currency);

      return money;
    }
}