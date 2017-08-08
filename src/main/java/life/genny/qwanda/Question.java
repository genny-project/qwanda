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
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import life.genny.qwanda.attribute.Attribute;
import life.genny.qwanda.exception.BadDataException;

/**
 * Question is the abstract base class for all questions
 * managed in the Qwanda library.
 * A Question object is used as a means of requesting information
 * from a source about a target attribute.  This
 * question information includes:
 * <ul>
 * <li>The Human Readable name for this question (used for summary lists)
 * <li>A title for the question
 * <li>The text that presents the default question to the source
 * <li>The attribute that the question serves to fill
 * <li>The contexts that are mandatory for this question
 * <li>The default expiry duration that should be required to answer.
 * <li>The default media used to ask this question.
 * </ul>
 * <p>
 * Questions represent the major way of retrieving facts about a target
 * from sources.
 * Each question is associated with an attribute which represents a distinct
 * fact about a target.
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
@Table(name = "question", uniqueConstraints = @UniqueConstraint(columnNames = "code"))
@Entity
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)

public class Question extends CodedEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_CODE_PREFIX = "QUE_";

	@JsonIgnore
    @XmlTransient
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.parent", cascade=CascadeType.ALL)	 
	private Set<QuestionQuestion> childQuestions = new HashSet<QuestionQuestion>(0);

	
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@XmlTransient	
	@OneToOne( fetch = FetchType.EAGER)
	@JoinColumn(name = "attribute_id", nullable = false)
    private Attribute attribute; 

	@Embedded
	@Valid
	private ContextList contextList;	

	
	/**
	  * Constructor.
	  * 
	  * @param none
	  */
	@SuppressWarnings("unused")
	private Question()
	{
		// dummy for hibernate
	}
	
	/**
	  * Constructor.
	  * 
	  * @param aCode The unique code for this Question
	  * @param aName The human readable summary name
	  * @param attribute The associated attribute
	  */
	public Question(String aCode, String aName, Attribute aAttribute)
	{
		super(aCode, aName);
		this.attribute = aAttribute;
	}

	/**
	 * @return the attribute
	 */
	public Attribute getAttribute() {
		return attribute;
	}

	/**
	 * @param attribute the attribute to set
	 */
	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	/**
	 * @return the contextList
	 */
	public ContextList getContextList() {
		return contextList;
	}

	/**
	 * @param contextList the contextList to set
	 */
	public void setContextList(ContextList contextList) {
		this.contextList = contextList;
	}
	
	
	
	
	/**
	 * @return the childQuestions
	 */
	public Set<QuestionQuestion> getChildQuestions() {
		return childQuestions;
	}

	/**
	 * @param childQuestions the childQuestions to set
	 */
	public void setChildQuestions(Set<QuestionQuestion> childQuestions) {
		this.childQuestions = childQuestions;
	}

	/**
	 * getDefaultCodePrefix This method is overrides the Base class
	 * 
	 * @return the default Code prefix for this class.
	 */
	static public String getDefaultCodePrefix() {
		return DEFAULT_CODE_PREFIX;
	}
	
	/**
	 * addChildQuestion This adds an child Question with default weight of 0.0 to the question. It auto creates the 
	 * QuestionQuestion object.
	 * For efficiency we assume the child question link does not exist
	 * @param ea
	 * @throws BadDataException
	 */
	public void addChildQuestion(QuestionQuestion qq) throws BadDataException
	{
		if (qq == null ) throw new BadDataException("missing Question");
		
		addChildQuestion(qq.getChild(),qq.getWeight(),qq.getMandatory());
	}	

	/**
	 * addChildQuestion This adds an child question and associated weight to the question group. It auto creates the 
	 * QuestionQuestion object.
	 * For efficiency we assume the question link does not already exist
	 * @param childQuestion
	 * @param weight
	 * @throws BadDataException
	 */
	public void addChildQuestion(Question childQuestion) throws BadDataException
	{
		
		addChildQuestion(childQuestion, 1.0);
	}

	/**
	 * addChildQuestion This adds a child question and associated weight to the question group with no mandatory. It auto creates the 
	 * QuestionQuestion object.
	 * For efficiency we assume the question link does not already exist
	 * @param childQuestion
	 * @param weight
	 * @throws BadDataException
	 */
	public void addChildQuestion(Question childQuestion, Double weight) throws BadDataException 
	{
		addChildQuestion(childQuestion,weight,false);
	}

	/**
	 * addChildQuestion This adds a child question and associated weight and mandatory setting to the question group. It auto creates the 
	 * QuestionQuestion object.
	 * For efficiency we assume the question link does not already exist
	 * @param childQuestion
	 * @param weight
	 * @param mandatory setting
	 * @throws BadDataException
	 */
	public void addChildQuestion(Question childQuestion, Double weight, Boolean mandatory) throws BadDataException
	{
		if (childQuestion == null ) throw new BadDataException("missing Question");
		if (weight == null ) throw new BadDataException("missing weight");
		if (mandatory == null) throw new BadDataException("missing mandatory setting");
		
		QuestionQuestion questionLink = new QuestionQuestion(this, childQuestion, weight, mandatory);
		getChildQuestions().add(questionLink);
	}	
	
	/**
	 * removeChildQuestion This removes a child Question from the question group. 
	 * For efficiency we assume the child question exists
	 * @param questionCode
	 */
	public void removeChildQuestion(String childQuestionCode)
	{
		Optional<QuestionQuestion> optQuestionQuestion = findQuestionLink(childQuestionCode);
		getChildQuestions().remove(optQuestionQuestion);
	}

	/**
	 * findChildQuestion This returns an QuestionLink if it exists in the question group. 
	 * @param childQuestionCode
	 * @returns Optional<QuestionQuestion>
	 */
	public Optional<QuestionQuestion> findQuestionLink(String childQuestionCode)
	{
		Optional<QuestionQuestion> foundEntity = Optional.of(getChildQuestions().parallelStream()
        .filter(x -> (x.getChild().getCode().equals(childQuestionCode)))
        .findFirst()
        .get());
		
	
		return foundEntity;
	}
	
	/**
	 * findQuestionQuestion This returns an question link if it exists in the question group. 
	 * Could be more efficient in retrival (ACC: test)
	 * @param questionLink
	 * @returns QuestionQuestion
	 */
	public QuestionQuestion findQuestionQuestion(Question childQuestion)
	{
		QuestionQuestion foundEntity = getChildQuestions().parallelStream()
        .filter(x -> (x.getChild().equals(childQuestion)))
        .findFirst()
        .get();
		
		return foundEntity;
	}
	
}