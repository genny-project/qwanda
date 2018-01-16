package life.genny.test;

import org.junit.Test;

import life.genny.qwanda.Answer;
import life.genny.qwanda.AnswerLink;
import life.genny.qwanda.attribute.Attribute;
import life.genny.qwanda.attribute.AttributeDate;
import life.genny.qwanda.attribute.AttributeDateTime;

public class AnswerTest {
	@Test
    public void AnswerDateTimetest() {
        
        Attribute attributeDateTime = new AttributeDateTime("PRI_PICKUP_DATETIME","Test DateTiume 7");
        AnswerLink link = new AnswerLink();
        Answer dateTime = new Answer("PER_USER1","BEG_JOMU655BE9F30EAD431F8BF7DC5627B86705", "PRI_PICKUP_DATETIME", "2018-01-16T01:00:00.000Z");
        dateTime.setId(new Long(20));
        dateTime.setAskId(new Long(10));
        System.out.println("Answer   ::  "+dateTime.toString());
        link.setAttribute(attributeDateTime);
        link.setAnswer(dateTime);
        
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
