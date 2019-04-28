package life.genny.qwanda.entity;

import java.lang.invoke.MethodHandles;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.Logger;
import org.h2.util.DateTimeUtils;
import org.hibernate.annotations.Type;
import org.javamoney.moneta.Money;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import life.genny.qwanda.Link;
import life.genny.qwanda.MoneyDeserializer;
import life.genny.qwanda.attribute.Attribute;
import life.genny.qwanda.converter.MoneyConverter;
import life.genny.qwanda.datatype.LocalDateAdapter;
import life.genny.qwanda.datatype.LocalDateTimeAdapter;
import life.genny.qwanda.datatype.LocalTimeAdapter;

@Entity
@Table(name = "baseentity_baseentity")

@AssociationOverrides({
    @AssociationOverride(name = "pk.source", joinColumns = @JoinColumn(name = "SOURCE_ID"))})

public class EntityEntity implements java.io.Serializable, Comparable<Object> {

	/**
	 * Stores logger object.
	 */
	protected static final Logger log = org.apache.logging.log4j.LogManager
			.getLogger(MethodHandles.lookup().lookupClass().getCanonicalName());

	@AttributeOverrides({
        @AttributeOverride(name = "sourceCode", column = @Column(name = "SOURCE_CODE", nullable = false)),
        @AttributeOverride(name = "targetCode", column = @Column(name = "TARGET_CODE", nullable = false)),
        @AttributeOverride(name = "attributeCode", column = @Column(name = "LINK_CODE", nullable = false)),
        @AttributeOverride(name = "weight", column = @Column(name = "LINK_WEIGHT", nullable = false)),
        @AttributeOverride(name = "parentColour", column = @Column(name = "PARENT_COL", nullable = true)),
        @AttributeOverride(name = "childColour", column = @Column(name = "CHILD_COL", nullable = true)),
        @AttributeOverride(name = "rule", column = @Column(name = "RULE", nullable = true))
        
	})
	@Expose
	private Link link ;
	
	private String realm;
	
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @EmbeddedId
  private EntityEntityId pk = new EntityEntityId();

 

  /**
   * @return the link
   */
  public Link getLink() {
		return link;

  }

  


/**
   * Stores the Created UMT DateTime that this object was created
   */

  @Column(name = "created")
  @Expose
  private LocalDateTime created;

  /**
   * Stores the Last Modified UMT DateTime that this object was last updated
   */
  @Column(name = "updated")
  @Expose
  private LocalDateTime updated;


  /**
   * Store the Double value of the attribute for the baseEntity
   */
  @Expose
  private Double valueDouble;

  /**
   * Store the Boolean value of the attribute for the baseEntity
   */
  @Expose
  private Boolean valueBoolean;
  /**
   * Store the Integer value of the attribute for the baseEntity
   */
  @Expose
  private Integer valueInteger;

  /**
   * Store the Long value of the attribute for the baseEntity
   */
  @Expose
  private Long valueLong;

  /**
   * Store the LocalDateTime value of the attribute for the baseEntity
   */
//  @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
  @Expose
  private LocalDateTime valueDateTime;

  /**
   * Store the LocalDate value of the attribute for the baseEntity
   */
//  @XmlJavaTypeAdapter(LocalDateAdapter.class)
  @Expose
  private LocalDate valueDate;
 
  /**
   * Store the LocalTime value of the attribute for the baseEntity
   */
//  @XmlJavaTypeAdapter(LocalTimeAdapter.class)
  @Expose
  private LocalTime valueTime;
 
	@Column(name = "money", length = 128)
	@Convert(converter = MoneyConverter.class)
	@Expose
	Money valueMoney;

  /**
   * Store the String value of the attribute for the baseEntity
   */
  @Type(type="text")
  @Expose
  private String valueString;


  /**
   * Store the relative importance of the attribute for the baseEntity
   */
  @Expose
  private Double weight;

  private Long version = 1L;


  public EntityEntity() {}

  /**
   * Constructor.
   * 
   * @param source the source baseEntity
   * @param target the target entity that is linked to
   * @param linkAttribute the associated linkAttribute
   * @param Weight the weighted importance of this attribute (relative to the other attributes)
   */
  public EntityEntity(final BaseEntity source, final BaseEntity target,
      final Attribute attribute, Double weight) {
   this(source,target,attribute, "DUMMY",weight);
   this.getLink().setLinkValue(null);
   this.setValueString(null);
  }

