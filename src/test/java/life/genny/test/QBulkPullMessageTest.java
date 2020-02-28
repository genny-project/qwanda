package life.genny.test;

import java.lang.invoke.MethodHandles;
import java.util.UUID;

import org.apache.logging.log4j.Logger;
import org.javamoney.moneta.Money;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import life.genny.qwanda.MoneyDeserializer;
import life.genny.qwanda.message.QBulkPullMessage;

public class QBulkPullMessageTest {
	
	/**
	 * Stores logger object.
	 */
	protected static final Logger log = org.apache.logging.log4j.LogManager
			.getLogger(MethodHandles.lookup().lookupClass().getCanonicalName());


@Test
public void jsonTest()
{
	UUID uuid = UUID.randomUUID();
	QBulkPullMessage msg = new QBulkPullMessage(uuid.toString());
	msg.setPullUrl("https://pontoon.channel40.com.au/pull/"+uuid.toString());
	
	GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(Money.class, new MoneyDeserializer());
	Gson gson = gsonBuilder.create();

	String moneyJson2 = gson.toJson(msg);
	log.info(moneyJson2);
	

}

}
