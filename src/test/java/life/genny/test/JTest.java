package life.genny.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import life.genny.qwanda.Answer;
import life.genny.qwanda.Ask;
import life.genny.qwanda.message.QDataAnswerMessage;
import life.genny.qwanda.message.QDataAskMessage;
import life.genny.test.qwanda.util.JsonUtils;

public class JTest {
	@Test
	public void testJ(){
		Gson gson = new Gson();
		QDataAskMessage msg = new QDataAskMessage(new Ask[0]);
		String json = JsonUtils.set(msg);
		
				try {
					System.out.println(gson.toJson(msg));
					String json2 = gson.toJson(msg);
					QDataAskMessage item3 = gson.fromJson(json2, QDataAskMessage.class);
					
					System.out.println(item3);

					ObjectMapper mapper = new ObjectMapper();
                    mapper.registerModule(new JavaTimeModule());
					QDataAskMessage item = (QDataAskMessage) mapper.readValue(json2.getBytes(), QDataAskMessage.class);
                    //item = (T) CoreUtils.deserializeBytes(bytes);
					System.out.println("fdsfsdklfjsdkfj" + item);


                    System.out.println(json);
                    QDataAskMessage item2 = (QDataAskMessage) mapper.readValue(json.getBytes(), QDataAskMessage.class);
                    

            } catch (Exception e) {
                    
            }
	}
	
	@Test
	public void answerMessageTest(){
		
		Answer answer = new Answer("PER_ADAMTEST","PER_ADAMTEST","PRI_FIRSTNAME","Adam");
		Answer answer2 = new Answer("PER_BYRONTEST","PER_BYRONTEST","PRI_FIRSTNAME","Byron");		
		
		List<Answer> answerList = new ArrayList<Answer>();
		answerList.add(answer);
		answerList.add(answer2);
		
		
		Gson gson = new Gson();
		QDataAnswerMessage msg = new QDataAnswerMessage(answerList.toArray(new Answer[0]));
		
				try {
					String json = gson.toJson(msg);
					System.out.println(json);
					
					QDataAnswerMessage item = gson.fromJson(json, QDataAnswerMessage.class);
					
					System.out.println(item);

	
            } catch (Exception e) {
                    
            }
	}