  /**
   * Constructor.
   * 
   * @param source the source baseEntity
   * @param target the target entity that is linked to
   * @param linkAttribute the associated linkAttribute
   * @param linkValue the associated linkValue
   * @param Weight the weighted importance of this attribute (relative to the other attributes)
   */
  public EntityEntity(final BaseEntity source, final BaseEntity target,
      final Attribute attribute, final Object value, Double weight) {
    autocreateCreated();
    getPk().setSource(source);
//    getPk().setTarget(target);
    getPk().setAttribute(attribute);
    this.setRealm(target.getRealm());
//    this.pk.setSourceCode(source.getCode());
    this.pk.setTargetCode(target.getCode());
    link = new Link(source.getCode(),target.getCode(),attribute.getCode(),null);

    if (value != null) {
        setValue(value);
       }
    if (weight == null) {
      weight = 0.0; // This permits ease of adding attributes and hides
                    // attribute from scoring.
    }
    setWeight(weight);
  }

  /**
   * Constructor.
   * 
   * @param BaseEntity the entity that needs to contain attributes
   * @param Attribute the associated Attribute
   * @param linkAttribute the associated linkAttribute
   * @param Weight the weighted importance of this attribute (relative to the other attributes)
   * @param Value the value associated with this attribute
   */
  public EntityEntity(final BaseEntity source, final BaseEntity target,
      final Attribute attribute, Double weight, final Object value) {
    autocreateCreated();
 
    this.pk.setSource(source);
 //   this.pk.setTarget(target);
    this.pk.setTargetCode(target.getCode());
    this.pk.setAttribute(attribute);

    link = new Link(source.getCode(),target.getCode(),attribute.getCode());

    if (weight == null) {
      weight = 0.0; // This permits ease of adding attributes and hides
                    // attribute from scoring.
    }
    setWeight(weight);
    if (value != null) {
        setValue(value);
       }
  }

  @JsonIgnore
  public EntityEntityId getPk() {
    return pk;
  }

  public void setPk(final EntityEntityId pk) {
    this.pk = pk;
  }


  /**
   * @return the created
   */
  public LocalDateTime getCreated() {
    return created;
  }

  /**
   * @param created the created to set
   */
  public void setCreated(final LocalDateTime created) {
    this.created = created;
  }

  /**
   * @return the updated
   */
  public LocalDateTime getUpdated() {
    return updated;
  }

  /**
   * @param updated the updated to set
   */
  public void setUpdated(final LocalDateTime updated) {
    this.updated = updated;
  }

  /**
   * @return the weight
   */
  public Double getWeight() {
    return weight;
  }

  /**
   * @param weight the weight to set
   */
  public void setWeight(final Double weight) {
    this.weight = weight;
    this.link.setWeight(weight);
  }

  /**
   * @return the version
   */
  public Long getVersion() {
    return version;
  }

  /**
   * @param version the version to set
   */
  public void setVersion(final Long version) {
    this.version = version;
  }



  /**
   * @return the valueDouble
   */
  public Double getValueDouble() {
    return valueDouble;
  }

  /**
   * @param valueDouble the valueDouble to set
   */
  public void setValueDouble(final Double valueDouble) {
    this.valueDouble = valueDouble;
  }

  /**
   * @return the valueInteger
   */
  public Integer getValueInteger() {
    return valueInteger;
  }

  /**
   * @param valueInteger the valueInteger to set
   */
  public void setValueInteger(final Integer valueInteger) {
    this.valueInteger = valueInteger;
  }

  /**
   * @return the valueLong
   */
  public Long getValueLong() {
    return valueLong;
  }

  /**
   * @param valueLong the valueLong to set
   */
  public void setValueLong(final Long valueLong) {
    this.valueLong = valueLong;
  }

  /**
   * @return the valueDateTime
   */
  public LocalDateTime getValueDateTime() {
    return valueDateTime;
  }

  /**
   * @param valueDateTime the valueDateTime to set
   */
  public void setValueDateTime(final LocalDateTime valueDateTime) {
    this.valueDateTime = valueDateTime;
  }

  /**
   * @return the valueString
   */
  public String getValueString() {
    return valueString;
  }

  /**
   * @param valueString the valueString to set
   */
  public void setValueString(final String valueString) {
    this.valueString = valueString;
  }

  
  
  /**
 * @return the valueTime
 */
public LocalTime getValueTime() {
	return valueTime;
}

/**
 * @param valueTime the valueTime to set
 */
public void setValueTime(LocalTime valueTime) {
	this.valueTime = valueTime;
}



/**
 * @return the valueMoney
 */
public Money getValueMoney() {
	return valueMoney;
}

/**
 * @param valueMoney the valueMoney to set
 */
public void setValueMoney(Money valueMoney) {
	this.valueMoney = valueMoney;
}

/**
 * @param link the link to set
 */
public void setLink(Link link) {
	this.link = link;
}

@PreUpdate
  public void autocreateUpdate() {
    setUpdated(LocalDateTime.now(ZoneId.of("Z")));
  }

  @PrePersist
  public void autocreateCreated() {
    if (getCreated() == null)
      setCreated(LocalDateTime.now(ZoneId.of("Z")));
  }

