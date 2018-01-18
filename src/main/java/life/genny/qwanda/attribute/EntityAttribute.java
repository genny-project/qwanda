package life.genny.qwanda.attribute;

import java.lang.invoke.MethodHandles;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
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
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.Logger;
import org.h2.util.DateTimeUtils;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;

import life.genny.qwanda.datatype.LocalTimeAdapter;
import life.genny.qwanda.datatype.LocalDateAdapter;
import life.genny.qwanda.datatype.LocalDateTimeAdapter;
import life.genny.qwanda.entity.BaseEntity;

@Entity
// #@JsonFilter("EntityAttribute")
@Table(name = "baseentity_attribute")
@AssociationOverrides({ @AssociationOverride(name = "pk.baseEntity", joinColumns = @JoinColumn(name = "BASEENTITY_ID")),
		@AssociationOverride(name = "pk.attribute", joinColumns = @JoinColumn(name = "ATTRIBUTE_ID")) })
public class EntityAttribute implements java.io.Serializable, Comparable<Object> {

	/**
	 * Stores logger object.
	 */
	protected static final Logger log = org.apache.logging.log4j.LogManager
			.getLogger(MethodHandles.lookup().lookupClass().getCanonicalName());

	private static final long serialVersionUID = 1L;
	@Expose
	private String baseEntityCode;
	@Expose
	private String attributeCode;

	/**
	 * @return the baseEntityCode
	 */
	public String getBaseEntityCode() {
		return baseEntityCode;
	}

	/**
	 * @param baseEntityCode
	 *            the baseEntityCode to set
	 */
	public void setBaseEntityCode(final String baseEntityCode) {
		this.baseEntityCode = baseEntityCode;
	}

	/**
	 * @return the attributeCode
	 */
	public String getAttributeCode() {
		return attributeCode;
	}

	/**
	 * @param attributeCode
	 *            the attributeCode to set
	 */
	public void setAttributeCode(final String attributeCode) {
		this.attributeCode = attributeCode;
	}

	@EmbeddedId
	public EntityAttributeId pk = new EntityAttributeId();

	/**
	 * Stores the Created UMT DateTime that this object was created
	 */
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	@Column(name = "created")
	private LocalDateTime created;

	/**
	 * Stores the Last Modified UMT DateTime that this object was last updated
	 */
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	@Column(name = "updated")
	private LocalDateTime updated;

	/**
	 * The following fields can be subclassed for better abstraction
	 */

	/**
	 * Store the Double value of the attribute for the baseEntity
	 */
	private Double valueDouble;

	/**
	 * Store the Boolean value of the attribute for the baseEntity
	 */
	private Boolean valueBoolean;
	/**
	 * Store the Integer value of the attribute for the baseEntity
	 */
	private Integer valueInteger;

	/**
	 * Store the Long value of the attribute for the baseEntity
	 */
	private Long valueLong;

	/**
	 * Store the LocalDateTime value of the attribute for the baseEntity
	 */
	@XmlJavaTypeAdapter(LocalTimeAdapter.class)
	private LocalTime valueTime;

	/**
	 * Store the LocalDateTime value of the attribute for the baseEntity
	 */
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime valueDateTime;

	/**
	 * Store the LocalDate value of the attribute for the baseEntity
	 */
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	private LocalDate valueDate;

	/**
	 * Store the String value of the attribute for the baseEntity
	 */
	@Type(type = "text")
	private String valueString;

	/**
	 * Store the relative importance of the attribute for the baseEntity
	 */
	private Double weight;

	/**
	 * Store the relative importance of the attribute for the baseEntity
	 */
	private Boolean inferred = false;

	/**
	 * Store the privacy of this attribute , i.e. Don't display
	 */
	private Boolean privacyFlag = false;

	// @Version
	// private Long version = 1L;

	public EntityAttribute() {
	}

