package life.genny.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import life.genny.qwanda.exception.BadDataException;
import life.genny.qwanda.rule.Rule;
import life.genny.test.qwanda.util.JsonUtils;


public class RuleTest {

	
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
	 System.out.println("Json Rule = "+jsonString);
 }
}
