package life.genny.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import life.genny.qwanda.Answer;
import life.genny.qwanda.Ask;
import life.genny.qwanda.attribute.AttributeText;
import life.genny.qwanda.entity.Person;
import life.genny.qwanda.exception.BadDataException;
import life.genny.qwanda.message.QDataAnswerMessage;
import life.genny.qwanda.message.QDataAskMessage;
import life.genny.test.qwanda.util.JsonUtils;

public class JTest {
	/**
	 * Stores logger object.
	 */
	protected static final Logger log = org.apache.logging.log4j.LogManager
			.getLogger(MethodHandles.lookup().lookupClass().getCanonicalName());

	
  @Test
  public void timeTest() {
    final LocalDateTime time = LocalDateTime.now();

    final String json = JsonUtils.set(time);

    log.info(json);
  }

  @Test
  public void testJ() {
    final Gson gson = new Gson();
    final QDataAskMessage msg = new QDataAskMessage(new Ask[0]);
    final String json = JsonUtils.set(msg);

    try {
      log.info(gson.toJson(msg));
      final String json2 = gson.toJson(msg);
      final QDataAskMessage item3 = gson.fromJson(json2, QDataAskMessage.class);

      log.info(item3);

      final ObjectMapper mapper = new ObjectMapper();
      mapper.registerModule(new JavaTimeModule());
      final QDataAskMessage item = mapper.readValue(json2.getBytes(), QDataAskMessage.class);
      // item = (T) CoreUtils.deserializeBytes(bytes);
      log.info("fdsfsdklfjsdkfj" + item);


      log.info(json);
      mapper.readValue(json.getBytes(), QDataAskMessage.class);


    } catch (final Exception e) {

    }
  }

  @Test
  public void answerMessageTest() {
    final AttributeText attributeFirstname =
        new AttributeText(AttributeText.getDefaultCodePrefix() + "FIRSTNAME_TEST", "Firstname");
    final AttributeText attributeLastname =
        new AttributeText(AttributeText.getDefaultCodePrefix() + "LASTNAME_TEST", "Surname");


    final Person person = new Person("Barry Allen");

    try {
      person.addAttribute(attributeFirstname, 1.0);
      person.addAttribute(attributeLastname, 0.8);
      final Answer answer = new Answer(person, person, attributeFirstname, "Adam");

      final List<Answer> answerList = new ArrayList<Answer>();
      answerList.add(answer);


      final Gson gson = new Gson();
      final QDataAnswerMessage msg = new QDataAnswerMessage(answerList.toArray(new Answer[0]));

      try {
        final String json = gson.toJson(msg);
        log.info(json);

        final QDataAnswerMessage item = gson.fromJson(json, QDataAnswerMessage.class);

        log.info(item);


      } catch (final Exception e) {

      }
    } catch (final BadDataException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }



  }

