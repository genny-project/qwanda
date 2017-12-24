package life.genny.qwanda;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Set;

import life.genny.qwanda.attribute.Attribute;
import life.genny.qwanda.attribute.EntityAttribute;
import life.genny.qwanda.converter.StringListConverter;
import life.genny.qwanda.converter.ValidationListConverter;
import life.genny.qwanda.entity.BaseEntity;

@Entity
@Table(name = "answerlinks")
//@Table(name = "answerlinks", indexes = {
//		@Index(name = "IDX_MYIDX1", columnList = "targetCode,sourceCode,attributeCode") })

@AssociationOverrides({ @AssociationOverride(name = "pk.source", joinColumns = @JoinColumn(name = "SOURCE_ID")),
		@AssociationOverride(name = "pk.target", joinColumns = @JoinColumn(name = "TARGET_ID")) })
public class AnswerLink implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AnswerLinkId pk = new AnswerLinkId();

	/**
	 * Stores the Created UMT DateTime that this object was created
	 */

	@Column(name = "created")
	private LocalDateTime created;

	/**
	 * Stores the Last Modified UMT DateTime that this object was last updated
	 */
	@Column(name = "updated")
	// @Version
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
	private LocalDateTime valueDateTime;

	/**
	 * Store the LocalDate value of the attribute for the baseEntity
	 */
	private LocalDate valueDate;

	/**
	 * Store the String value of the attribute for the baseEntity
	 */
	@Type(type="text")
	private String valueString;

	/**
	 * Store the Boolean value of the attribute for the baseEntity
	 */
	private Boolean valueBoolean;
	
	/**
	 * Store the BaseEntity Code value of the attribute for the baseEntity
	 */
	  @Column(name = "be_list", length = 512)
	  @Convert(converter = StringListConverter.class)
	private List<String> ValueBaseEntityCodeList;	

	/**
	 * Store the Expired boolean value of the attribute for the baseEntity
	 */
	private Boolean expired = false;

	/**
	 * Store the Refused boolean value of the attribute for the baseEntity
	 */
	private Boolean refused = false;

	/**
	 * Store wther this answer is inferred
	 */
	private Boolean inferred = false;

	/**
	 * Store the relative importance of the attribute for the baseEntity
	 */
	private Double weight;

	// @Version
	private Long version = 1L;

	private String targetCode;
	private String sourceCode;
	private Long askId;
	private String attributeCode; // original answer

	public AnswerLink() {
	}

	/**
	 * Constructor.
	 * 
	 * @param source
	 *            the source baseEntity
	 * @param target
	 *            the target entity that is linked to
	 * @param linkAttribute
	 *            the associated linkAttribute
	 * @param Weight
	 *            the weighted importance of this attribute (relative to the other
	 *            attributes)
	 */
	public AnswerLink(final BaseEntity source, final BaseEntity target, final Answer answer) {

		this(source, target, answer, 0.0); // make zero so to not impact scoring
		this.attributeCode = answer.getAttributeCode();
		this.setSourceCode(answer.getSourceCode());
		this.setTargetCode(answer.getTargetCode());
		this.setAskId(answer.getAskId());
	}

	/**
	 * Constructor.
	 * 
	 * @param BaseEntity
	 *            the entity that needs to contain attributes
	 * @param Attribute
	 *            the associated Attribute
	 * @param linkAttribute
	 *            the associated linkAttribute
	 * @param Weight
	 *            the weighted importance of this attribute (relative to the other
	 *            attributes)
	 * @param Value
	 *            the value associated with this attribute
	 */
	public AnswerLink(final BaseEntity source, final BaseEntity target, final Answer answer, Double weight) {
		autocreateCreated();
		setSource(source);
		setTarget(target);
		pk.setAttribute(answer.getAttribute());
		// setAsk(answer.getAsk());
		// if (answer.getAsk() != null) {
		// if (answer.getAsk().getQuestion() != null) {
		// setAttribute(answer.getAsk().getQuestion().getAttribute());
		// }
		// } else {
		// // create dummy
		//
		// }
		setAttributeCode(answer.getAttributeCode());

		if (weight == null) {
			weight = 0.0; // This permits ease of adding attributes and hides
							// attribute from scoring.
		}
		setWeight(weight);
		setAnswer(answer);

	}

	@JsonIgnore
	public void setAnswer(final Answer answer) {
		this.setCreated(answer.getCreated());
		this.setExpired(answer.getExpired());
		this.setRefused(answer.getRefused());
		this.setInferred(answer.getInferred());

		// Assume that Attribute Validation has been performed

		if (getAttribute().getDataType().getClassName().equalsIgnoreCase(String.class.getCanonicalName())) {
			setValueString(answer.getValue());
		} else if (getAttribute().getDataType().getClassName()
				.equalsIgnoreCase(LocalDateTime.class.getCanonicalName())) {
			final String result = answer.getValue();
			final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
			final LocalDateTime dateTime = LocalDateTime.parse(result, formatter);
			setValueDateTime(dateTime);
		} else if (getAttribute().getDataType().getClassName().equalsIgnoreCase(LocalDate.class.getCanonicalName())) {
			final String result = answer.getValue();
			final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			final LocalDate date = LocalDate.parse(result, formatter);
			setValueDate(date);
		} else if (getAttribute().getDataType().getClassName().equalsIgnoreCase(Integer.class.getCanonicalName())) {
			final String result = answer.getValue();
			final Integer integer = Integer.parseInt(result);
			setValueInteger(integer);
		} else if (getAttribute().getDataType().getClassName().equalsIgnoreCase(Double.class.getCanonicalName())) {
			final String result = answer.getValue();
			final Double d = Double.parseDouble(result);
			setValueDouble(d);
		} else if (getAttribute().getDataType().getClassName().equalsIgnoreCase(Long.class.getCanonicalName())) {
			final String result = answer.getValue();
			final Long l = Long.parseLong(result);
			setValueLong(l);
		} else if (getAttribute().getDataType().getClassName().equalsIgnoreCase(Boolean.class.getCanonicalName())) {
			final String result = answer.getValue();
			final Boolean b = Boolean.parseBoolean(result);
			setValueBoolean(b);
		} else {
			setValueString(answer.getValue());
		}
	}

	// @JsonIgnore
	public AnswerLinkId getPk() {
		return pk;
	}

	public void setPk(final AnswerLinkId pk) {
		this.pk = pk;
	}

	@Transient
	@JsonIgnore
	public BaseEntity getSource() {
		return getPk().getSource();
	}

	public void setSource(final BaseEntity source) {
		getPk().setSource(source);
		setSourceCode(source.getCode());
	}

	// @Transient
	// @JsonIgnore
	// public Ask getAsk() {
	// return getPk().getAsk();
	// }
	//
	// public void setAsk(final Ask ask) {
	// getPk().setAsk(ask);
	// if (ask != null) {
	// setAskId(ask.getId());
	// }
	// }

	@Transient
	@JsonIgnore
	public BaseEntity getTarget() {
		return getPk().getTarget();
	}

	public void setTarget(final BaseEntity target) {
		getPk().setTarget(target);
		setTargetCode(target.getCode());
	}

	@Transient
	@JsonIgnore
	public Attribute getAttribute() {
		return getPk().getAttribute();
	}

	public void setAttribute(final Attribute attribute) {
		getPk().setAttribute(attribute);
		;
	}

	/**
	 * @return the askId
	 */

	public Long getAskId() {
		return askId;
	}

	/**
	 * @param askId
	 *            the askId to set
	 */
	public void setAskId(final Long askId) {
		this.askId = askId;
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

	/**
	 * @return the valueBoolean
	 */
	public Boolean getValueBoolean() {
		return valueBoolean;
	}

	/**
	 * @param valueBoolean
	 *            the valueBoolean to set
	 */
	public void setValueBoolean(final Boolean valueBoolean) {
		this.valueBoolean = valueBoolean;
	}

	
	
	/**
	 * @return the valueBaseEntityCode
	 */
	public List<String> getValueBaseEntityCodeList() {
		return ValueBaseEntityCodeList;
	}

	/**
	 * @param valueBaseEntityCode the valueBaseEntityCode to set
	 */
	public void setValueBaseEntityCodeList(List<String> valueBaseEntityCode) {
		this.ValueBaseEntityCodeList = valueBaseEntityCode;
	}

	/**
	 * @return the expired
	 */
	public Boolean getExpired() {
		return expired;
	}

	/**
	 * @param expired
	 *            the expired to set
	 */
	public void setExpired(final Boolean expired) {
		this.expired = expired;
	}

	/**
	 * @return the refused
	 */
	public Boolean getRefused() {
		return refused;
	}

	/**
	 * @param refused
	 *            the refused to set
	 */
	public void setRefused(final Boolean refused) {
		this.refused = refused;
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
		Date out = null;
		if (updated != null) {
			out = Date.from(updated.atZone(ZoneId.systemDefault()).toInstant());
		}
		return out;
	}

	public String getTargetCode() {
		return targetCode;
	}

	public void setTargetCode(final String targetCode) {
		this.targetCode = targetCode;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(final String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getAttributeCode() {
		return attributeCode;
	}

	public void setAttributeCode(final String attributeCode) {
		this.attributeCode = attributeCode;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		final AnswerLink that = (AnswerLink) o;

		if (getPk() != null ? !getPk().equals(that.getPk()) : that.getPk() != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EE[" + getTarget().getCode() + ":" + created + ", linkType=" + getAttribute().getCode() + ",weight="
				+ weight + ", value=" + getValue() + ", v=" + version + "]";
	}

	@JsonIgnore
	@Transient
	@XmlTransient
	public <T> T getValue() {
		final String dataType = getAttribute().getDataType().getClassName();
		switch (dataType) {
		case "life.genny.qwanda.entity":
			return (T) getValueBaseEntityCodeList();
		case "java.lang.Integer":
			return (T) getValueInteger();
		case "java.time.LocalDateTime":
			return (T) getValueDateTime();
		case "java.time.LocalDate":
			return (T) getValueDate();
		case "java.lang.Long":
			return (T) getValueLong();
		case "java.lang.Double":
			return (T) getValueDouble();
		case "java.lang.Boolean":
			return (T) getValueBoolean();
		case "java.lang.String":
		default:
			return (T) getValueString();
		}

	}

	@JsonIgnore
	@Transient
	@XmlTransient
	public <T> void setValue(final Object value) {
		switch (this.pk.getAttribute().getDataType().getClassName()) {
		case "life.genny.qwanda.entity":
			setValueBaseEntityCodeList((List<String>) value);
			break;
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
