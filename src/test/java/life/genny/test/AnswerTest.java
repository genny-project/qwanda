package life.genny.test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.javamoney.moneta.Money;
import org.junit.Test;

import com.google.common.collect.Range;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import life.genny.qwanda.Answer;
import life.genny.qwanda.AnswerLink;
import life.genny.qwanda.DateTimeDeserializer;
import life.genny.qwanda.MoneyDeserializer;
import life.genny.qwanda.RangeDeserializer;
import life.genny.qwanda.attribute.Attribute;
import life.genny.qwanda.attribute.AttributeDate;
import life.genny.qwanda.attribute.AttributeDateTime;
import life.genny.qwanda.datatype.LocalDateConverter;
import life.genny.qwanda.message.QDataAnswerMessage;

public class AnswerTest {
	static GsonBuilder gsonBuilder = new GsonBuilder();

	static public Gson gson = gsonBuilder.registerTypeAdapter(Money.class, new MoneyDeserializer())
			.registerTypeAdapter(new TypeToken<Range<LocalDate>>(){}.getType(), new RangeDeserializer())
			.registerTypeAdapter(LocalDateTime.class, new DateTimeDeserializer()).setPrettyPrinting()
			.registerTypeAdapter(LocalDate.class, new LocalDateConverter()).excludeFieldsWithoutExposeAnnotation()
			.create();

	@Test
    public void AnswerDateTimetest() {
	       Answer[] answers = new Answer[2];
	       
        
        Attribute attributeDateTime = new AttributeDateTime("PRI_PICKUP_DATETIME","Test DateTiume 7");
        AnswerLink link = new AnswerLink();
        Answer dateTime = new Answer("PER_USER1","BEG_JOMU655BE9F30EAD431F8BF7DC5627B86705", "PRI_PICKUP_DATETIME", "2018-02-15T10:00:00.000Z");
        dateTime.setId(new Long(20));
        dateTime.setAskId(new Long(10));
        System.out.println("Answer   ::  "+dateTime.toString());
        link.setAttribute(attributeDateTime);
        link.setAnswer(dateTime);
        
        answers[0] = dateTime;
        
        dateTime = new Answer("PER_USER1","BEG_JOMU655BE9F30EAD431F8BF7DC5627B86705", "PRI_PICKUP_DATETIME", "2018-01-16T01:00:00");
        dateTime.setId(new Long(20));
        dateTime.setAskId(new Long(10));
        System.out.println("Answer   ::  "+dateTime.toString());
        link.setAttribute(attributeDateTime);
        link.setAnswer(dateTime);
    
        dateTime = new Answer("PER_USER1","BEG_JOMU655BE9F30EAD431F8BF7DC5627B86705", "PRI_PICKUP_DATETIME", "2018-01-16T01:00");
        dateTime.setId(new Long(20));
        dateTime.setAskId(new Long(10));
        System.out.println("Answer   ::  "+dateTime.toString());
        link.setAttribute(attributeDateTime);
        link.setAnswer(dateTime);
        answers[1] = dateTime;
       
        QDataAnswerMessage msg = new QDataAnswerMessage(answers);
        
        String json = gson.toJson(msg);
        System.out.println("json Answer Msg="+json);
  
        
        
    }
	
	@Test
    public void AnswerDatetest() {
        
        Attribute attributeDate = new AttributeDate("PRI_PICKUP_DATE","Test DateTiume 7");
        AnswerLink link = new AnswerLink();
        Answer dateTime = new Answer("PER_USER1","BEG_JOMU655BE9F30EAD431F8BF7DC5627B86705", "PRI_PICKUP_DATE", "2018-01-16");
        dateTime.setId(new Long(20));
        dateTime.setAskId(new Long(10));
        System.out.println("Answer   ::  "+dateTime.toString());
        link.setAttribute(attributeDate);
        link.setAnswer(dateTime);
        
        attributeDate = new AttributeDate("PRI_PICKUP_DATE","Test Date 7");
         link = new AnswerLink();
        dateTime = new Answer("PER_USER1","BEG_JOMU655BE9F30EAD431F8BF7DC5627B86705", "PRI_PICKUP_DATE", "1964-06-19T14:00:00.000Z");
        dateTime.setId(new Long(20));
        dateTime.setAskId(new Long(10));
        System.out.println("Answer   ::  "+dateTime.toString());
        link.setAttribute(attributeDate);
        link.setAnswer(dateTime);   
    }
}
