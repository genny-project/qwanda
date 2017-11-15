package life.genny.qwanda.datatype;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
	@Override
	public LocalDate unmarshal(String s) throws Exception {
		return LocalDate.parse(s);
	}

	@Override
	public String marshal(LocalDate date) throws Exception {
		return date.toString();
	}
}