	@Test
	public void answerTest() {
		Gson gson = new Gson();
		String testStr = "{\"items\":[{\"created\":{\"date\":{\"year\":2017,\"month\":7,\"day\":8},\"time\":{\"hour\":12,\"minute\":23,\"second\":14,\"nano\":806000000}},\"value\":\"Console\",\"attributeCode\":\"PRI_LASTNAME\",\"targetCode\":22,\"sourceCode\":20,\"expired\":false,\"refused\":false,\"weight\":0.5},{\"created\":{\"date\":{\"year\":2017,\"month\":7,\"day\":8},\"time\":{\"hour\":12,\"minute\":23,\"second\":14,\"nano\":806000000}},\"value\":\"Console\",\"attributeCode\":\"PRI_LASTNAME\",\"targetCode\":22,\"sourceCode\":20,\"expired\":false,\"refused\":false,\"weight\":0.5}],\"data_type\":\"Answer\",\"delete\":false,\"msg_type\":\"DATA_MSG\"}\n" ;
		try {
			QDataAnswerMessage item = gson.fromJson(testStr, QDataAnswerMessage.class);
			System.out.println(item);
			
			
//			String askMsg = "[{\"created\":\"2017-07-10T15:10:30\",\"updated\":\"2017-07-11T01:12:46\",\"id\":1,\"name\":\"Firstname\",\"question\":{\"created\":\"2017-07-10T15:10:30\",\"updated\":\"2017-07-11T01:10:30\",\"id\":1,\"name\":\"Firstname\",\"code\":\"QUE_FIRSTNAME\",\"attribute\":{\"created\":\"2017-07-10T15:10:30\",\"updated\":\"2017-07-11T01:10:30\",\"id\":2,\"name\":\"Firstname\",\"code\":\"PRI_FIRSTNAME\",\"dataType\":{\"className\":\"java.lang.String\",\"validationList\":{}}},\"contextList\":{\"contextList\":[]}},\"answerList\":{\"answerList\":[{\"created\":\"2017-07-10T15:12:46\",\"updated\":\"2017-07-10T15:12:46\",\"valueDouble\":null,\"valueInteger\":null,\"valueLong\":null,\"valueDateTime\":null,\"valueString\":\"Bob\",\"valueBoolean\":null,\"expired\":false,\"refused\":false,\"weight\":1.0,\"version\":2,\"targetId\":5,\"sourceId\":5,\"askId\":1,\"createdDate\":\"2017-07-10T05:12:46.000+0000\",\"updatedDate\":\"2017-07-10T05:12:46.000+0000\"}]},\"contextList\":{\"contextList\":[]}},{\"created\":\"2017-07-10T15:10:30\",\"updated\":\"2017-07-11T01:12:46\",\"id\":2,\"name\":\"Lastname\",\"question\":{\"created\":\"2017-07-10T15:10:30\",\"updated\":\"2017-07-11T01:10:30\",\"id\":2,\"name\":\"Lastname\",\"code\":\"QUE_LASTNAME\",\"attribute\":{\"created\":\"2017-07-10T15:10:30\",\"updated\":\"2017-07-11T01:10:30\",\"id\":3,\"name\":\"Lastname\",\"code\":\"PRI_LASTNAME\",\"dataType\":{\"className\":\"java.lang.String\",\"validationList\":{}}},\"contextList\":{\"contextList\":[]}},\"answerList\":{\"answerList\":[{\"created\":\"2017-07-10T15:12:46\",\"updated\":\"2017-07-10T15:12:46\",\"valueDouble\":null,\"valueInteger\":null,\"valueLong\":null,\"valueDateTime\":null,\"valueString\":\"Console\",\"valueBoolean\":null,\"expired\":false,\"refused\":false,\"weight\":1.0,\"version\":2,\"targetId\":5,\"sourceId\":5,\"askId\":2,\"createdDate\":\"2017-07-10T05:12:46.000+0000\",\"updatedDate\":\"2017-07-10T05:12:46.000+0000\"}]},\"contextList\":{\"contextList\":[]}},{\"created\":\"2017-07-10T15:10:30\",\"updated\":\"2017-07-11T01:12:46\",\"id\":3,\"name\":\"Birthdate\",\"question\":{\"created\":\"2017-07-10T15:10:30\",\"updated\":\"2017-07-11T01:10:30\",\"id\":3,\"name\":\"Birthdate\",\"code\":\"QUE_BIRTHDATE\",\"attribute\":{\"created\":\"2017-07-10T15:10:30\",\"updated\":\"2017-07-11T01:10:30\",\"id\":4,\"name\":\"Date of Birth\",\"code\":\"PRI_BIRTHDATE\",\"dataType\":{\"className\":\"java.time.LocalDateTime\",\"validationList\":{}}},\"contextList\":{\"contextList\":[]}},\"answerList\":{\"answerList\":[{\"created\":\"2017-07-10T15:12:46\",\"updated\":\"2017-07-10T15:12:46\",\"valueDouble\":null,\"valueInteger\":null,\"valueLong\":null,\"valueDateTime\":\"1989-01-07T16:00:00\",\"valueString\":null,\"valueBoolean\":null,\"expired\":false,\"refused\":false,\"weight\":1.0,\"version\":2,\"targetId\":5,\"sourceId\":5,\"askId\":3,\"createdDate\":\"2017-07-10T05:12:46.000+0000\",\"updatedDate\":\"2017-07-10T05:12:46.000+0000\"}]},\"contextList\":{\"contextList\":[]}}]";
//			QDataAskMessage qask = gson.fromJson(askMsg, QDataAskMessage.class);
		//	System.out.println(qask);;
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
}
