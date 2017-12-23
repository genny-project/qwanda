package life.genny.qwanda;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;

import life.genny.qwanda.entity.EntityEntityId;

@Embeddable
public class QuestionQuestionId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JsonBackReference
	private Question source;
	
	private String targetCode;
	

	


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

	public Question getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(final Question source) {
		this.source = source;
	}





	@Override
    public int hashCode() {
//        int result;
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(source.getCode());
        hcb.append(targetCode);
        return hcb.toHashCode();
    }      
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof QuestionQuestionId)) {
                return false;
            }
            QuestionQuestionId that = (QuestionQuestionId) obj;
            EqualsBuilder eb = new EqualsBuilder();
            eb.append(source.getCode(), that.source.getCode());
            eb.append(targetCode, that.targetCode);
            return eb.isEquals();
        }
        

}