  @Transient
  public Date getCreatedDate() {
    final Date out = Date.from(created.atZone(ZoneId.systemDefault()).toInstant());
    return out;
  }

  @Transient
  public Date getUpdatedDate() {
	  if (updated!=null) {
    final Date out = Date.from(updated.atZone(ZoneId.systemDefault()).toInstant());
    return out;
	  } else {
		  return null;
	  }
  }



	 public int compareTo(Object o) {
		 EntityEntity myClass = (EntityEntity) o;
	     return new CompareToBuilder()
//	       .appendSuper(super.compareTo(o)
	       .append(this.weight, myClass.weight)
	       .toComparison();
	   }

 
  
  
  @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + ((valueString == null) ? 0 : valueString.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntityEntity other = (EntityEntity) obj;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (valueString == null) {
			if (other.valueString != null)
				return false;
		} else if (!valueString.equals(other.valueString))
			return false;
		return true;
	}

@JsonIgnore
  @Transient
  @XmlTransient
  public <T> T getValue() {
    final String dataType = getPk().getAttribute().getDataType().getClassName();
    switch (dataType) {
      case "java.lang.Integer":
        return (T) getValueInteger();
      case "java.time.LocalDateTime":
        return (T) getValueDateTime();
      case "java.time.LocalTime":
          return (T) getValueTime();
      case "java.lang.Long":
        return (T) getValueLong();
      case "java.lang.Double":
        return (T) getValueDouble();
      case "java.lang.Boolean":
          return (T) getValueBoolean();
      case "java.time.LocalDate":
          return (T) getValueDate();

      case "java.lang.String":
      default:
        return (T) getValueString();
    }

  }
  
