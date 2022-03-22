package life.genny.test.qwanda.util;

import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

import org.junit.Assert;
import org.zalando.jackson.datatype.money.MoneyModule;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonUtils {

	   public static String set(final Object item)  {

	        ObjectMapper mapper = new ObjectMapper();
	        mapper.registerModule(new JavaTimeModule());
	        mapper.registerModule(new MoneyModule());

	        String json = null;

	        try {
	                json = mapper.writeValueAsString(item);
	        } catch (JsonProcessingException e) {
	                Assert.assertTrue("Bad Serialization for "+item.getClass().getSimpleName(), true);
	        }
	        return json;
	}


	@SuppressWarnings("unchecked")
	@Transient
	@JsonIgnore
	@XmlTransient
	public  <T>  T get(@SuppressWarnings("rawtypes") Class testClass,final String json)  {
	        T item = null;


	        if (json != null) {
	                try {
	                        //item = (T) CoreUtils.deserializeBytes(bytes);
	                        ObjectMapper mapper = new ObjectMapper();
	                        mapper.registerModule(new JavaTimeModule());
	                        mapper.registerModule(new MoneyModule());
	                        item = (T) mapper.readValue(json.getBytes(), testClass);

	                } catch (Exception e) {
	                        Assert.assertTrue("Bad Deserialisation for "+testClass.getSimpleName(), true);
	                }
	        }
	        return item;
	}
}
