package life.genny.qwanda;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class QuestionQuestionId implements java.io.Serializable {

	@ManyToOne
	private Question parent;
	
	@ManyToOne
	private Question child;



	/**
	 * @return the parent
	 */
	public Question getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Question parent) {
		this.parent = parent;
	}

	/**
	 * @return the child
	 */
	public Question getChild() {
		return child;
	}

	/**
	 * @param child the child to set
	 */
	public void setChild(Question child) {
		this.child = child;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionQuestionId that = (QuestionQuestionId) o;

        if (parent != null ? !parent.equals(that.parent) : that.parent != null) return false;
        if (child != null ? !child.equals(that.child) : that.child != null)
            return false;
        return true;
    }

    public int hashCode() {
        int result;
        result = (parent != null ? parent.hashCode() : 0);
        result = 31 * result + (child != null ? child.hashCode() : 0);
        return result;
    }

}