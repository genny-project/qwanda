package life.genny.qwanda.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import life.genny.qwanda.attribute.Attribute;

@Embeddable
public class EntityEntityId implements java.io.Serializable {

	@ManyToOne
	@JsonIgnore
	private BaseEntity source;
	
	@ManyToOne
	@JsonIgnore
	private BaseEntity target;

	@ManyToOne
	@JsonIgnore
	private Attribute linkAttribute;

	private String sourceCode;
	
	private String targetCode;
	
	private String attributeCode;

	/**
	 * @return the source
	 */
	public BaseEntity getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(final BaseEntity source) {
		this.source = source;
		this.sourceCode = source.getCode();
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
	public void setTarget(final BaseEntity target) {
		this.target = target;
		this.targetCode = target.getCode();
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
	public void setLinkAttribute(final Attribute linkAttribute) {
		this.linkAttribute = linkAttribute;
		this.attributeCode = linkAttribute.getCode();
	}

	
	
	
	/**
   * @return the sourceCode
   */
  public String getSourceCode() {
    return sourceCode;
  }

  /**
   * @param sourceCode the sourceCode to set
   */
  public void setSourceCode(final String sourceCode) {
    this.sourceCode = sourceCode;
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
  public void setTargetCode(final String targetCode) {
    this.targetCode = targetCode;
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
  public void setAttributeCode(final String attributeCode) {
    this.attributeCode = attributeCode;
  }

  @Override
  public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final EntityEntityId that = (EntityEntityId) o;

        if (source != null ? !source.equals(that.source) : that.source != null) return false;
        if (target != null ? !target.equals(that.target) : that.target != null)
            return false;
        if (linkAttribute != null ? !linkAttribute.equals(that.linkAttribute) : that.linkAttribute != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = (source != null ? source.hashCode() : 0);
        result = 31 * result + (target != null ? target.hashCode() : 0);
        result = 127 * result + (linkAttribute != null ? linkAttribute.hashCode() : 0);
        return result;
    }

}