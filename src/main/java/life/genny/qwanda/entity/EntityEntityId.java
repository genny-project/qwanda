package life.genny.qwanda.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import life.genny.qwanda.attribute.Attribute;

@Embeddable
public class EntityEntityId implements java.io.Serializable {

	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JsonIgnore
	private BaseEntity source;
	
//	@ManyToOne(fetch = FetchType.LAZY, optional = true)
//	@JsonIgnore
//	private BaseEntity target;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
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

//	/**
//	 * @return the target
//	 */
//	public BaseEntity getTarget() {
//		return target;
//	}
//
//	/**
//	 * @param target the target to set
//	 */
//	public void setTarget(final BaseEntity target) {
//		this.target = target;
//		this.targetCode = target.getCode();
//	}

	
	
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

        if (sourceCode != null ? !sourceCode.equals(that.sourceCode) : that.sourceCode != null) return false;
        if (targetCode != null ? !targetCode.equals(that.targetCode) : that.targetCode != null)
            return false;
        if (attributeCode != null ? !this.linkAttribute.getCode().equals(that.linkAttribute.getCode()) : that.linkAttribute.getCode() != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = (sourceCode != null ? sourceCode.hashCode() : 0);
        result = 31 * result + (targetCode != null ? targetCode.hashCode() : 0);
        result = 127 * result + (this.linkAttribute != null ? this.linkAttribute.hashCode() : 0);
        return result;
    }

}