  /**
 * @return the valueBoolean
 */
public Boolean getValueBoolean() {
	return valueBoolean;
}

/**
 * @param valueBoolean the valueBoolean to set
 */
public void setValueBoolean(Boolean valueBoolean) {
	this.valueBoolean = valueBoolean;
}

/**
 * @return the valueDate
 */
public LocalDate getValueDate() {
	return valueDate;
}

/**
 * @param valueDate the valueDate to set
 */
public void setValueDate(LocalDate valueDate) {
	this.valueDate = valueDate;
}

@JsonIgnore
@Transient
@XmlTransient
public <T> void setValue(final Object value) {

	if (value instanceof String) {
		String result = (String) value;
		try {
			if (getPk().getAttribute().getDataType().getClassName().equalsIgnoreCase(String.class.getCanonicalName())) {
				setValueString(result);
			} else if (getPk().getAttribute().getDataType().getClassName()
					.equalsIgnoreCase(LocalDateTime.class.getCanonicalName())) {
				List<String> formatStrings = Arrays.asList("yyyy-MM-dd", "yyyy-MM-dd'T'HH:mm:ss",
						"yyyy-MM-dd'T'HH:mm:ss.SSSZ");
				for (String formatString : formatStrings) {
					try {
						Date olddate = new SimpleDateFormat(formatString).parse(result);
						final LocalDateTime dateTime = olddate.toInstant().atZone(ZoneId.systemDefault())
								.toLocalDateTime();
						setValueDateTime(dateTime);
						break;
					} catch (ParseException e) {
					}

				}
				// Date olddate = null;
				// olddate = DateTimeUtils.parseDateTime(result,
				// "yyyy-MM-dd","yyyy-MM-dd'T'HH:mm:ss","yyyy-MM-dd'T'HH:mm:ss.SSSZ");
				// final LocalDateTime dateTime =
				// olddate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
				// setValueDateTime(dateTime);
			} else if (getPk().getAttribute().getDataType().getClassName()
					.equalsIgnoreCase(LocalDate.class.getCanonicalName())) {
				Date olddate = null;
				try {
					olddate = DateUtils.parseDate(result, "M/y", "yyyy-MM-dd", "yyyy/MM/dd",
							"yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSSZ");
				} catch (java.text.ParseException e) {
					olddate = DateTimeUtils.parseDateTime(result, "yyyy-MM-dd", "yyyy-MM-dd'T'HH:mm:ss",
							"yyyy-MM-dd'T'HH:mm:ss.SSSZ");
				}
				final LocalDate date = olddate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				setValueDate(date);
			} else if (getPk().getAttribute().getDataType().getClassName()
					.equalsIgnoreCase(LocalTime.class.getCanonicalName())) {
				final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
				final LocalTime date = LocalTime.parse(result, formatter);
				setValueTime(date);
			} else if (getPk().getAttribute().getDataType().getClassName()
					.equalsIgnoreCase(Money.class.getCanonicalName())) {
				GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(Money.class, new MoneyDeserializer());
				Gson gson = gsonBuilder.create();
				Money money = gson.fromJson(result, Money.class);
				setValueMoney(money);
			} else if (getPk().getAttribute().getDataType().getClassName()
					.equalsIgnoreCase(Integer.class.getCanonicalName())) {
				final Integer integer = Integer.parseInt(result);
				setValueInteger(integer);
			} else if (getPk().getAttribute().getDataType().getClassName()
					.equalsIgnoreCase(Double.class.getCanonicalName())) {
				final Double d = Double.parseDouble(result);
				setValueDouble(d);
			} else if (getPk().getAttribute().getDataType().getClassName()
					.equalsIgnoreCase(Long.class.getCanonicalName())) {
				final Long l = Long.parseLong(result);
				setValueLong(l);
			} else if (getPk().getAttribute().getDataType().getClassName()
					.equalsIgnoreCase(Boolean.class.getCanonicalName())) {
				final Boolean b = Boolean.parseBoolean(result);
				setValueBoolean(b);
			} else {
				setValueString(result);
			}
		} catch (Exception e) {
			log.error("Conversion Error :" + value + " for attribute " + getPk().getAttribute() + " and SourceCode:"
					+ this.getPk().getSource().getCode());
		}
	} else {

		switch (this.getPk().getAttribute().getDataType().getClassName()) {
		case "java.lang.Integer":
		case "Integer":
			setValueInteger((Integer) value);
			break;
		case "java.time.LocalDateTime":
		case "LocalDateTime":
			setValueDateTime((LocalDateTime) value);
			break;
		case "java.time.LocalDate":
		case "LocalDate":
			setValueDate((LocalDate) value);
			break;
		case "java.lang.Long":
		case "Long":
			setValueLong((Long) value);
			break;
		case "java.time.LocalTime":
		case "LocalTime":
			setValueTime((LocalTime) value);
			break;
		case "org.javamoney.moneta.Money":
		case "Money":
			setValueMoney((Money) value);
			break;
		case "java.lang.Double":
		case "Double":
			setValueDouble((Double) value);
			break;
		case "java.lang.Boolean":
		case "Boolean":
			setValueBoolean((Boolean) value);
			break;

		case "java.lang.String":
		default:
			setValueString((String) value);
			break;
		}
	}

	this.link.setLinkValue(getObjectAsString(getValue()));
}

@JsonIgnore
@Transient
@XmlTransient
public String getAsString() {
	final String dataType = getPk().getAttribute().getDataType().getClassName();
	switch (dataType) {
	case "java.lang.Integer":
		return "" + getValueInteger();
	case "java.time.LocalDateTime":
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
		Date datetime = Date.from(getValueDateTime().atZone(ZoneId.systemDefault()).toInstant());
		String dout = df.format(datetime);
		return dout;
	case "java.lang.Long":
		return "" + getValueLong();
	case "java.time.LocalTime":
		return getValueTime().toString();
	case "org.javamoney.moneta.Money":
		return getValueMoney().toString();

	case "java.lang.Double":
		return getValueDouble().toString();
	case "java.lang.Boolean":
		return getValueBoolean() ? "TRUE" : "FALSE";
	case "java.time.LocalDate":
		DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
		Date date = Date.from(getValueDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
		String dout2 = df2.format(date);
		return dout2;

	case "java.lang.String":
	default:
		return getValueString();
	}

}


@JsonIgnore
@Transient
@XmlTransient
public String getObjectAsString(Object value) {
    if (value instanceof Integer)
      return ""+value;
    if (value instanceof LocalDateTime) {
    		LocalDateTime val = (LocalDateTime)value;
  	  	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS"); 
  	  	Date datetime = Date.from(val.atZone(ZoneId.systemDefault()).toInstant());
  	  	String dout = df.format(datetime);
  	  	return dout;
    }
    if (value instanceof Long)
      return ""+value;
    if (value instanceof Double) {
    	 Double val = (Double) value;
      return val.toString();
    }
    if (value instanceof Boolean) {
    	Boolean val = (Boolean)value;
        return val?"TRUE":"FALSE";
    }
    if (value instanceof LocalDate) {
    		LocalDate val = (LocalDate)value;
   	  	DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd"); 
   	  	Date date = Date.from(val.atStartOfDay(ZoneId.systemDefault()).toInstant());
  	  	String dout2 = df2.format(date);
  	  	return dout2;
    }

    if (value instanceof Money) {
		Money val = (Money)value;
	  	String dout2 = val.toString();
	  	return dout2;
}
    if (value instanceof LocalTime) {
		LocalTime val = (LocalTime)value;
	  	String dout2 = val.toString();
	  	return dout2;
}
    	String val = (String)value;
      return val;


}

/**
 * @return the realm
 */
public String getRealm() {
	return realm;
}

/**
 * @param realm the realm to set
 */
public void setRealm(String realm) {
	this.realm = realm;
}


}
