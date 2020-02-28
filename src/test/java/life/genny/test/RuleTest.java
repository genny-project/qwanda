package life.genny.test;

import java.lang.invoke.MethodHandles;

import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import life.genny.qwanda.exception.BadDataException;
import life.genny.qwanda.rule.Rule;
import life.genny.test.qwanda.util.JsonUtils;


public class RuleTest {

	/**
	 * Stores logger object.
	 */
	protected static final Logger log = org.apache.logging.log4j.LogManager
			.getLogger(MethodHandles.lookup().lookupClass().getCanonicalName());

	
	@Before
	public void setup() throws BadDataException {
		

	}

	@After
	public void tearDown() {
	}
	

	
 @Test
 public void jsonLocalDateTimeTest()
 {
	 Rule rule = new Rule("RUL_RULE1","Rule 1","This is a rule");
	 
	 String jsonString = JsonUtils.set(rule);
	 log.info("Json Rule = "+jsonString);
 }
}
