package life.genny.test;

import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;

import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import life.genny.qwanda.Answer;
import life.genny.qwanda.Ask;
import life.genny.qwanda.Question;
import life.genny.qwanda.attribute.Attribute;
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
		
		attributeFirstname = new AttributeText(AttributeText.getDefaultCodePrefix()+"FIRSTNAME_TEST","Firstname");
		attributeLastname = new AttributeText(AttributeText.getDefaultCodePrefix()+"LASTNAME_TEST","Surname");
		attributeBirthdate = new AttributeDateTime(AttributeText.getDefaultCodePrefix()+"BIRTHDAY","Date of Birth");
		
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
	public void entityAttributeTest()
	{	
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory(hibernate_h2_cfg);
		 
		Session session = null;
		

		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			tx.setTimeout(30);

			for (EntityAttribute ea : person.getBaseEntityAttributes()) {
				session.save(ea.getAttribute());
			}
			session.save(person);
			System.out.println(person);
			
			// Create a Question to match the attribute
			
			// Firstname
			
			questionFirstname = new Question(Question.getDefaultCodePrefix()+"FIRSTNAME1", "Firstname:", attributeFirstname);
			questionLastname = new Question(Question.getDefaultCodePrefix()+"LASTNAME1", "Lastname:", attributeLastname);
			questionBirthdate = new Question(Question.getDefaultCodePrefix()+"BIRTHDATE1", "Birthdatee:", attributeBirthdate);
			
			session.save(questionFirstname);
			session.save(questionLastname);
			session.save(questionBirthdate);
			
			// Now ask the question!
			
			Ask askFirstname = new Ask(questionFirstname,person,person);
			Ask askLastname = new Ask(questionLastname,person,person);
			Ask askBirthdate = new Ask(questionBirthdate,person,person);
			
			session.save(askFirstname);
			session.save(askLastname);
			session.save(askBirthdate);
			
			Answer answerFirstname = new Answer(askFirstname,"Bob");
			Answer answerLastname = new Answer(askFirstname,"Console");
			Answer answerBirthdate = new Answer(askFirstname,"1989-01-07 16:00");
			
			person.addAnswer(answerFirstname, 1.0);
			person.addAnswer(answerLastname, 1.0);
			person.addAnswer(answerBirthdate, 1.0);
			
			person = (Person) session.merge(person);
			
			tx.commit();
			String json = JsonUtils.set(askFirstname);
			Ask result = new JsonUtils().get(Ask.class, json);
			System.out.println(result);
			
			

		} catch (RuntimeException | BadDataException e) {
			try {
				log.error("Error in Hibernate Class Testing", e);
				tx.rollback();
			} catch (RuntimeException rollbackException) {
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