  @Test
  public void answerTest() {
    final Gson gson = new Gson();
    final String testStr =
        "{\"items\":[{\"created\":{\"date\":{\"year\":2017,\"month\":7,\"day\":8},\"time\":{\"hour\":12,\"minute\":23,\"second\":14,\"nano\":806000000}},\"value\":\"Console\",\"attributeCode\":\"PRI_LASTNAME\",\"targetCode\":22,\"sourceCode\":20,\"expired\":false,\"refused\":false,\"weight\":0.5},{\"created\":{\"date\":{\"year\":2017,\"month\":7,\"day\":8},\"time\":{\"hour\":12,\"minute\":23,\"second\":14,\"nano\":806000000}},\"value\":\"Console\",\"attributeCode\":\"PRI_LASTNAME\",\"targetCode\":22,\"sourceCode\":20,\"expired\":false,\"refused\":false,\"weight\":0.5}],\"data_type\":\"Answer\",\"delete\":false,\"msg_type\":\"DATA_MSG\"}\n";
    try {
      final QDataAnswerMessage item = gson.fromJson(testStr, QDataAnswerMessage.class);
      log.info(item);


      // String askMsg =
      // "[{\"created\":\"2017-07-10T15:10:30\",\"updated\":\"2017-07-11T01:12:46\",\"id\":1,\"name\":\"Firstname\",\"question\":{\"created\":\"2017-07-10T15:10:30\",\"updated\":\"2017-07-11T01:10:30\",\"id\":1,\"name\":\"Firstname\",\"code\":\"QUE_FIRSTNAME\",\"attribute\":{\"created\":\"2017-07-10T15:10:30\",\"updated\":\"2017-07-11T01:10:30\",\"id\":2,\"name\":\"Firstname\",\"code\":\"PRI_FIRSTNAME\",\"dataType\":{\"className\":\"java.lang.String\",\"validationList\":{}}},\"contextList\":{\"contextList\":[]}},\"answerList\":{\"answerList\":[{\"created\":\"2017-07-10T15:12:46\",\"updated\":\"2017-07-10T15:12:46\",\"valueDouble\":null,\"valueInteger\":null,\"valueLong\":null,\"valueDateTime\":null,\"valueString\":\"Bob\",\"valueBoolean\":null,\"expired\":false,\"refused\":false,\"weight\":1.0,\"version\":2,\"targetId\":5,\"sourceId\":5,\"askId\":1,\"createdDate\":\"2017-07-10T05:12:46.000+0000\",\"updatedDate\":\"2017-07-10T05:12:46.000+0000\"}]},\"contextList\":{\"contextList\":[]}},{\"created\":\"2017-07-10T15:10:30\",\"updated\":\"2017-07-11T01:12:46\",\"id\":2,\"name\":\"Lastname\",\"question\":{\"created\":\"2017-07-10T15:10:30\",\"updated\":\"2017-07-11T01:10:30\",\"id\":2,\"name\":\"Lastname\",\"code\":\"QUE_LASTNAME\",\"attribute\":{\"created\":\"2017-07-10T15:10:30\",\"updated\":\"2017-07-11T01:10:30\",\"id\":3,\"name\":\"Lastname\",\"code\":\"PRI_LASTNAME\",\"dataType\":{\"className\":\"java.lang.String\",\"validationList\":{}}},\"contextList\":{\"contextList\":[]}},\"answerList\":{\"answerList\":[{\"created\":\"2017-07-10T15:12:46\",\"updated\":\"2017-07-10T15:12:46\",\"valueDouble\":null,\"valueInteger\":null,\"valueLong\":null,\"valueDateTime\":null,\"valueString\":\"Console\",\"valueBoolean\":null,\"expired\":false,\"refused\":false,\"weight\":1.0,\"version\":2,\"targetId\":5,\"sourceId\":5,\"askId\":2,\"createdDate\":\"2017-07-10T05:12:46.000+0000\",\"updatedDate\":\"2017-07-10T05:12:46.000+0000\"}]},\"contextList\":{\"contextList\":[]}},{\"created\":\"2017-07-10T15:10:30\",\"updated\":\"2017-07-11T01:12:46\",\"id\":3,\"name\":\"Birthdate\",\"question\":{\"created\":\"2017-07-10T15:10:30\",\"updated\":\"2017-07-11T01:10:30\",\"id\":3,\"name\":\"Birthdate\",\"code\":\"QUE_BIRTHDATE\",\"attribute\":{\"created\":\"2017-07-10T15:10:30\",\"updated\":\"2017-07-11T01:10:30\",\"id\":4,\"name\":\"Date
      // of
      // Birth\",\"code\":\"PRI_BIRTHDATE\",\"dataType\":{\"className\":\"java.time.LocalDateTime\",\"validationList\":{}}},\"contextList\":{\"contextList\":[]}},\"answerList\":{\"answerList\":[{\"created\":\"2017-07-10T15:12:46\",\"updated\":\"2017-07-10T15:12:46\",\"valueDouble\":null,\"valueInteger\":null,\"valueLong\":null,\"valueDateTime\":\"1989-01-07T16:00:00\",\"valueString\":null,\"valueBoolean\":null,\"expired\":false,\"refused\":false,\"weight\":1.0,\"version\":2,\"targetId\":5,\"sourceId\":5,\"askId\":3,\"createdDate\":\"2017-07-10T05:12:46.000+0000\",\"updatedDate\":\"2017-07-10T05:12:46.000+0000\"}]},\"contextList\":{\"contextList\":[]}}]";
      // QDataAskMessage qask = gson.fromJson(askMsg, QDataAskMessage.class);
      // log.info(qask);;
    } catch (final JsonSyntaxException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}
