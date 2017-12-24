package life.genny.qwanda.converter;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {

  @Override
  public String convertToDatabaseColumn(final List<String> list) {
    String ret = "";
    if (list!=null) {
    for (final String str : list) {
      ret += str + ",";
    }
    }
    return ret;

  }

  @Override
  public List<String> convertToEntityAttribute(final String joined) {
    List<String> strings = new ArrayList<String>();
    if (joined != null) {
      strings = new ArrayList<>(Arrays.asList(joined.split(",")));
    }
    return strings;
  }

}
