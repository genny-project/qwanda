package life.genny.qwanda;

import java.time.LocalDateTime;
import java.time.ZoneId;
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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.CompareToBuilder;



@Entity
@Table(name = "question_question")
@AssociationOverrides({ @AssociationOverride(name = "pk.source", joinColumns = @JoinColumn(name = "SOURCE_ID")) })
public class QuestionQuestion implements java.io.Serializable, Comparable<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private QuestionQuestionId pk = new QuestionQuestionId();

	@Column(name = "created")
	private LocalDateTime created;

	/**
	 * Stores the Last Modified UMT DateTime that this object was last updated
	 */
	@Column(name = "updated")
	private LocalDateTime updated;

	/**
	 * Store the relative importance of this question link
	 */
	private Double weight;

	private Long version = 1L;

	Boolean mandatory = false;

	public QuestionQuestion() {
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
	 * @param linkValue
	 *            the associated linkValue
	 * @param Weight
	 *            the weighted importance of this attribute (relative to the other
	 *            attributes)
	 */
	public QuestionQuestion(final Question source, final String targetCode, Double weight, boolean mandatory) {
		autocreateCreated();
		getPk().setSource(source);
		this.pk.setTargetCode(targetCode);
		setMandatory(mandatory);
		if (weight == null) {
			weight = 0.0; // This permits ease of adding attributes and hides
							// attribute from scoring.
		}
		setWeight(weight);
	}

	/**
	 * Constructor.
	 * 
	 * @param Question
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
	public QuestionQuestion(final Question source, final Question target, Double weight) {
		autocreateCreated();

		this.pk.setSource(source);

		this.pk.setTargetCode(target.getCode());

		if (weight == null) {
			weight = 0.0; // This permits ease of adding attributes and hides
							// attribute from scoring.
		}
		setWeight(weight);

	}

	public QuestionQuestionId getPk() {
		return pk;
	}

	public void setPk(final QuestionQuestionId pk) {
		this.pk = pk;
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
	 * @return the mandatory
	 */
	public Boolean getMandatory() {
		return mandatory;
	}

	/**
	 * @param mandatory
	 *            the mandatory to set
	 */
	public void setMandatory(Boolean mandatory) {
		this.mandatory = mandatory;
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
		if (updated != null) {
			final Date out = Date.from(updated.atZone(ZoneId.systemDefault()).toInstant());
			return out;
		} else {
			return null;
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
		if (!(obj instanceof QuestionQuestion)) {
			return false;
		}
		QuestionQuestion that = (QuestionQuestion) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(pk, that.pk);
		return eb.isEquals();
	}

	 public int compareTo(Object o) {
		 QuestionQuestion myClass = (QuestionQuestion) o;
	     return new CompareToBuilder()
//	       .appendSuper(super.compareTo(o)
	       .append(this.weight, myClass.weight)
	       .toComparison();
	   }


}