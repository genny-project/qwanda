package life.genny.qwanda.attribute;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import life.genny.qwanda.entity.BaseEntity;

@Embeddable
public class EntityAttributeId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	public BaseEntity baseEntity;
	
	@ManyToOne
	public Attribute attribute;

	@JsonIgnore
	public BaseEntity getBaseEntity() {
		return baseEntity;
	}

	public void setBaseEntity(BaseEntity baseEntity) {
		this.baseEntity = baseEntity;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityAttributeId that = (EntityAttributeId) o;

        if (baseEntity != null ? !baseEntity.equals(that.baseEntity) : that.baseEntity != null) return false;
        if (attribute != null ? !attribute.equals(that.attribute) : that.attribute != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (baseEntity != null ? baseEntity.hashCode() : 0);
        result = 31 * result + (attribute != null ? attribute.hashCode() : 0);
        return result;
    }

}