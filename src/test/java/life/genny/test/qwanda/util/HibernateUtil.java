package life.genny.test.qwanda.util;

import java.lang.invoke.MethodHandles;

import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import life.genny.qwanda.Answer;
import life.genny.qwanda.AnswerLink;
import life.genny.qwanda.AnswerList;
import life.genny.qwanda.Ask;
import life.genny.qwanda.CodedEntity;
import life.genny.qwanda.Context;
import life.genny.qwanda.ContextList;
import life.genny.qwanda.CoreEntity;
import life.genny.qwanda.Question;
import life.genny.qwanda.QuestionQuestion;
import life.genny.qwanda.attribute.Attribute;
import life.genny.qwanda.attribute.AttributeBoolean;
import life.genny.qwanda.attribute.AttributeDate;
import life.genny.qwanda.attribute.AttributeDateTime;
import life.genny.qwanda.attribute.AttributeDouble;
import life.genny.qwanda.attribute.AttributeInteger;
import life.genny.qwanda.attribute.AttributeList;
import life.genny.qwanda.attribute.AttributeText;
import life.genny.qwanda.attribute.EntityAttribute;
import life.genny.qwanda.attribute.EntityAttributeId;
import life.genny.qwanda.datatype.DataType;
import life.genny.qwanda.entity.BaseEntity;
import life.genny.qwanda.entity.Company;
import life.genny.qwanda.entity.EntityEntity;
import life.genny.qwanda.entity.EntityEntityId;
import life.genny.qwanda.entity.Person;
import life.genny.qwanda.entity.Product;
import life.genny.qwanda.rule.Rule;
import life.genny.qwanda.rule.RuleDrool;
import life.genny.qwanda.rule.RuleSpreadsheet;
import life.genny.qwanda.rule.RuleWorkflow;
import life.genny.qwanda.validation.Validation;

public class HibernateUtil {
	/**
	 * 
	 * Stores logger object.
	 */
	private static final Logger log = org.apache.logging.log4j.LogManager
			.getLogger(MethodHandles.lookup().lookupClass().getCanonicalName());

	
	private static SessionFactory sessionFactory;
    
    public static SessionFactory getSessionFactory(String hibernateFile) {
        if (sessionFactory == null) {
            // loads configuration and mappings
//            Configuration configuration = new Configuration().configure(
//					);
            
            Configuration configuration = new Configuration()
            		.addAnnotatedClass(BaseEntity.class)
            		.addAnnotatedClass(DataType.class)
            		.addAnnotatedClass(Attribute.class)
            		.addAnnotatedClass(AttributeText.class)
            		.addAnnotatedClass(AttributeInteger.class)
            		.addAnnotatedClass(AttributeDate.class)
            		.addAnnotatedClass(AttributeDateTime.class)
            		.addAnnotatedClass(AttributeDouble.class)
            		.addAnnotatedClass(AttributeBoolean.class)
            		.addAnnotatedClass(EntityAttribute.class)
            		.addAnnotatedClass(EntityAttributeId.class)
            		.addAnnotatedClass(Rule.class)
            		.addAnnotatedClass(RuleDrool.class)
            		.addAnnotatedClass(RuleWorkflow.class)
            		.addAnnotatedClass(RuleSpreadsheet.class)
            		.addAnnotatedClass(CoreEntity.class)
            		.addAnnotatedClass(CodedEntity.class)
            		.addAnnotatedClass(Answer.class)
            		.addAnnotatedClass(AnswerLink.class)
            		.addAnnotatedClass(AnswerList.class)
            		.addAnnotatedClass(Ask.class)
            		.addAnnotatedClass(ContextList.class)
            		.addAnnotatedClass(Context.class)
            		.addAnnotatedClass(Question.class)            		
            		.addAnnotatedClass(QuestionQuestion.class)
            		.addAnnotatedClass(Person.class)
            		.addAnnotatedClass(Company.class)
            		.addAnnotatedClass(Product.class)
            		.addAnnotatedClass(AttributeText.class)
            		.addAnnotatedClass(Validation.class)
            		.addAnnotatedClass(java.util.HashSet.class)
            		.addAnnotatedClass(EntityEntity.class)
            		.addAnnotatedClass(EntityEntityId.class)
            	
            .addAnnotatedClass(AttributeList.class);
            configuration.configure(hibernateFile);
            
            ServiceRegistry serviceRegistry
                = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
             
            // builds a session factory from the service registry
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);           
        }
         
        return sessionFactory;
    }
}
