package life.genny.qwanda.converter;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Arrays;
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
			String validationGroupStr = "";
			if (validation != null) {
			if (validation.getSelectionBaseEntityGroupList() != null) {
				validationGroupStr += "\"" + convertToString(validation.getSelectionBaseEntityGroupList()) + "\"";
				validationGroupStr += ",\"" + (validation.getMultiAllowed() ? "TRUE" : "FALSE") + "\"";
				validationGroupStr += ",\"" + (validation.getRecursiveGroup() ? "TRUE" : "FALSE") + "\",";
				ret += "\"" + validation.getCode() + "\",\"" + validation.getName() + "\",\"" + validation.getRegex()
				+ "\"," + validationGroupStr;
			} else {
				ret += "\"" + validation.getCode() + "\",\"" + validation.getName() + "\",\"" + validation.getRegex()+"\",";

			}
			}
		
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
			// System.out.println("ValidationStr=" + joined);
			if (!StringUtils.isBlank(joined)) {
				joined = joined.substring(1); // remove leading quotes
				joined = StringUtils.chomp(joined, "\""); // remove last char
				final String[] validationListStr = joined.split("\",\"");

				if (validationListStr.length == 6) {
					for (int i = 0; i < validationListStr.length; i = i + 6) {
						List<String> validationGroups = convertFromString(validationListStr[i + 3]);

						validations.add(new Validation(validationListStr[i], validationListStr[i + 1], validationGroups,
								validationListStr[i + 3].equalsIgnoreCase("TRUE"),
								validationListStr[i + 4].equalsIgnoreCase("TRUE")));
					}

				} else {
					for (int i = 0; i < validationListStr.length; i = i + 3) {
						validations.add(new Validation(validationListStr[i], validationListStr[i + 1],
								validationListStr[i + 2]));
					}
				}

			}
		}
		return validations;
	}

	public String convertToString(final List<String> list) {
		String ret = "";
		if (list != null) {
			for (final String str : list) {
				ret += str + ":";
			}
			ret = StringUtils.removeEnd(ret, ":");

		}
		return ret;

	}

	public List<String> convertFromString(final String joined) {
		List<String> strings = new ArrayList<String>();
		if (joined != null) {
			strings = new ArrayList<>(Arrays.asList(joined.split(":")));
		}
		return strings;
	}
}
