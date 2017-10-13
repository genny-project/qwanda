package life.genny.qwanda.converter;


import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import life.genny.qwanda.validation.Validation;

@Converter
public class ValidationListConverter implements AttributeConverter<List<Validation>, String> {

  private static final Logger log = org.apache.logging.log4j.LogManager
      .getLogger(MethodHandles.lookup().lookupClass().getCanonicalName());

  @Override
  public String convertToDatabaseColumn(final List<Validation> list) {
    String ret = "";
    for (final Validation validation : list) {
      ret += "\"" + validation.getCode() + "\",\"" + validation.getName() + "\",\""
          + validation.getRegex() + "\",";
    }
    ret = StringUtils.removeEnd(ret, ",");
    if (ret.length() >= 512) {
      log.error("Error -> field > 512 " + ret + ":" + ret.length());
    }

    return ret;

  }

  @Override
  public List<Validation> convertToEntityAttribute(String joined) {
    final List<Validation> validations = new ArrayList<Validation>();
    if (joined != null) {
      System.out.println("ValidationStr=" + joined);
      if (!StringUtils.isBlank(joined)) {
        joined = joined.substring(1); // remove leading quotes
        joined = StringUtils.chomp(joined, "\""); // remove last char
        final String[] validationListStr = joined.split("\",\"");
        for (int i = 0; i < validationListStr.length; i = i + 3)
          validations.add(new Validation(validationListStr[i], validationListStr[i + 1],
              validationListStr[i + 2]));
      }
    }
    return validations;
  }

}
