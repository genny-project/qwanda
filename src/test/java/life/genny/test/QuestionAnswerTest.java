package life.genny.test;

import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import life.genny.qwanda.Answer;
import life.genny.qwanda.Ask;
import life.genny.qwanda.Question;
import life.genny.qwanda.attribute.Attribute;
import life.genny.qwanda.attribute.AttributeDate;
import life.genny.qwanda.attribute.AttributeDateTime;
import life.genny.qwanda.attribute.AttributeText;
import life.genny.qwanda.attribute.EntityAttribute;
import life.genny.qwanda.entity.Person;
import life.genny.qwanda.exception.BadDataException;
import life.genny.test.qwanda.util.HibernateUtil;
import life.genny.test.qwanda.util.JsonUtils;


public class QuestionAnswerTest {

  Person person;
  Attribute attributeFirstname = null;
  Attribute attributeLastname = null;
  Attribute attributeBirthdate = null;
  Question questionFirstname = null;
  Question questionLastname = null;
  Question questionBirthdate = null;


  static public final String hibernate_h2_cfg = "hibernate.cfg.xml";
  static public final String hibernate_mysql_cfg = "hibernate-mysql.cfg.xml";

  /**
   * 
   * Stores logger object.
   */
  private static final Logger log = org.apache.logging.log4j.LogManager
      .getLogger(MethodHandles.lookup().lookupClass().getCanonicalName());



  @Before
  public void setup() throws BadDataException {

    attributeFirstname =
        new AttributeText(AttributeText.getDefaultCodePrefix() + "FIRSTNAME_TEST", "Firstname");
    attributeLastname =
        new AttributeText(AttributeText.getDefaultCodePrefix() + "LASTNAME_TEST", "Surname");
    attributeBirthdate =
        new AttributeDateTime(AttributeText.getDefaultCodePrefix() + "BIRTHDAY", "Date of Birth");

    person = new Person("Barry Allen");

    person.addAttribute(attributeFirstname, 1.0);
    person.addAttribute(attributeLastname, 0.8);
    person.addAttribute(attributeBirthdate, 0.6, LocalDateTime.of(1989, 1, 7, 16, 0));


  }

  @After
  public void tearDown() {
    person = null;

  }

  @Test
  public void entityAttributeTest() {
    final SessionFactory sessionFactory = HibernateUtil.getSessionFactory(hibernate_h2_cfg);

    Session session = null;


    Transaction tx = null;

    try {
      session = sessionFactory.openSession();
      tx = session.beginTransaction();
      tx.setTimeout(30);

      for (final EntityAttribute ea : person.getBaseEntityAttributes()) {
        session.save(ea.getAttribute());
      }
      session.save(person);
      System.out.println(person);

      // Create a Question to match the attribute

      // Firstname

      questionFirstname = new Question(Question.getDefaultCodePrefix() + "FIRSTNAME1", "Firstname:",
          attributeFirstname);
      questionLastname = new Question(Question.getDefaultCodePrefix() + "LASTNAME1", "Lastname:",
          attributeLastname);
      questionBirthdate = new Question(Question.getDefaultCodePrefix() + "BIRTHDATE1",
          "Birthdatee:", attributeBirthdate);

      session.save(questionFirstname);
      session.save(questionLastname);
      session.save(questionBirthdate);

      // Now ask the question!

      final Ask askFirstname = new Ask(questionFirstname, person.getCode(), person.getCode());
      final Ask askLastname = new Ask(questionLastname, person.getCode(), person.getCode());
      final Ask askBirthdate = new Ask(questionBirthdate, person.getCode(), person.getCode());

      session.save(askFirstname);
      session.save(askLastname);
      session.save(askBirthdate);

      final Answer answerFirstname = new Answer(askFirstname, "Bob");
      final Answer answerLastname = new Answer(askFirstname, "Console");
      final Answer answerBirthdate = new Answer(askFirstname, "1989-01-07 16:00");

      person.addAnswer(answerFirstname, 1.0);
      person.addAnswer(answerLastname, 1.0);
      person.addAnswer(answerBirthdate, 1.0);

      person = (Person) session.merge(person);

      tx.commit();
      final String json = JsonUtils.set(askFirstname);
      final Ask result = new JsonUtils().get(Ask.class, json);
      System.out.println(result);

//      tx = session.beginTransaction();
//      tx.setTimeout(30);
//      session.remove(questionFirstname);
//      session.remove(questionLastname);
//      session.remove(questionBirthdate);
//
//      session.remove(person);
//      session.remove(attributeFirstname);
//      session.remove(attributeLastname);
//      session.remove(attributeBirthdate);
//      tx.commit();

    } catch (RuntimeException | BadDataException e) {
      try {
        log.error("Error in Hibernate Class Testing", e);
        tx.rollback();
      } catch (final RuntimeException rollbackException) {
        log.error("Rollback Error", rollbackException);
      }


    } finally {
      if (session != null) {
        session.close();
      }
      System.out.println("Finished Hibernate Testing");
    }
  }

