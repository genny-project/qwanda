package life.genny.qwanda.attribute;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import life.genny.qwanda.entity.BaseEntity;

@Embeddable
public class EntityAttributeId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@ManyToOne
	@JsonBackReference(value="entityAttribute")
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



//	@Override
//	public boolean equals(final Object o) {
//		if (this == o)
//			return true;
//		if (o == null || getClass() != o.getClass())
//			return false;
//
//		final EntityAttributeId that = (EntityAttributeId) o;
//
////		if (id != null ? !(id == (that.id)) : that.id != null)
////			return false;
//		if (baseEntity != null ? !baseEntity.equals(that.baseEntity) : that.baseEntity != null)
//			return false;
//		if (attribute != null ? !attribute.equals(that.attribute) : that.attribute != null)
//			return false;
//
//		return true;
//	}
//
//	@Override
//	public int hashCode() {
//		int result = 0;
////		result = (id != null ? id.hashCode() : 0);
//		result = 17 * result + (baseEntity != null ? baseEntity.hashCode() : 0);
//		result = 31 * result + (attribute != null ? attribute.hashCode() : 0);
//		return result;
//	}

}
