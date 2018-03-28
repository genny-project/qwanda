package life.genny.test;

import java.util.List;

import org.junit.Test;

import life.genny.qwanda.converter.ValidationListConverter;
import life.genny.qwanda.validation.Validation;

public class AttributeTest {
	@Test
	public void validationTest()
	{
		
		ValidationListConverter vc = new ValidationListConverter();
		List<Validation> validationList = vc.convertToEntityAttribute("\"VLD_EMAIL\",\"Email\",\"^(\\w[-._+\\w]*\\w@\\w[-._\\w]*\\w\\.\\w{2,20})$\",\"[\"\"]\",\"FALSE\",\"FALSE\"");
		System.out.println(validationList);;
	}
}
