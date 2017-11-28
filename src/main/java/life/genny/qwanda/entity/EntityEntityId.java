package life.genny.qwanda.entity;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonBackReference;

import life.genny.qwanda.attribute.Attribute;

@Embeddable
public class EntityEntityId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonBackReference
	private BaseEntity source;
	
//	@ManyToOne(fetch = FetchType.EAGER, optional = false)
//	@JsonIgnore
//	private BaseEntity target;
	
	private String targetCode;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
//	@JsonIgnore
	private Attribute attribute;


/**
	 * @return the targetCode
	 */
	public String getTargetCode() {
		return targetCode;
	}

	/**
	 * @param targetCode the targetCode to set
	 */
	public void setTargetCode(String targetCode) {
		this.targetCode = targetCode;
	}

	//
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
//		this.getSource().getCode() = source.getCode();
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
////		this.getTarget().getCode() = target.getCode();
//	}

	
	
	/**
	 * @return the linkAttribute
	 */
	public Attribute getAttribute() {
		return attribute;
	}

	/**
	 * @param linkAttribute the linkAttribute to set
	 */
	public void setAttribute(final Attribute attribute) {
		this.attribute = attribute;
//		this.getAttribute().getCode() = linkAttribute.getCode();
	}

	
	
	
//	/**
//   * @return the getSource().getCode()
//   */
//  public String getSourceCode() {
//    return getSource().getCode();
//  }
//
//  /**
//   * @param getSource().getCode() the getSource().getCode() to set
//   */
//  public void setSourceCode(final String getSource().getCode()) {
//    this.getSource().getCode() = getSource().getCode();
//  }
//
//  /**
//   * @return the getTarget().getCode()
//   */
//  public String getTargetCode() {
//    return getTarget().getCode();
//  }
//
//  /**
//   * @param getTarget().getCode() the getTarget().getCode() to set
//   */
//  public void setTargetCode(final String getTarget().getCode()) {
//    this.getTarget().getCode() = getTarget().getCode();
//  }
//
//  /**
//   * @return the getAttribute().getCode()
//   */
//  public String getAttributeCode() {
//    return getAttribute().getCode();
//  }
//
//  /**
//   * @param getAttribute().getCode() the getAttribute().getCode() to set
//   */
//  public void setAttributeCode(final String getAttribute().getCode()) {
//    this.getAttribute().getCode() = getAttribute().getCode();
//  }

  
  
	// @Override
	// public boolean equals(final Object o) {
	// if (this == o) return true;
	// if (o == null || getClass() != o.getClass()) return false;
	//
	// final EntityEntityId that = (EntityEntityId) o;
	//
	// if (getSource().getCode() != null ?
	// !getSource().getCode().equals(that.getSource().getCode()) :
	// that.getSource().getCode() != null) return false;
	// if (getTargetCode() != null ? !getTargetCode().equals(that.getTargetCode()) :
	// that.getTargetCode() != null)
	// return false;
	// if (getAttribute().getCode() != null ?
	// !this.attribute.getCode().equals(that.attribute.getCode()) :
	// that.attribute.getCode() != null)
	// return false;
	// return true;
	// }

    @Override
    public int hashCode() {
//        int result;
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(attribute);
        hcb.append(source.getCode());
        hcb.append(targetCode);
        return hcb.toHashCode();
    }      
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof EntityEntityId)) {
                return false;
            }
            EntityEntityId that = (EntityEntityId) obj;
            EqualsBuilder eb = new EqualsBuilder();
            eb.append(attribute, that.attribute);
            eb.append(source.getCode(), that.source.getCode());
            eb.append(targetCode, that.targetCode);
            return eb.isEquals();
        }
        
//        result = (getSource().getCode() != null ? getSource().getCode().hashCode() : 0);
//        result = 31 * result + (getTargetCode() != null ? getTargetCode().hashCode() : 0);
//        result = 127 * result + (this.attribute != null ? this.attribute.hashCode() : 0);
//        return result;
    

//    
//    @Override
//    public boolean equals(final Object o) {
//          if (this == o) return true;
//          if (o == null || getClass() != o.getClass()) return false;
//
//          final EntityEntityId that = (EntityEntityId) o;
//
//          if (getSource().getCode() != null ? !getSource().getCode().equals(that.getSource().getCode()) : that.getSource().getCode() != null) return false;
//          if (getTarget().getCode() != null ? !getTarget().getCode().equals(that.getTarget().getCode()) : that.getTarget().getCode() != null)
//              return false;
//          if (getAttribute().getCode() != null ? !this.attribute.getCode().equals(that.attribute.getCode()) : that.attribute.getCode() != null)
//              return false;
//          return true;
//      }
//
//      @Override
//      public int hashCode() {
//          int result;
//          result = (getSource().getCode() != null ? getSource().getCode().hashCode() : 0);
//          result = 31 * result + (getTarget().getCode() != null ? getTarget().getCode().hashCode() : 0);
//          result = 127 * result + (this.attribute != null ? this.attribute.hashCode() : 0);
//          return result;
//      }
//    
}