package life.genny.qwanda.attribute;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import life.genny.qwanda.entity.BaseEntity;

@Embeddable
public class EntityAttributeId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@ManyToOne ( cascade = CascadeType.DETACH)
	@JsonBackReference(value="entityAttribute")
	@JsonIgnore
	public BaseEntity baseEntity;

	@ManyToOne
	@JsonBackReference(value="attribute")
//	@JsonIgnore
	public Attribute attribute;

	public BaseEntity getBaseEntity() {
		return baseEntity;
	}

	public void setBaseEntity(final BaseEntity baseEntity) {
		this.baseEntity = baseEntity;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(final Attribute attribute) {
		this.attribute = attribute;
	}




}