  @Test
  public void questionQuestionTest() {
    final SessionFactory sessionFactory = HibernateUtil.getSessionFactory(hibernate_h2_cfg);

    Session session = null;


    Transaction tx = null;

    try {
      session = sessionFactory.openSession();
      tx = session.beginTransaction();
      tx.setTimeout(30);

      AttributeText attributeFirstname2 =
    	        new AttributeText(AttributeText.getDefaultCodePrefix() + "FIRSTNAME_TEST2", "Firstname");
      AttributeText  attributeLastname2 =
    	        new AttributeText(AttributeText.getDefaultCodePrefix() + "LASTNAME_TEST2", "Surname");
      AttributeDate  attributeBirthdate2 =
    	        new AttributeDate(AttributeText.getDefaultCodePrefix() + "BIRTHDAY2", "Date of Birth");
      AttributeText  attributeMiddlename2 =
  	        new AttributeText(AttributeText.getDefaultCodePrefix() + "MIDDLENAME_TEST2", "Middle Name");

    	   Person person2 = new Person("Clark Kent");

    	    person2.addAttribute(attributeFirstname2, 1.0);
    	    person2.addAttribute(attributeLastname2, 0.8);
    	    person2.addAttribute(attributeBirthdate2, 0.6, LocalDate.of(1989, 1, 7));
    	    person2.addAttribute(attributeMiddlename2, 0.9);

      for (final EntityAttribute ea : person2.getBaseEntityAttributes()) {
        session.save(ea.getAttribute());
      }
      session.save(person2);
      System.out.println(person2);

      // Create a Question to match the attribute

      // Firstname

 
      
      Question questionFirstname2 = new Question(Question.getDefaultCodePrefix() + "FIRSTNAME2", "Firstname:",
          attributeFirstname2);
      Question questionMiddlename2 = new Question(Question.getDefaultCodePrefix() + "MIDDLENAME2", "Middlename:",
              attributeMiddlename2);
      Question questionLastname2 = new Question(Question.getDefaultCodePrefix() + "LASTNAME2", "Lastname:",
          attributeLastname2);
      Question questionBirthdate2 = new Question(Question.getDefaultCodePrefix() + "BIRTHDATE2",
          "Birthdatee:", attributeBirthdate2);

      session.save(questionFirstname2);
      session.save(questionMiddlename2);
      session.save(questionLastname2);
      session.save(questionBirthdate2);

      
     List<Question> childQuestions = new ArrayList<Question>();
     childQuestions.add(questionFirstname2);
     childQuestions.add(questionLastname2);
 
     Question questionName = new Question(Question.getDefaultCodePrefix() + "NAME", "Name:",childQuestions);
     session.save(questionName);

     List<Question> childQuestions2 = new ArrayList<Question>();
     childQuestions2.add(questionMiddlename2);
     childQuestions2.add(questionBirthdate2);
     childQuestions2.add(questionName);

      Question questionName2 = new Question(Question.getDefaultCodePrefix() + "NAME2", "Name2:",childQuestions2);
      session.save(questionName2);

      // Now ask the question!

      final Ask askFirstname2 = new Ask(questionFirstname2, person2.getCode(), person2.getCode());
      final Ask askLastname2 = new Ask(questionLastname2, person2.getCode(), person2.getCode());
      final Ask askBirthdate2 = new Ask(questionBirthdate2, person2.getCode(), person2.getCode());

      session.save(askFirstname2);
      session.save(askLastname2);
      session.save(askBirthdate2);

      final Answer answerFirstname2 = new Answer(askFirstname2, "Bob");
      final Answer answerLastname2 = new Answer(askFirstname2, "Console");
      final Answer answerBirthdate2 = new Answer(askFirstname2, "1989-01-07 16:00");

      person2.addAnswer(answerFirstname2, 1.0);
      person2.addAnswer(answerLastname2, 1.0);
      person2.addAnswer(answerBirthdate2, 1.0);

      person2 = (Person) session.merge(person2);

      tx.commit();
      final String json = JsonUtils.set(askFirstname2);
      final Ask result = new JsonUtils().get(Ask.class, json);
      System.out.println(result);

      // Now fetch questionName
      Query query = session.createQuery("from Question where code = :code ");
      query.setParameter("code", Question.getDefaultCodePrefix() + "NAME");
      Question qNameQueried = (Question) query.getSingleResult();
      System.out.println("Fetched qName by code = "+qNameQueried);
      Question qName = session.find(Question.class, questionName.getId());
      System.out.println("Fetched qName by id = "+qName);
      
      // test recursive Questions
      Question qName3 = session.find(Question.class, questionName2.getId());
      System.out.println("Fetched recursive qName3 by id = "+qName3);
     

    } catch (RuntimeException | BadDataException e) {
      try {
        log.error("Error in Hibernate Class Testing", e);
        tx.rollback();
      } catch (final RuntimeException rollbackException) {
        log.error("Rollback Error", rollbackException);
      }


    } finally {
      if (session != null) {
        session.close();
      }
      System.out.println("Finished Hibernate Testing");
    }
  }



}
