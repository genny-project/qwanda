package life.genny.qwanda.entity;

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

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import life.genny.qwanda.attribute.Attribute;
import life.genny.qwanda.datatype.LocalDateAdapter;
import life.genny.qwanda.datatype.LocalDateTimeAdapter;

@Entity
@Table(name = "baseentity_baseentity")
@AssociationOverrides({
    @AssociationOverride(name = "pk.source", joinColumns = @JoinColumn(name = "SOURCE_ID")),
    @AssociationOverride(name = "pk.target", joinColumns = @JoinColumn(name = "TARGET_ID"))})
public class EntityEntity implements java.io.Serializable {

  @Transient
  private String parentCode;
  @Transient
  private String targetCode;
  @Transient
  private String linkCode;
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @EmbeddedId
  private EntityEntityId pk = new EntityEntityId();

 

  /**
   * @return the parentCode
   */
  public String getParentCode() {
    return parentCode;
  }

  /**
   * @param parentCode the parentCode to set
   */
  public void setParentCode(String parentCode) {
    this.parentCode = parentCode;
  }

  /**
   * @return the targetCode
   */
  public String getTargetCode() {
    return targetCode;
  }

  /**
   * @param targetCode the targetCode to set
   */
  public void setTargetCode(String targetCode) {
    this.targetCode = targetCode;
  }

  
  
  /**
 * @return the linkCode
 */
public String getLinkCode() {
	return linkCode;
}

/**
 * @param linkCode the linkCode to set
 */
public void setLinkCode(String linkCode) {
	this.linkCode = linkCode;
}



/**
   * Stores the Created UMT DateTime that this object was created
   */

  @Column(name = "created")
  private LocalDateTime created;

  /**
   * Stores the Last Modified UMT DateTime that this object was last updated
   */
  @Column(name = "updated")
  @Version
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

  @Version
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
      final Attribute linkAttribute, Double weight) {
    autocreateCreated();
    setSource(source);
    setTarget(target);
    setLinkAttribute(linkAttribute);
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
      final Attribute linkAttribute, Double weight, final Object value) {
    autocreateCreated();
    setSource(source);
    setTarget(target);
    setLinkAttribute(linkAttribute);

    if (weight == null) {
      weight = 0.0; // This permits ease of adding attributes and hides
                    // attribute from scoring.
    }
    setWeight(weight);
    if (value != null) {
        setValue(value);
       }
  }

  public EntityEntityId getPk() {
    return pk;
  }

  public void setPk(final EntityEntityId pk) {
    this.pk = pk;
  }

  @Transient
  public BaseEntity getSource() {
    return getPk().getSource();
  }

  public void setSource(final BaseEntity source) {
    getPk().setSource(source);
  }

  @Transient
  public BaseEntity getTarget() {
    return getPk().getTarget();
  }

  public void setTarget(final BaseEntity target) {
    getPk().setTarget(target);
  }

  @Transient
  public Attribute getLinkAttribute() {
    return getPk().getLinkAttribute();
  }

  public void setLinkAttribute(final Attribute linkAttribute) {
    getPk().setLinkAttribute(linkAttribute);
    setLinkCode(linkAttribute.getCode());
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
    final Date out = Date.from(updated.atZone(ZoneId.systemDefault()).toInstant());
    return out;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    final EntityEntity that = (EntityEntity) o;

    if (getPk() != null ? !getPk().equals(that.getPk()) : that.getPk() != null)
      return false;

    return true;
  }

  @Override
  public int hashCode() {
    return (getPk() != null ? getPk().hashCode() : 0);
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "EntityEntity [parentCode=" + parentCode + ", targetCode=" + targetCode + ", pk=" + pk
        + ", created=" + created + ", updated=" + updated + ", valueDouble=" + valueDouble
        + ", valueInteger=" + valueInteger + ", valueLong=" + valueLong + ", valueDateTime="
        + valueDateTime + ", valueString=" + valueString + ", weight=" + weight + ", version="
        + version + "]";
  }
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

  
  
  @JsonIgnore
  @Transient
  @XmlTransient
  public <T> T getValue() {
    final String dataType = getLinkAttribute().getDataType().getClassName();
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
    switch (this.getLinkAttribute().getDataType().getClassName()) {
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
}
