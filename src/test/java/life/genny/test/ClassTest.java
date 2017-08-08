package life.genny.test;

import java.lang.invoke.MethodHandles;
import java.util.regex.PatternSyntaxException;

import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Assert;

import life.genny.qwanda.attribute.Attribute;
import life.genny.qwanda.attribute.AttributeText;
import life.genny.qwanda.entity.BaseEntity;
import life.genny.qwanda.entity.Company;
import life.genny.qwanda.entity.Person;
import life.genny.qwanda.entity.Product;
import life.genny.qwanda.exception.BadDataException;
import life.genny.qwanda.rule.Rule;
import life.genny.qwanda.validation.Validation;
import life.genny.test.qwanda.util.HibernateUtil;


public class ClassTest {
	
	static public final String hibernate_h2_cfg = "hibernate.cfg.xml";
	static public final String hibernate_mysql_cfg = "hibernate-mysql.cfg.xml";

	/**
	 * 
	 * Stores logger object.
	 */
	private static final Logger log = org.apache.logging.log4j.LogManager
			.getLogger(MethodHandles.lookup().lookupClass().getCanonicalName());

	public static void main(String[] args) {
		entityTest();
	}

	public static void entityTest()
	{
		entityTest(hibernate_h2_cfg);
	}
	
	public static void entityTest(String hibernateFile) {
	
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory(hibernateFile);
		 
		Session session = null;
		

		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			tx.setTimeout(30);

			// Entities
			
			BaseEntity be = new BaseEntity("Test BaseEntity");
			session.save(be);
			
			Person edison = new Person("Thomas Edison");
		//	session.save(edison);

			Person tesla = new Person("Nikola Tesla");
			session.save(tesla);

			Company crowtech = new Company("crowtech","Crowtech Pty Ltd");
			session.save(crowtech);
			
			Company spacex = new Company("spacex","SpaceX");
			session.save(spacex);
			
			
			Product bmw316i = new Product("bmw316i","BMW 316i");
			session.save(bmw316i);

			Product mazdaCX5 = new Product("maxdacx5","Mazda CX-5");
			session.save(mazdaCX5);
			
			Product fetchedMazda = session.find(Product.class, mazdaCX5.getId());
			
			Validation validation1 = null;
			try {
				validation1 = new Validation(Validation.getDefaultCodePrefix()+"TEST","Test",".*");
				session.save(validation1);
			} catch (PatternSyntaxException e) {
				log.error("Pattern Syntax Exception");
				Assert.assertTrue("Pattern Syntax Exception", false);
			}
			
			// Should fail
			Validation validationBadRegex = null;
			try {
				validationBadRegex = new Validation(Validation.getDefaultCodePrefix()+"BADREGEX","Test","][");
				log.error("Pattern Syntax Exception should have occured");
				Assert.assertTrue("Pattern Syntax Exception", false);
				session.save(validationBadRegex);
			} catch (PatternSyntaxException e) {
				log.info("Pattern Syntax Exception occured as expected");
			}
			
			Attribute emailAttribute = new AttributeText(Attribute.getDefaultCodePrefix()+"EMAIL","Email");
			session.save(emailAttribute);
			
			Double emailWeight = 1.0;
			
			try {
				edison.addAttribute(emailAttribute, emailWeight);
		//		session.save(edison.getBaseEntityAttributes());
				session.save(edison);
				
				// test retrieval
				Person fetchedEdison = session.find(Person.class, edison.getId());
				System.out.println(fetchedEdison);
				
				Rule rule1 = new Rule("RUL_RULE1","Test Rule 1","This is a bad format rule");
				session.save(rule1);
				
				
			} catch (BadDataException e) {
				log.error("Unable to add emailAttribute to edison ");
				Assert.assertTrue("Bad Data Exception", false);
				
			}
			
			System.out.println(fetchedMazda);
			tx.commit();

		} catch (RuntimeException e) {
			try {
				log.error("Error in Hibernate Class Testing", e);
				tx.rollback();
			} catch (RuntimeException rollbackException) {
				log.error("Rollback Error", rollbackException);
			}
				throw e;

		} finally {
			if (session != null) {
				session.close();
			}
			System.out.println("Finished Hibernate Testing");
		}
	}
}
