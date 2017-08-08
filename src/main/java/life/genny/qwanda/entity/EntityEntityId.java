package life.genny.qwanda.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import life.genny.qwanda.attribute.Attribute;

@Embeddable
public class EntityEntityId implements java.io.Serializable {

	@ManyToOne
	private BaseEntity source;
	
	@ManyToOne
	private BaseEntity target;

	@ManyToOne
	private Attribute linkAttribute;


	/**
	 * @return the source
	 */
	public BaseEntity getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(BaseEntity source) {
		this.source = source;
	}

	/**
	 * @return the target
	 */
	public BaseEntity getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(BaseEntity target) {
		this.target = target;
	}

	
	
	/**
	 * @return the linkAttribute
	 */
	public Attribute getLinkAttribute() {
		return linkAttribute;
	}

	/**
	 * @param linkAttribute the linkAttribute to set
	 */
	public void setLinkAttribute(Attribute linkAttribute) {
		this.linkAttribute = linkAttribute;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityEntityId that = (EntityEntityId) o;

        if (source != null ? !source.equals(that.source) : that.source != null) return false;
        if (target != null ? !target.equals(that.target) : that.target != null)
            return false;
        if (linkAttribute != null ? !linkAttribute.equals(that.linkAttribute) : that.linkAttribute != null)
            return false;
        return true;
    }

    public int hashCode() {
        int result;
        result = (source != null ? source.hashCode() : 0);
        result = 31 * result + (target != null ? target.hashCode() : 0);
        result = 127 * result + (linkAttribute != null ? linkAttribute.hashCode() : 0);
        return result;
    }

}