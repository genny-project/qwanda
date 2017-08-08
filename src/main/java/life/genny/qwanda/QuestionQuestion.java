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
import javax.persistence.Version;

@Entity
@Table(name = "question_question")
@AssociationOverrides({ @AssociationOverride(name = "pk.parent", joinColumns = @JoinColumn(name = "PARENT_ID")),
		@AssociationOverride(name = "pk.child", joinColumns = @JoinColumn(name = "CHILD_ID")) })
public class QuestionQuestion implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private QuestionQuestionId pk = new QuestionQuestionId();

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
	 * The following fields can be subclassed for better abstraction
	 */
	
	/**
	 * Store the mandatory value for this question link
	 */
	private Boolean mandatory;

	
	/**
	 * Store the relative importance of the attribute for the baseEntity
	 */
	private Double weight;

	@Version
	private Long version = 1L;

	public QuestionQuestion() {
	}

	/**
	 * Constructor.
	 * 
	 * @param parent
	 *            the parent question
	 * @param child
	 *            the child question that is linked to
	 */
	public QuestionQuestion(Question parent, Question child) {
		this(parent,child,0.0);
	}

	/**
	 * Constructor.
	 * 
	 * @param parent
	 *            the parent question
	 * @param child
	 *            the child question that is linked to
	 * @param Weight
	 *            the weighted importance of this question (relative to the
	 *            other questions)
	 */
	public QuestionQuestion(Question parent, Question child, Double weight) {
		this(parent,child,weight,false);
	}

	/**
	 * Constructor.
	 * 
	 * @param parent
	 *            the parent question
	 * @param child
	 *            the child question that is linked to
	 * @param mandatory
	 *            is the child question mandatory?
	 * @param Weight
	 *            the weighted importance of this question (relative to the
	 *            other questions)
	 */
	public QuestionQuestion(Question parent, Question child, Double weight, Boolean mandatory) {
		autocreateCreated();
		setParent(parent);
		setChild(child);
		setMandatory(mandatory);
		if (weight == null) {
			weight = 0.0; // This permits ease of adding attributes and hides
							// attribute from scoring.
		}
		setWeight(weight);
	}


	
	public QuestionQuestionId getPk() {
		return pk;
	}

	public void setPk(QuestionQuestionId pk) {
		this.pk = pk;
	}

	@Transient
	public Question getParent() {
		return getPk().getParent();
	}

	public void setParent(Question source) {
		getPk().setParent(source);
	}

	@Transient
	public Question getChild() {
		return getPk().getChild();
	}

	public void setChild(Question target) {
		getPk().setChild(target);
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
	public void setCreated(LocalDateTime created) {
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
	public void setUpdated(LocalDateTime updated) {
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
	public void setWeight(Double weight) {
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
	public void setVersion(Long version) {
		this.version = version;
	}

	
	


	/**
	 * @return the mandatory
	 */
	public Boolean getMandatory() {
		return mandatory;
	}

	/**
	 * @param mandatory the mandatory to set
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
		Date out = Date.from(created.atZone(ZoneId.systemDefault()).toInstant());
		return out;
	}

	@Transient
	public Date getUpdatedDate() {
		Date out = Date.from(updated.atZone(ZoneId.systemDefault()).toInstant());
		return out;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		QuestionQuestion that = (QuestionQuestion) o;

		if (getPk() != null ? !getPk().equals(that.getPk()) : that.getPk() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QQ["+getChild().getCode()+":"+created + ", mandatory="+(getMandatory()?"YES":"NO")+",weight=" + weight + ", v=" + version
				+ "]";
	}
	
	
}