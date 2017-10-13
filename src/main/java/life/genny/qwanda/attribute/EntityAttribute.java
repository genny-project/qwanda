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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import life.genny.qwanda.datatype.LocalDateTimeAdapter;
import life.genny.qwanda.entity.BaseEntity;

@Entity
//#@JsonFilter("EntityAttribute")
@Table(name = "baseentity_attribute")
@AssociationOverrides({ @AssociationOverride(name = "pk.baseEntity", joinColumns = @JoinColumn(name = "BASEENTITY_ID")),
		@AssociationOverride(name = "pk.attribute", joinColumns = @JoinColumn(name = "ATTRIBUTE_ID")) })
public class EntityAttribute implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

	private String baseEntityCode ;
	
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
  public void setBaseEntityCode(String baseEntityCode) {
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
  public void setAttributeCode(String attributeCode) {
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
	@Version
	private LocalDateTime updated;

	/**
	 * The following fields can be subclassed for better abstraction
	 */

	/**
	 * Store the Double value of the attribute for the baseEntity
	 */
	private Double valueDouble;

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
	 * Store the String value of the attribute for the baseEntity
	 */
	private String valueString;

	/**
	 * Store the relative importance of the attribute for the baseEntity
	 */
	private Double weight;

	@Version
	private Long version = 1L;

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
	 *            the weighted importance of this attribute (relative to the
	 *            other attributes)
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
	 *            the weighted importance of this attribute (relative to the
	 *            other attributes)
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
			if (value instanceof String) {
				setValueString((String) value);
			}
			else if (value instanceof Double) {
				setValueDouble((Double) value);
			}
			else if (value instanceof Long) {
				setValueLong((Long) value);
			}
			else if (value instanceof Integer) {
				setValueInteger((Integer) value);
			}
			else if (value instanceof LocalDateTime) {
				setValueDateTime((LocalDateTime) value);
			}

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
	}

	@Transient
	public Attribute getAttribute() {
		return getPk().getAttribute();
	}

	public void setAttribute(final Attribute attribute) {
		getPk().setAttribute(attribute);
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

	/**
	 * @return the version
	 */
	public Long getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
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

	@Override
  public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		final EntityAttribute that = (EntityAttribute) o;

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
    return "EntityAttribute [baseEntityCode=" + baseEntityCode + ", attributeCode=" + attributeCode
        + ", pk=" + pk + ", created=" + created + ", updated=" + updated + ", valueDouble="
        + valueDouble + ", valueInteger=" + valueInteger + ", valueLong=" + valueLong
        + ", valueDateTime=" + valueDateTime + ", valueString=" + valueString + ", weight=" + weight
        + ", version=" + version + "]";
  }


}