	/**
	 * Constructor.
	 * 
	 * @param BaseEntity
	 *            the entity that needs to contain attributes
	 * @param Attribute
	 *            the associated Attribute
	 * @param Weight
	 *            the weighted importance of this attribute (relative to the other
	 *            attributes)
	 */
	public EntityAttribute(final BaseEntity baseEntity, final Attribute attribute, Double weight) {
		autocreateCreated();
		setBaseEntity(baseEntity);
		setAttribute(attribute);
		if (weight == null) {
			weight = 0.0; // This permits ease of adding attributes and hides
							// attribute from scoring.
		}
		setWeight(weight);
	}

	/**
	 * Constructor.
	 * 
	 * @param BaseEntity
	 *            the entity that needs to contain attributes
	 * @param Attribute
	 *            the associated Attribute
	 * @param Weight
	 *            the weighted importance of this attribute (relative to the other
	 *            attributes)
	 * @param Value
	 *            the value associated with this attribute
	 */
	public EntityAttribute(final BaseEntity baseEntity, final Attribute attribute, Double weight, final Object value) {
		autocreateCreated();
		setBaseEntity(baseEntity);
		setAttribute(attribute);
		if (weight == null) {
			weight = 0.0; // This permits ease of adding attributes and hides
							// attribute from scoring.
		}
		setWeight(weight);
		// Assume that Attribute Validation has been performed
		if (value != null) {
			setValue(value);
		}
	}

	@JsonIgnore
	public EntityAttributeId getPk() {
		return pk;
	}

	public void setPk(final EntityAttributeId pk) {
		this.pk = pk;
	}

	// @Transient
	// @JsonIgnore
	// @XmlTransient
	// public BaseEntity getBaseEntity() {
	// return getPk().getBaseEntity();
	// }

	public void setBaseEntity(final BaseEntity baseEntity) {
		getPk().setBaseEntity(baseEntity);
		this.baseEntityCode = baseEntity.getCode();
	}

	@Transient
	// @JsonIgnore
	public Attribute getAttribute() {
		return getPk().getAttribute();
	}

	public void setAttribute(final Attribute attribute) {
		getPk().setAttribute(attribute);
		this.attributeCode = attribute.getCode();
	}

	/**
	 * @return the created
	 */
	public LocalDateTime getCreated() {
		return created;
	}

	/**
	 * @param created
	 *            the created to set
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
	 * @param updated
	 *            the updated to set
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
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(final Double weight) {
		this.weight = weight;
	}

	// /**
	// * @return the version
	// */
	// public Long getVersion() {
	// return version;
	// }
	//
	// /**
	// * @param version the version to set
	// */
	// public void setVersion(final Long version) {
	// this.version = version;
	// }

	/**
	 * @return the valueDouble
	 */
	public Double getValueDouble() {
		return valueDouble;
	}

	/**
	 * @param valueDouble
	 *            the valueDouble to set
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
	 * @param valueInteger
	 *            the valueInteger to set
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
	 * @param valueLong
	 *            the valueLong to set
	 */
	public void setValueLong(final Long valueLong) {
		this.valueLong = valueLong;
	}

	public LocalDate getValueDate() {
		return valueDate;
	}

	public void setValueDate(LocalDate valueDate) {
		this.valueDate = valueDate;
	}

	/**
	 * @return the valueDateTime
	 */
	public LocalDateTime getValueDateTime() {
		return valueDateTime;
	}

	/**
	 * @param valueDateTime
	 *            the valueDateTime to set
	 */
	public void setValueDateTime(final LocalDateTime valueDateTime) {
		this.valueDateTime = valueDateTime;
	}

	/**
	 * @return the valueTime
	 */
	public LocalTime getValueTime() {
		return valueTime;
	}

	/**
	 * @param valueTime
	 *            the valueTime to set
	 */
	public void setValueTime(LocalTime valueTime) {
		this.valueTime = valueTime;
	}

	/**
	 * @return the valueString
	 */
	public String getValueString() {
		return valueString;
	}

