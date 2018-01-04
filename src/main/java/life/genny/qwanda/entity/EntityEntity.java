package life.genny.qwanda.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
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
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import life.genny.qwanda.Link;
import life.genny.qwanda.attribute.Attribute;
import life.genny.qwanda.datatype.LocalDateAdapter;
import life.genny.qwanda.datatype.LocalDateTimeAdapter;

@Entity
@Table(name = "baseentity_baseentity")
//@AssociationOverrides({
//    @AssociationOverride(name = "pk.source", joinColumns = @JoinColumn(name = "SOURCE_ID")),
//    @AssociationOverride(name = "pk.target", joinColumns = @JoinColumn(name = "TARGET_ID"))    
//})
@AssociationOverrides({
    @AssociationOverride(name = "pk.source", joinColumns = @JoinColumn(name = "SOURCE_ID"))})

public class EntityEntity implements java.io.Serializable, Comparable<Object> {


	@AttributeOverrides({
        @AttributeOverride(name = "sourceCode", column = @Column(name = "SOURCE_CODE", nullable = false)),
        @AttributeOverride(name = "targetCode", column = @Column(name = "TARGET_CODE", nullable = false)),
        @AttributeOverride(name = "attributeCode", column = @Column(name = "LINK_CODE", nullable = false)),
        @AttributeOverride(name = "weight", column = @Column(name = "LINK_WEIGHT", nullable = false))
	})
	private Link link ;
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

  
 
//
//  /**
//   * @return the targetCode
//   */
//  public String getTargetCode() {
//		return  this.targetCode = getPk().getTargetCode();
//  }
//
//  /**
//   * @param targetCode the targetCode to set
//   */
//  public void setTargetCode(String targetCode) {
// 	  this.getPk().setTargetCode(targetCode);
//  }

  
//  
//  /**
// * @return the linkCode
// */
//public String getLinkCode() {
//	return linkCode;
//}
//
///**
// * @param linkCode the linkCode to set
// */
//public void setLinkCode(String linkCode) {
//	this.linkCode = linkCode;
//}
//


/**
   * Stores the Created UMT DateTime that this object was created
   */

  @Column(name = "created")
  private LocalDateTime created;

  /**
   * Stores the Last Modified UMT DateTime that this object was last updated
   */
  @Column(name = "updated")
  private LocalDateTime updated;


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
   this(source,target,attribute, "LINK",weight);
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
//    this.pk.setSourceCode(source.getCode());
    this.pk.setTargetCode(target.getCode());
    if (value != null) {
        setValue(value);
       }
    String linkValue = this.getAsString();
    link = new Link(source.getCode(),target.getCode(),attribute.getCode(),linkValue);
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

    if (weight == null) {
      weight = 0.0; // This permits ease of adding attributes and hides
                    // attribute from scoring.
    }
    setWeight(weight);
    if (value != null) {
        setValue(value);
       }
    link = new Link(source.getCode(),target.getCode(),attribute.getCode());
  }

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

	/*@Override
	public int hashCode() {

		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(pk);
		return hcb.toHashCode();
	}*/ 
  
  

	/*@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof EntityEntity)) {
			return false;
		}
		EntityEntity that = (EntityEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(pk, that.pk);
		return eb.isEquals();
	}*/
  
  

	 public int compareTo(Object o) {
		 EntityEntity myClass = (EntityEntity) o;
	     return new CompareToBuilder()
//	       .appendSuper(super.compareTo(o)
	       .append(this.weight, myClass.weight)
	       .toComparison();
	   }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
//  @Override
//  public String toString() {
//    return "EntityEntity [parentCode=" + getPk().getSource().getCode() + ", targetCode=" + getPk().getTargetCode() + ", pk=" + pk
//        + ", created=" + created + ", updated=" + updated + ", value="+getValue()+ ", weight=" + weight +"]";
//  }
  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  // @Override
  // public String toString() {
  // return "EE["+getTarget().getCode()+":"+created + ",
  // linkType="+getLinkAttribute().getCode()+",weight=" + weight + ", v=" + version
  // + "]";
  // }

  
  
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
  final String dataType = getPk().getAttribute().getDataType().getClassName();
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
}
