package life.genny.qwanda.attribute;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Type;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import life.genny.qwanda.datatype.LocalDateTimeAdapter;
import life.genny.qwanda.QuestionQuestion;
import life.genny.qwanda.datatype.LocalDateAdapter;
import life.genny.qwanda.entity.BaseEntity;

@Entity
// #@JsonFilter("EntityAttribute")
@Table(name = "baseentity_attribute")
@AssociationOverrides({
   @AssociationOverride(name = "pk.baseEntity", joinColumns = @JoinColumn(name = "BASEENTITY_ID")),
    @AssociationOverride(name = "pk.attribute", joinColumns = @JoinColumn(name = "ATTRIBUTE_ID"))})
public class EntityAttribute implements java.io.Serializable, Comparable<Object> {

  private static final long serialVersionUID = 1L;

  private String baseEntityCode;

  private String attributeCode;

  /**
   * @return the baseEntityCode
   */
  public String getBaseEntityCode() {
    return baseEntityCode;
  }

  /**
   * @param baseEntityCode the baseEntityCode to set
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
   * @param attributeCode the attributeCode to set
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
  @Type(type="text")
  private String valueString;

  /**
   * Store the relative importance of the attribute for the baseEntity
   */
  private Double weight;
  
  /**
   * Store the relative importance of the attribute for the baseEntity
   */
  private Boolean inferred=false;
  
  /**
   * Store the privacy of this attribute , i.e. Don't display 
   */
  private Boolean privacyFlag = false;
  
//  @Version
//  private Long version = 1L;

  public EntityAttribute() {}

  /**
   * Constructor.
   * 
   * @param BaseEntity the entity that needs to contain attributes
   * @param Attribute the associated Attribute
   * @param Weight the weighted importance of this attribute (relative to the other attributes)
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
   * @param BaseEntity the entity that needs to contain attributes
   * @param Attribute the associated Attribute
   * @param Weight the weighted importance of this attribute (relative to the other attributes)
   * @param Value the value associated with this attribute
   */
  public EntityAttribute(final BaseEntity baseEntity, final Attribute attribute, Double weight,
      final Object value) {
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

  public EntityAttributeId getPk() {
    return pk;
  }

  public void setPk(final EntityAttributeId pk) {
    this.pk = pk;
  }

  @Transient
  @JsonIgnore
  @XmlTransient
  public BaseEntity getBaseEntity() {
    return getPk().getBaseEntity();
  }

  public void setBaseEntity(final BaseEntity baseEntity) {
    getPk().setBaseEntity(baseEntity);
    this.baseEntityCode = baseEntity.getCode();
  }

  @Transient
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
  }

//  /**
//   * @return the version
//   */
//  public Long getVersion() {
//    return version;
//  }
//
//  /**
//   * @param version the version to set
//   */
//  public void setVersion(final Long version) {
//    this.version = version;
//  }

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
 * @param privacyFlag the privacyFlag to set
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
 * @param inferred the inferred to set
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
  @Override
  public String toString() {
    return "EA:"+baseEntityCode + ":" + attributeCode
        + ": "+getAsString()+" wt=" + weight + ":"+(inferred?"INFERRED":"PRI")+"]";
     //   + ", version=" + version + "]";
  }

  @JsonIgnore
  @Transient
  @XmlTransient
  public <T> T getValue() {
    final String dataType = getAttribute().getDataType().getClassName();
    switch (dataType) {
      case "java.lang.Integer":
        return (T) getValueInteger();
      case "java.time.LocalDateTime":
        return (T) getValueDateTime();
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
    switch (this.getAttribute().getDataType().getClassName()) {
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
  
  @JsonIgnore
  @Transient
  @XmlTransient
  public String getAsString() {
    final String dataType = getAttribute().getDataType().getClassName();
    switch (dataType) {
      case "java.lang.Integer":
        return ""+getValueInteger();
      case "java.time.LocalDateTime":
    	  	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS"); 
    	  	Date datetime = Date.from(getValueDateTime().atZone(ZoneId.systemDefault()).toInstant());
    	  	String dout = df.format(datetime);
    	  	return dout;
      case "java.lang.Long":
        return ""+getValueLong();
      case "java.lang.Double":
        return getValueDouble().toString();
      case "java.lang.Boolean":
          return getValueBoolean()?"TRUE":"FALSE";
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
		hcb.append(pk);
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
		eb.append(pk, that.pk);
		return eb.isEquals();
	}

	 public int compareTo(Object o) {
		 EntityAttribute myClass = (EntityAttribute) o;
		   final String dataType = getAttribute().getDataType().getClassName();
		    switch (dataType) {
		      case "java.lang.Integer":
		        return new CompareToBuilder().append(this.getValueInteger(), myClass.getValueInteger()) .toComparison();
		      case "java.time.LocalDateTime":
			        return new CompareToBuilder().append(this.getValueDateTime(), myClass.getValueDateTime()) .toComparison();
		      case "java.lang.Long":
			        return new CompareToBuilder().append(this.getValueLong(), myClass.getValueLong()) .toComparison();
		      case "java.lang.Double":
			        return new CompareToBuilder().append(this.getValueDouble(), myClass.getValueDouble()) .toComparison();
		      case "java.lang.Boolean":
			        return new CompareToBuilder().append(this.getValueBoolean(), myClass.getValueBoolean()) .toComparison();
		      case "java.time.LocalDate":
			        return new CompareToBuilder().append(this.getValueDate(), myClass.getValueDate()) .toComparison();

		      case "java.lang.String":
		      default:
			        return new CompareToBuilder().append(this.getValueString(), myClass.getValueString()) .toComparison();

		    }

	   }
}