	/**
	 * @param valueString
	 *            the valueString to set
	 */
	public void setValueString(final String valueString) {
		this.valueString = valueString;
	}

	public Boolean getValueBoolean() {
		return valueBoolean;
	}

	public void setValueBoolean(Boolean valueBoolean) {
		this.valueBoolean = valueBoolean;
	}

	/**
	 * @return the privacyFlag
	 */
	public Boolean getPrivacyFlag() {
		return privacyFlag;
	}

	/**
	 * @param privacyFlag
	 *            the privacyFlag to set
	 */
	public void setPrivacyFlag(Boolean privacyFlag) {
		this.privacyFlag = privacyFlag;
	}

	/**
	 * @return the inferred
	 */
	public Boolean getInferred() {
		return inferred;
	}

	/**
	 * @param inferred
	 *            the inferred to set
	 */
	public void setInferred(Boolean inferred) {
		this.inferred = inferred;
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
	@JsonIgnore
	public Date getCreatedDate() {
		final Date out = Date.from(created.atZone(ZoneId.systemDefault()).toInstant());
		return out;
	}

	@Transient
	@JsonIgnore
	public Date getUpdatedDate() {
		final Date out = Date.from(updated.atZone(ZoneId.systemDefault()).toInstant());
		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	/*
	 * @Override
	 * 
	 * @JsonIgnore public String toString() { return "EA:"+baseEntityCode + ":" +
	 * attributeCode + ": "+getAsString()+" wt=" + weight +
	 * ":"+(inferred?"INFERRED":"PRI")+"]"; // + ", version=" + version + "]"; }
	 */

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

	@JsonIgnore
	@Transient
	@XmlTransient
	public <T> void setValue(final Object value) {

		if (value instanceof String) {
			String result = (String) value;
			try {
				if (getAttribute().getDataType().getClassName().equalsIgnoreCase(String.class.getCanonicalName())) {
					setValueString(result);
				} else if (getAttribute().getDataType().getClassName()
						.equalsIgnoreCase(LocalDateTime.class.getCanonicalName())) {
					Date olddate = null;		
					olddate =  DateTimeUtils.parseDateTime(result, "yyyy-MM-dd","yyyy-MM-dd'T'HH:mm:ss","yyyy-MM-dd'T'HH:mm:ss.SSSZ");
						   final LocalDateTime dateTime = olddate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
					setValueDateTime(dateTime);
					} else if (getAttribute().getDataType().getClassName()
						.equalsIgnoreCase(LocalDate.class.getCanonicalName())) {
					Date olddate = null;
					try {
						olddate =  DateUtils.parseDate(result,"M/y", "yyyy-MM-dd","yyyy/MM/dd","yyyy-MM-dd'T'HH:mm:ss","yyyy-MM-dd'T'HH:mm:ss.SSSZ");
					} catch (java.text.ParseException e) {
						olddate =  DateTimeUtils.parseDateTime(result,"yyyy-MM-dd","yyyy-MM-dd'T'HH:mm:ss","yyyy-MM-dd'T'HH:mm:ss.SSSZ");
					}
						   final LocalDate date = olddate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						setValueDate(date);
					} else if (getAttribute().getDataType().getClassName()
						.equalsIgnoreCase(LocalTime.class.getCanonicalName())) {
					final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
					final LocalTime date = LocalTime.parse(result, formatter);
					setValueTime(date);
				} else if (getAttribute().getDataType().getClassName()
						.equalsIgnoreCase(Integer.class.getCanonicalName())) {
					final Integer integer = Integer.parseInt(result);
					setValueInteger(integer);
				} else if (getAttribute().getDataType().getClassName()
						.equalsIgnoreCase(Double.class.getCanonicalName())) {
					final Double d = Double.parseDouble(result);
					setValueDouble(d);
				} else if (getAttribute().getDataType().getClassName()
						.equalsIgnoreCase(Long.class.getCanonicalName())) {
					final Long l = Long.parseLong(result);
					setValueLong(l);
				} else if (getAttribute().getDataType().getClassName()
						.equalsIgnoreCase(Boolean.class.getCanonicalName())) {
					final Boolean b = Boolean.parseBoolean(result);
					setValueBoolean(b);
				} else {
					setValueString(result);
				}
			} catch (Exception e) {
				log.error("Conversion Error :" + value + " for attribute " + getAttribute() + " and SourceCode:"+this.baseEntityCode);
			}
		} else {

			switch (this.getPk().getAttribute().getDataType().getClassName()) {
			case "java.lang.Integer":
				setValueInteger((Integer) value);
				break;
			case "java.time.LocalDateTime":
				setValueDateTime((LocalDateTime) value);
				break;
			case "java.time.LocalDate":
				setValueDate((LocalDate) value);
				break;
			case "java.lang.Long":
				setValueLong((Long) value);
				break;
			case "java.time.LocalTime":
				setValueTime((LocalTime) value);
				break;

			case "java.lang.Double":
				setValueDouble((Double) value);
				break;
			case "java.lang.Boolean":
				setValueBoolean((Boolean) value);
				break;

			case "java.lang.String":
			default:
				setValueString((String) value);
				break;
			}
		}

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

	@Override
	public int hashCode() {

		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(baseEntityCode);
		hcb.append(attributeCode);
		return hcb.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof EntityAttribute)) {
			return false;
		}
		EntityAttribute that = (EntityAttribute) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(baseEntityCode, that.baseEntityCode);
		eb.append(attributeCode, that.attributeCode);
		return eb.isEquals();
	}

	public int compareTo(Object o) {
		EntityAttribute myClass = (EntityAttribute) o;
		final String dataType = getPk().getAttribute().getDataType().getClassName();
		switch (dataType) {
		case "java.lang.Integer":
			return new CompareToBuilder().append(this.getValueInteger(), myClass.getValueInteger()).toComparison();
		case "java.time.LocalDateTime":
			return new CompareToBuilder().append(this.getValueDateTime(), myClass.getValueDateTime()).toComparison();
		case "java.time.LocalTime":
			return new CompareToBuilder().append(this.getValueTime(), myClass.getValueTime()).toComparison();
		case "java.lang.Long":
			return new CompareToBuilder().append(this.getValueLong(), myClass.getValueLong()).toComparison();
		case "java.lang.Double":
			return new CompareToBuilder().append(this.getValueDouble(), myClass.getValueDouble()).toComparison();
		case "java.lang.Boolean":
			return new CompareToBuilder().append(this.getValueBoolean(), myClass.getValueBoolean()).toComparison();
		case "java.time.LocalDate":
			return new CompareToBuilder().append(this.getValueDate(), myClass.getValueDate()).toComparison();

		case "java.lang.String":
		default:
			return new CompareToBuilder().append(this.getValueString(), myClass.getValueString()).toComparison();

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	// @Override
	// public int hashCode() {
	// final int prime = 31;
	// int result = 1;
	// result = prime * result + ((attributeCode == null) ? 0 :
	// attributeCode.hashCode());
	// result = prime * result + ((baseEntityCode == null) ? 0 :
	// baseEntityCode.hashCode());
	// return result;
	// }
	//
	// /* (non-Javadoc)
	// * @see java.lang.Object#equals(java.lang.Object)
	// */
	// @Override
	// public boolean equals(Object obj) {
	// if (this == obj)
	// return true;
	// if (obj == null)
	// return false;
	// if (getClass() != obj.getClass())
	// return false;
	// EntityAttribute other = (EntityAttribute) obj;
	// if (attributeCode == null) {
	// if (other.attributeCode != null)
	// return false;
	// } else if (!attributeCode.equals(other.attributeCode))
	// return false;
	// if (baseEntityCode == null) {
	// if (other.baseEntityCode != null)
	// return false;
	// } else if (!baseEntityCode.equals(other.baseEntityCode))
	// return false;
	// if (created == null) {
	// if (other.created != null)
	// return false;
	// } else if (!created.equals(other.created))
	// return false;
	// if (inferred == null) {
	// if (other.inferred != null)
	// return false;
	// } else if (!inferred.equals(other.inferred))
	// return false;
	//// if (pk == null) {
	//// if (other.pk != null)
	//// return false;
	//// } else if (!pk.equals(other.pk))
	//// return false;
	// else if (privacyFlag == null) {
	// if (other.privacyFlag != null)
	// return false;
	// } else if (!privacyFlag.equals(other.privacyFlag))
	// return false;
	// if (updated == null) {
	// if (other.updated != null)
	// return false;
	// } else if (!updated.equals(other.updated))
	// return false;
	// if (valueBoolean == null) {
	// if (other.valueBoolean != null)
	// return false;
	// } else if (!valueBoolean.equals(other.valueBoolean))
	// return false;
	// if (valueDate == null) {
	// if (other.valueDate != null)
	// return false;
	// } else if (!valueDate.equals(other.valueDate))
	// return false;
	// if (valueDateTime == null) {
	// if (other.valueDateTime != null)
	// return false;
	// } else if (!valueDateTime.equals(other.valueDateTime))
	// return false;
	// if (valueDouble == null) {
	// if (other.valueDouble != null)
	// return false;
	// } else if (!valueDouble.equals(other.valueDouble))
	// return false;
	// if (valueInteger == null) {
	// if (other.valueInteger != null)
	// return false;
	// } else if (!valueInteger.equals(other.valueInteger))
	// return false;
	// if (valueLong == null) {
	// if (other.valueLong != null)
	// return false;
	// } else if (!valueLong.equals(other.valueLong))
	// return false;
	// if (valueString == null) {
	// if (other.valueString != null)
	// return false;
	// } else if (!valueString.equals(other.valueString))
	// return false;
	// if (weight == null) {
	// if (other.weight != null)
	// return false;
	// } else if (!weight.equals(other.weight))
	// return false;
	// return true;
	// }

	@Override
	public String toString() {
		return "EntityAttribute [baseEntityCode=" + baseEntityCode + ", attributeCode=" + attributeCode + ", value="
				+ getObjectAsString() + ", weight=" + weight + ", inferred=" + inferred + "]";
	}

	@JsonIgnore
	@Transient
	@XmlTransient
	public <T> T getObject() {

		if (getValueInteger() != null) {
			return (T) getValueInteger();
		}

		if (getValueDateTime() != null) {
			return (T) getValueDateTime();
		}

		if (getValueLong() != null) {
			return (T) getValueLong();
		}

		if (getValueDouble() != null) {
			return (T) getValueDouble();
		}

		if (getValueBoolean() != null) {
			return (T) getValueBoolean();
		}

		if (getValueDate() != null) {
			return (T) getValueDate();
		}
		if (getValueTime() != null) {
			return (T) getValueTime();
		}

		if (getValueString() != null) {
			return (T) getValueString();
		}

		return (T) getValueString();

	}

	@JsonIgnore
	@Transient
	@XmlTransient
	public String getObjectAsString() {

		if (getValueInteger() != null) {
			return "" + getValueInteger();
		}

		if (getValueDateTime() != null) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
			Date datetime = Date.from(getValueDateTime().atZone(ZoneId.systemDefault()).toInstant());
			String dout = df.format(datetime);
			return dout;
		}

		if (getValueLong() != null) {
			return "" + getValueLong();
		}

		if (getValueDouble() != null) {
			return getValueDouble().toString();
		}

		if (getValueBoolean() != null) {
			return getValueBoolean() ? "TRUE" : "FALSE";
		}

		if (getValueDate() != null) {
			DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
			Date date = Date.from(getValueDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
			String dout2 = df2.format(date);
			return dout2;
		}
		if (getValueTime() != null) {
			
			String dout2 = getValueTime().toString();
			return dout2;
		}

		if (getValueString() != null) {
			return getValueString();
		}

		return getValueString();

	}

}
