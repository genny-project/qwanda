package life.genny.qwandautils;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.Logger;
import org.javamoney.moneta.Money;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import life.genny.qwanda.DateTimeDeserializer;
import life.genny.qwanda.MoneyDeserializer;
import life.genny.qwanda.datatype.LocalDateConverter;
import life.genny.qwanda.entity.BaseEntity;

public class JsonUtils {
	
	protected static final Logger log = org.apache.logging.log4j.LogManager
			.getLogger(MethodHandles.lookup().lookupClass().getCanonicalName());

	static GsonBuilder gsonBuilder = new GsonBuilder();       

	static public Gson gson = gsonBuilder.registerTypeAdapter(Money.class, new MoneyDeserializer())
			.registerTypeAdapter(LocalDateTime.class, new DateTimeDeserializer()).setPrettyPrinting()
			.registerTypeAdapter(LocalDate.class, new LocalDateConverter())

			.excludeFieldsWithoutExposeAnnotation().create();
	
	
	public static <T> T fromJson(final String json, Class clazz)
	{
	        T item = null;
	        if (json != null) {
	                try {
	                	if (clazz.getSimpleName().equalsIgnoreCase(BaseEntity.class.getSimpleName())) {
	                		 item = (T)gson.fromJson(json, clazz);
	                	} else {
	                      item = (T)gson.fromJson(json, clazz);
	                	}
	                } catch (Exception e) {
	                	     System.out.println("The JSON file received is  :::  "+json);;
	                     log.error("Bad Deserialisation for "+clazz.getSimpleName());
	                }
	        }
	        return item;
	}
	
	public static <T> T fromJson(final String json, Type clazz)
	{
	        T item = null;
	        if (json != null) {
	                try {
	                      item = (T)gson.fromJson(json, clazz);

	                } catch (Exception e) {
	                	     System.out.println("The JSON file received is  :::  "+json);;
	                     log.error("Bad Deserialisation for "+clazz.getTypeName());
	                }
	        }
	        return item;
	}
	
	public static String toJson(Object obj)
	{
	
		String ret =  gson.toJson(obj);
		return ret;
	}
	
	
	public static org.json.simple.JSONObject jsonStringParser(String stringifiedJsonObject) {
		
		org.json.simple.JSONObject obj = null;
		if(stringifiedJsonObject != null) {
			
			JSONParser parser = new JSONParser();
			try {
				obj = (org.json.simple.JSONObject) parser.parse(stringifiedJsonObject);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return obj;	
	}
}
