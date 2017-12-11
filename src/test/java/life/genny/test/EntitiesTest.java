package life.genny.test;


import java.lang.invoke.MethodHandles;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class EntitiesTest extends TestCase {

	/**
	 * 
	 * Stores logger object.
	 */
	private static final Logger log = org.apache.logging.log4j.LogManager
			.getLogger(MethodHandles.lookup().lookupClass().getCanonicalName());

	   /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public EntitiesTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( EntitiesTest.class );
    }

    /**
     * Rigorous Test :-)
     */
    public void testApp()
    {
    	String hibernateConfigFile = System.getenv("HIBERNATE_TEST_FILE");
    	if (StringUtils.isBlank(hibernateConfigFile)) {
    		hibernateConfigFile = "hibernate.cfg.xml";
    	}
    	log.info("Using "+hibernateConfigFile);
    	ClassTest.entityTest(hibernateConfigFile);
    		
        assertTrue( true );
    }
}
