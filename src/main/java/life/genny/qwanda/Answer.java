/*
 * (C) Copyright 2017 GADA Technology (http://www.outcome-hub.com/) and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     Adam Crow
 *     Byron Aguirre
 */


package life.genny.qwanda;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import life.genny.qwanda.datatype.LocalDateTimeAdapter;
import life.genny.qwanda.exception.BadDataException;

/**
 * Answer is the abstract base class for all answers
 * managed in the Qwanda library.
 * An Answer object is used as a means of storing information
 * from a source about a target attribute.  This
 * answer information includes:
 * <ul>
 * <li>The Associated Ask
 * <li>The time at which the answer was created
 * <li>The status of the answer e.g Expired, Refused, Answered
 * </ul>
 * <p>
 * Answers represent the manner in which facts about a target
 * from sources are stored.
 * Each Answer is associated with an attribute.
 * <p>
 * 
 * 
 * @author      Adam Crow
 * @author      Byron Aguirre
 * @version     %I%, %G%
 * @since       1.0
 */

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
@Table(name = "answer")
@Entity
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)

public class Answer  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Stores the hibernate generated Id value for this object
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", updatable = false, nullable = false)	
	private Long id;

	
	
	/**
	 * Stores the Created UMT DateTime that this object was created
	 */
//	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @Column(name = "created")
    private LocalDateTime created;

	/**
	 * Stores the Last Modified UMT DateTime that this object was last updated
	 */
//	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @Column(name = "updated")
    private LocalDateTime updated;
    
	
	/**
	A field that stores the human readable value of the answer.
	<p>
	*/
	@NotNull
	@NotEmpty
	@Size(max = 250)
	@Column(name = "value", updatable = true, nullable = false)
	private String value;

	/**
	A field that stores the human readable attributecode associated with this answer.
	<p>
	*/
	@NotNull
	@NotEmpty
	@Size(max = 250)
	@Column(name = "attributecode", updatable = true, nullable = false)
	private String attributeCode;
	
	//@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonIgnore
	@XmlTransient	
	@OneToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "ask_id", nullable = true)
    private Ask ask; 
	
	/**
	 * Store the askId (if present)
	 */
	private Long askId;	
	
	/**
	A field that stores the human readable targetcode associated with this answer.
	<p>
	*/
	@NotNull
	@NotEmpty
	@Size(max = 64)
	@Column(name = "targetcode", updatable = true, nullable = true)
	private String targetCode;

	/**
	A field that stores the human readable sourcecode associated with this answer.
	<p>
	*/
	@NotNull
	@NotEmpty
	@Size(max = 64)
	@Column(name = "sourcecode", updatable = true, nullable = true)
	private String sourceCode;


	/**
	 * Store the Expired boolean value of the attribute for the baseEntity
	 */
	private Boolean expired=false;

	/**
	 * Store the Refused boolean value of the attribute for the baseEntity
	 */
	private Boolean refused=false;

	
	/**
	 * Store the relative importance of the attribute for the baseEntity
	 */
	private Double weight=0.0;

	
	/**
	  * Constructor.
	  * 
	  * @param none
	  */
	@SuppressWarnings("unused")
	private Answer()
	{
		// dummy for hibernate
	}


	
	/**
	  * Constructor.
	  * 
	  * @param sourceCode The unique code for the source associated with this Answer
	  * @param targetCode The unique code for the target associated with this Answer
	  * @param aCode The unique code for the attribute associated with this Answer
	  * @param value The associated String value
	  */
	public Answer(String sourceCode, String targetCode, String attributeCode,String value)
	{
		this.sourceCode = sourceCode;
		this.targetCode = targetCode;
		this.attributeCode = attributeCode;
		this.setValue(value);
		autocreateCreated();
	}
	/**
	  * Constructor.
	  * 
	  * @param aAsk The ask that created this answer
	  * @param value The associated String value
	 * @throws BadDataException 
	  */
	public Answer(Ask aAsk,String value) throws BadDataException
	{
		this.ask = aAsk;
		this.attributeCode = this.ask.getQuestion().getAttribute().getCode();
		this.sourceCode = this.ask.getSource().getCode();
		this.targetCode = this.ask.getTarget().getCode();
		this.setValue(value);
		autocreateCreated();
	//	this.ask.add(this);
	}
	
	/**
	  * Constructor.
	  * 
	  * @param aAsk The ask that created this answer
	  * @param expired  did this ask expire?
	  * @param refused did the user refuse this question?
	 * @throws BadDataException 
	  */
	public Answer(Ask aAsk,Boolean expired, Boolean refused) throws BadDataException
	{
		this.ask = aAsk;
		this.attributeCode = this.ask.getQuestion().getAttribute().getCode();
		this.sourceCode = this.ask.getSource().getCode();
		this.targetCode = this.ask.getTarget().getCode();

		this.setRefused(refused);
		this.setExpired(expired);
		autocreateCreated();
	//	this.ask.add(this);
	}


    /**
	 * @return the created
	 */
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	public LocalDateTime getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	/**
	 * @return the updated
	 */
	public LocalDateTime getUpdated() {
		return updated;
	}

	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}

	@PreUpdate
    public void autocreateUpdate() {
        setUpdated( LocalDateTime.now(ZoneId.of("Z")));
    }
    
    @PrePersist
    public void autocreateCreated() {
    	if (getCreated()==null)
    		setCreated( LocalDateTime.now(ZoneId.of("Z")));
    }

    
	@Transient
	@JsonIgnore
	public Date getCreatedDate()
	{
		Date out = Date.from(created.atZone(ZoneId.systemDefault()).toInstant());
		return out;
	}
	
	@Transient
	@JsonIgnore
	public Date getUpdatedDate()
	{
		Date out = Date.from(updated.atZone(ZoneId.systemDefault()).toInstant());
		return out;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	
	
	
	/**
	 * @return the expired
	 */
	public Boolean getExpired() {
		return expired;
	}

	/**
	 * @param expired the expired to set
	 */
	public void setExpired(Boolean expired) {
		this.expired = expired;
	}

	/**
	 * @return the refused
	 */
	public Boolean getRefused() {
		return refused;
	}

	/**
	 * @param refused the refused to set
	 */
	public void setRefused(Boolean refused) {
		this.refused = refused;
	}

	/**
	 * @return the weight
	 */
	public Double getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
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

	/**
	 * @return the attributeCode
	 */
	public String getAttributeCode() {
		return attributeCode;
	}

	/**
	 * @param attributeCode the attributeCode to set
	 */
	public void setAttributeCode(String attributeCode) {
		this.attributeCode = attributeCode;
	}

	/**
	 * @return the askId
	 */
	public Long getAskId() {
		return askId;
	}

	/**
	 * @param askId the askId to set
	 */
	public void setAskId(Long askId) {
		this.askId = askId;
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
	public void setTargetCode(String targetCode) {
		this.targetCode = targetCode;
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
	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Answer [id=" + id + ", created=" + created + ", value=" + value + ", attributeCode=" + attributeCode
				+ ", askId=" + askId + ", expired=" + expired + ", refused=" + refused + ", weight=" + weight + "]";
	}
	
	
	
}
