package life.genny.test;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.junit.Test;

import life.genny.qwanda.converter.ValidationListConverter;
import life.genny.qwanda.validation.Validation;

public class AttributeTest {
	/**
	 * Stores logger object.
	 */
	protected static final Logger log = org.apache.logging.log4j.LogManager
			.getLogger(MethodHandles.lookup().lookupClass().getCanonicalName());

	@Test
	public void validationTest()
	{
		
		ValidationListConverter vc = new ValidationListConverter();
		List<Validation> validationList = vc.convertToEntityAttribute("\"VLD_EMAIL\",\"Email\",\"^(\\w[-._+\\w]*\\w@\\w[-._\\w]*\\w\\.\\w{2,20})$\",\"[\"\"]\",\"FALSE\",\"FALSE\"");
		log.info(validationList);;
	}
}
