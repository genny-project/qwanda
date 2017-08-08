package life.genny.qwanda;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import life.genny.qwanda.attribute.Attribute;
import life.genny.qwanda.entity.BaseEntity;

@Embeddable
public class AnswerLinkId implements java.io.Serializable {

	@JsonIgnore
	@ManyToOne
	private BaseEntity source;
	
	@JsonIgnore
	@ManyToOne
	private BaseEntity target;

	@JsonIgnore
	@ManyToOne
	private Attribute attribute;

	@JsonIgnore
	@ManyToOne
	private Ask ask;

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
	public Attribute getAttribute() {
		return attribute;
	}

	/**
	 * @param linkAttribute the linkAttribute to set
	 */
	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	
	
	/**
	 * @return the ask
	 */
	public Ask getAsk() {
		return ask;
	}

	/**
	 * @param ask the ask to set
	 */
	public void setAsk(Ask ask) {
		this.ask = ask;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerLinkId that = (AnswerLinkId) o;

        if (source != null ? !source.equals(that.source) : that.source != null) return false;
        if (target != null ? !target.equals(that.target) : that.target != null)
            return false;
        if (attribute != null ? !attribute.equals(that.attribute) : that.attribute != null)
            return false;
        return true;
    }

    public int hashCode() {
        int result;
        result = (source != null ? source.hashCode() : 0);
        result = 31 * result + (target != null ? target.hashCode() : 0);
        result = 127 * result + (attribute != null ? attribute.hashCode() : 0);
        return result;
    }

}