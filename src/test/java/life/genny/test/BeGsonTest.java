package life.genny.test;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.javamoney.moneta.Money;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;

import life.genny.qwanda.DateTimeDeserializer;
import life.genny.qwanda.MoneyDeserializer;
import life.genny.qwanda.datatype.LocalDateAdapter;
import life.genny.qwanda.message.QDataBaseEntityMessage;

public class BeGsonTest {
	static GsonBuilder gsonBuilder = new GsonBuilder();       

	static public Gson gson = gsonBuilder.registerTypeAdapter(Money.class, new MoneyDeserializer())
			.registerTypeAdapter(LocalDateTime.class, new DateTimeDeserializer()).registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
			    @Override
			    public LocalDate deserialize(JsonElement json, java.lang.reflect.Type typeOfT,
			        JsonDeserializationContext context) throws JsonParseException {

			      return LocalDate.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ISO_DATE);     
			    }
			}).setPrettyPrinting()
			.registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
				@Override
				public LocalDate deserialize(final JsonElement json, final Type type,
						final JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
					LocalDate ret = null;
					try {
						String str = json.getAsJsonPrimitive().getAsString();
					} catch (Exception e) {
						return null;
					}
					ret = LocalDate.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ISO_LOCAL_DATE);
					return ret;
				}

				public JsonElement serialize(final LocalDate date, final Type typeOfSrc,
						final JsonSerializationContext context) {
					
					return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
				}
			})
			// .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
			.excludeFieldsWithoutExposeAnnotation().create();
	
	 @Test
	 public void beTest()
	 {
		 String incoming2 = "{  \"items\": [    {      \"baseEntityAttributes\": [        {          \"baseEntityCode\": \"PER_USER1\",          \"attributeCode\": \"PRI_EMAIL\",          \"created\": \"2018-01-31T10:12:54\",          \"valueString\": \"gayatri.raghavan@outcome.life\",          \"weight\": 1.0,          \"inferred\": false,          \"privacyFlag\": false        },        {          \"baseEntityCode\": \"PER_USER1\",          \"attributeCode\": \"PRI_ADDRESS_POSTCODE\",          \"created\": \"2018-01-31T10:12:53\",          \"valueString\": \"3053\",          \"weight\": 1.0,          \"inferred\": false,          \"privacyFlag\": false        },        {          \"baseEntityCode\": \"PER_USER1\",          \"attributeCode\": \"PRI_ASSEMBLY_USER_ID\",          \"created\": \"2018-01-31T10:12:54\",          \"valueString\": \"eyJ1c2VyQ29kZSI6IlBFUl9VU0VSMSJ9\",          \"weight\": 1.0,          \"inferred\": false,          \"privacyFlag\": false        },        {          \"baseEntityCode\": \"PER_USER1\",          \"attributeCode\": \"PRI_OWNER\",          \"created\": \"2018-01-31T10:12:54\",          \"valueBoolean\": true,          \"weight\": 1.0,          \"inferred\": false,          \"privacyFlag\": false        },        {          \"baseEntityCode\": \"PER_USER1\",          \"attributeCode\": \"PRI_UUID\",          \"created\": \"2018-01-31T10:12:53\",          \"valueString\": \"9e4dd67d-c75c-4ab9-b26b-40ce0ba0e034\",          \"weight\": 1.0,          \"inferred\": false,          \"privacyFlag\": true        },        {          \"baseEntityCode\": \"PER_USER1\",          \"attributeCode\": \"PRI_ADDRESS_STATE\",          \"created\": \"2018-01-31T10:12:53\",          \"valueString\": \"VIC\",          \"weight\": 1.0,          \"inferred\": false,          \"privacyFlag\": false        },        {          \"baseEntityCode\": \"PER_USER1\",          \"attributeCode\": \"PRI_ADDRESS_CITY\",          \"created\": \"2018-01-31T10:12:53\",          \"valueString\": \"Melbourne\",          \"weight\": 1.0,          \"inferred\": false,          \"privacyFlag\": false        },        {          \"baseEntityCode\": \"PER_USER1\",          \"attributeCode\": \"PRI_LASTNAME\",          \"created\": \"2018-01-31T10:12:53\",          \"valueString\": \"Cavanagh\",          \"weight\": 1.0,          \"inferred\": false,          \"privacyFlag\": false        },        {          \"baseEntityCode\": \"PER_USER1\",          \"attributeCode\": \"PRI_ASSEMBLY_COMPANY_ID\",          \"created\": \"2018-01-31T10:12:54\",          \"valueString\": \"71f464cf-d9b1-4547-91a5-9ee80a7adb39\",          \"weight\": 1.0,          \"inferred\": false,          \"privacyFlag\": false        },        {          \"baseEntityCode\": \"PER_USER1\",          \"attributeCode\": \"PRI_ADDRESS_FULL\",          \"created\": \"2018-01-31T10:12:54\",          \"valueString\": \"121 Cardigan Street, Carlton, VIC\",          \"weight\": 1.0,          \"inferred\": false,          \"privacyFlag\": false        },        {          \"baseEntityCode\": \"PER_USER1\",          \"attributeCode\": \"PRI_FIRSTNAME\",          \"created\": \"2018-01-31T10:12:54\",          \"valueString\": \"Tom\",          \"weight\": 1.0,          \"inferred\": false,          \"privacyFlag\": false        },        {          \"baseEntityCode\": \"PER_USER1\",          \"attributeCode\": \"PRI_ADDRESS_COUNTRY\",          \"created\": \"2018-01-31T10:12:54\",          \"valueString\": \"AU\",          \"weight\": 1.0,          \"inferred\": false,          \"privacyFlag\": false        },        {          \"baseEntityCode\": \"PER_USER1\",          \"attributeCode\": \"PRI_KEYCLOAK_UUID\",          \"created\": \"2018-01-31T10:12:54\",          \"valueString\": \"029b2ab9-e6ee-47b9-aeff-33fd537a1a07\",          \"weight\": 1.0,          \"inferred\": false,          \"privacyFlag\": false        },        {          \"baseEntityCode\": \"PER_USER1\",          \"attributeCode\": \"PRI_ADDRESS_ADDRESS1\",          \"created\": \"2018-01-31T10:12:54\",          \"valueString\": \"121 Cardigan Street\",          \"weight\": 1.0,          \"inferred\": false,          \"privacyFlag\": false        },        {          \"baseEntityCode\": \"PER_USER1\",          \"attributeCode\": \"PRI_MOBILE\",          \"created\": \"2018-01-31T10:12:54\",          \"valueString\": \"61424664610\",          \"weight\": 1.0,          \"inferred\": false,          \"privacyFlag\": false        },        {          \"baseEntityCode\": \"PER_USER1\",          \"attributeCode\": \"PRI_TEST\",          \"created\": \"2018-01-31T10:12:53\",          \"valueString\": \"I am a test\",          \"weight\": 1.0,          \"inferred\": false,          \"privacyFlag\": false        },        {          \"baseEntityCode\": \"PER_USER1\",          \"attributeCode\": \"PRI_DRIVING_LICENSE\",          \"created\": \"2018-01-31T10:12:53\",          \"valueString\": \"null\",          \"weight\": 1.0,          \"inferred\": false,          \"privacyFlag\": false        },        {          \"baseEntityCode\": \"PER_USER1\",          \"attributeCode\": \"PRI_USERNAME\",          \"created\": \"2018-01-31T10:12:54\",          \"valueString\": \"user1\",          \"weight\": 1.0,          \"inferred\": false,          \"privacyFlag\": false        },        {          \"baseEntityCode\": \"PER_USER1\",          \"attributeCode\": \"PRI_GENDER\",          \"created\": \"2018-01-31T10:12:53\",          \"valueString\": \"null\",          \"weight\": 1.0,          \"inferred\": false,          \"privacyFlag\": false        },        {          \"baseEntityCode\": \"PER_USER1\",          \"attributeCode\": \"PRI_IMAGE_URL\",          \"created\": \"2018-01-31T10:12:55\",          \"valueString\": \"https://i.imgur.com/nRIZ93K.png\",          \"weight\": 1.0,          \"inferred\": false,          \"privacyFlag\": false        }      ],      \"code\": \"PER_USER1\",      \"created\": \"2018-01-31T10:12:52\",      \"id\": 85,      \"name\": \"Josh Mullens\",      \"realm\": \"genny\"    }  ],  \"parentCode\": \"\",  \"linkCode\": \"\",  \"total\": 1,  \"returnCount\": 1,  \"data_type\": \"BaseEntity\",  \"delete\": false,  \"msg_type\": \"DATA_MSG\"}";
		 String incoming = "{\"items\":[{\"baseEntityAttributes\":[{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_EMAIL\",\"created\":\"2018-01-31T08:31:58\",\"valueString\":\"gayatri.raghavan@outcome.life\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_ADDRESS_POSTCODE\",\"created\":\"2018-01-31T08:31:57\",\"valueString\":\"3053\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_ASSEMBLY_USER_ID\",\"created\":\"2018-01-31T08:31:58\",\"valueString\":\"eyJ1c2VyQ29kZSI6IlBFUl9VU0VSMSJ9\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_DOB\",\"created\":\"2018-01-31T08:31:59\",\"valueDate\":{},\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_OWNER\",\"created\":\"2018-01-31T08:31:58\",\"valueBoolean\":true,\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_UUID\",\"created\":\"2018-01-31T08:31:58\",\"valueString\":\"9e4dd67d-c75c-4ab9-b26b-40ce0ba0e034\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":true},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_ADDRESS_STATE\",\"created\":\"2018-01-31T08:31:58\",\"valueString\":\"VIC\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_ADDRESS_CITY\",\"created\":\"2018-01-31T08:31:57\",\"valueString\":\"Melbourne\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_LASTNAME\",\"created\":\"2018-01-31T08:31:57\",\"valueString\":\"Cavanagh\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_ASSEMBLY_COMPANY_ID\",\"created\":\"2018-01-31T08:31:58\",\"valueString\":\"71f464cf-d9b1-4547-91a5-9ee80a7adb39\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_ADDRESS_FULL\",\"created\":\"2018-01-31T08:31:58\",\"valueString\":\"121 Cardigan Street, Carlton, VIC\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_FIRSTNAME\",\"created\":\"2018-01-31T08:31:58\",\"valueString\":\"Tom\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_ADDRESS_COUNTRY\",\"created\":\"2018-01-31T08:31:58\",\"valueString\":\"AU\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_FB_BASIC\",\"created\":\"2018-01-31T08:31:57\",\"valueString\":\"null\",\"weight\":0.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_KEYCLOAK_UUID\",\"created\":\"2018-01-31T08:31:59\",\"valueString\":\"029b2ab9-e6ee-47b9-aeff-33fd537a1a07\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_ADDRESS_ADDRESS1\",\"created\":\"2018-01-31T08:31:58\",\"valueString\":\"121 Cardigan Street\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_MOBILE\",\"created\":\"2018-01-31T08:31:58\",\"valueString\":\"61424664610\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_TEST\",\"created\":\"2018-01-31T08:31:57\",\"valueString\":\"I am a test\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_DRIVING_LICENSE\",\"created\":\"2018-01-31T08:31:57\",\"valueString\":\"null\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_USERNAME\",\"created\":\"2018-01-31T08:31:59\",\"valueString\":\"user1\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_GENDER\",\"created\":\"2018-01-31T08:31:57\",\"valueString\":\"null\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_IMAGE_URL\",\"created\":\"2018-01-31T08:31:59\",\"valueString\":\"https://i.imgur.com/nRIZ93K.png\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false}],\"code\":\"PER_USER1\",\"created\":\"2018-01-31T08:31:57\",\"id\":90,\"name\":\"Josh Mullens\",\"realm\":\"genny\"}],\"parentCode\":\"\",\"linkCode\":\"\",\"total\":1,\"returnCount\":1,\"data_type\":\"BaseEntity\",\"delete\":false,\"msg_type\":\"DATA_MSG\"}";
	 
		    
		    QDataBaseEntityMessage item = (QDataBaseEntityMessage)gson.fromJson(incoming, QDataBaseEntityMessage.class);
		    System.out.println("Item ="+item);
	 
	 }
}
