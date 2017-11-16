package life.genny.test;

import com.google.gson.Gson;
import org.junit.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import life.genny.qwanda.Answer;
import life.genny.qwanda.Ask;
import life.genny.qwanda.GPS;
import life.genny.qwanda.Question;
import life.genny.qwanda.attribute.Attribute;
import life.genny.qwanda.attribute.AttributeDateTime;
import life.genny.qwanda.attribute.AttributeText;
import life.genny.qwanda.entity.Person;
import life.genny.qwanda.exception.BadDataException;
import life.genny.qwanda.message.QCmdLayoutMessage;
import life.genny.qwanda.message.QCmdLogoutMessage;
import life.genny.qwanda.message.QCmdMessage;
import life.genny.qwanda.message.QCmdRedirectMessage;
import life.genny.qwanda.message.QDataAnswerMessage;
import life.genny.qwanda.message.QDataGPSMessage;
import life.genny.test.qwanda.util.JsonUtils;


public class JsonGenerator {

  Person person;
  Attribute attributeFirstname = null;
  Attribute attributeLastname = null;
  Attribute attributeBirthdate = null;
  Question questionFirstname = null;
  Question questionLastname = null;
  Question questionBirthdate = null;


  @Test
  public void generateCMDJsons() throws BadDataException {
    final GPS[] gpss = new GPS[1];
    gpss[0] = new GPS("PER_USER1", "-37.86330", "145.0922");
    final QDataGPSMessage gpsmsg = new QDataGPSMessage(gpss);
    JsonGenerator(gpsmsg);

    JsonGenerator(new QCmdLayoutMessage("LAY_TEST_WITH_DATA", "this is the layout code"));
    JsonGenerator(new QCmdMessage("CMD_LAYOUT", "LAY_TEST_WITH_JUST_CODE"));
    JsonGenerator(new QCmdRedirectMessage("http://www.theage.com.au"));
    JsonGenerator(new QCmdLogoutMessage());


    attributeFirstname =
        new AttributeText(AttributeText.getDefaultCodePrefix() + "FIRSTNAME_TEST", "Firstname");
    attributeLastname =
        new AttributeText(AttributeText.getDefaultCodePrefix() + "LASTNAME_TEST", "Surname");
    attributeBirthdate =
        new AttributeDateTime(AttributeText.getDefaultCodePrefix() + "BIRTHDAY", "Date of Birth");

    JsonGenerator(attributeFirstname);

    person = new Person("Barry Allen");

    person.addAttribute(attributeFirstname, 1.0);
    person.addAttribute(attributeLastname, 0.8);
    person.addAttribute(attributeBirthdate, 0.6, LocalDateTime.of(1989, 1, 7, 16, 0));


    questionFirstname = new Question(Question.getDefaultCodePrefix() + "FIRSTNAME1", "Firstname:",
        attributeFirstname);
    questionLastname =
        new Question(Question.getDefaultCodePrefix() + "LASTNAME1", "Lastname:", attributeLastname);
    questionBirthdate = new Question(Question.getDefaultCodePrefix() + "BIRTHDATE1", "Birthdatee:",
        attributeBirthdate);

    JsonGenerator(questionFirstname);


    // Now ask the question!


    final Ask[] asks = new Ask[3];

    asks[0] = new Ask(questionFirstname, person.getCode(), person.getCode());
    asks[1] = new Ask(questionLastname, person.getCode(), person.getCode());
    asks[2] = new Ask(questionBirthdate, person.getCode(), person.getCode());



    // JsonGenerator(new QDataAskMessage(asks));


    final Answer answerFirstname = new Answer(asks[0], "Bob");
    final Answer answerLastname = new Answer(asks[1], "Console");
    // final Answer answerBirthdate = new Answer(asks[2],"1989-01-07 16:00");

    JsonGenerator(answerFirstname);

    person.addAnswer(answerFirstname, 1.0);
    person.addAnswer(answerLastname, 1.0);
    // person.addAnswer(answerBirthdate, 1.0);


    final Answer answer = new Answer(person, person, attributeFirstname, "Adam");
    // final Answer answer2 = new
    // Answer("PER_BYRONTEST","PER_BYRONTEST",attributeFirstname,"Byron");

    final List<Answer> answerList = new ArrayList<Answer>();
    answerList.add(answer);
    // answerList.add(answer2);


    final QDataAnswerMessage msg = new QDataAnswerMessage(answerList.toArray(new Answer[0]));
    JsonGenerator(msg);
  }

  private void JsonGenerator(final Object src) {


    new Gson();
    try {
      final String json = JsonUtils.set(src); // gson.toJson(src);
      System.out.println("*** " + src.getClass().getSimpleName());
      System.out.println(json);
    } catch (final Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}
