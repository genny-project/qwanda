package life.genny.qwanda.entity;

import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Convert;

import org.apache.logging.log4j.Logger;
import org.javamoney.moneta.Money;

import com.google.gson.annotations.Expose;

import life.genny.qwanda.Ask;
import life.genny.qwanda.Link;
import life.genny.qwanda.attribute.Attribute;
import life.genny.qwanda.converter.MoneyConverter;

public class EntityQuestion implements java.io.Serializable, Comparable<Object> {

	private static final long serialVersionUID = 1L;

	protected static final Logger log = org.apache.logging.log4j.LogManager
			.getLogger(MethodHandles.lookup().lookupClass().getCanonicalName());

  @Expose
  private String valueString;

  @Expose
  private Double weight;

  @Expose 
	private Link link;

  public EntityQuestion() {}

  public EntityQuestion(Link link) {
    this.link = link;
  }

	@Override
	public int compareTo(Object o) {
		return 0;
	}
}
