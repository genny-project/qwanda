/*
 * (C) Copyright 2017 GADA Technology (http://www.outcome-hub.com/) and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * Contributors: Adam Crow Byron Aguirre
 */


package life.genny.qwanda;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import life.genny.qwanda.exception.BadDataException;

/**
 * Ask represents the presentation of a Question to a source entity. A Question object is refered to
 * as a means of requesting information from a source about a target attribute. This ask information
 * includes:
 * <ul>
 * <li>The source of the answer (Who is being asked the question?)
 * <li>The target of the answer (To whom does the answer refer to?)
 * <li>The text that presents the question to the source
 * <li>The context entities that relate to the question
 * <li>The associated Question object
 * <li>The expiry duration that should be required to answer.
 * <li>The media used to ask this question.
 * <li>The associated answers List
 * </ul>
 * <p>
 * Asks represent the major way of retrieving facts (answers) about a target from sources. Each ask
 * is associated with an question which represents one or more distinct fact about a target.
 * <p>
 * 
 * 
 * @author Adam Crow
 * @author Byron Aguirre
 * @version %I%, %G%
 * @since 1.0
 */

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
@Table(name = "ask")
@Entity
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)

public class Ask extends CoreEntity implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;



  @XmlTransient
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "question_id", nullable = true)
  private Question question;

  // @JsonIgnore
  // @XmlTransient
  // @ManyToOne(fetch = FetchType.EAGER)
  // @JoinColumn(name = "source_id", nullable = false)
  // private BaseEntity source;
  //
  // @JsonIgnore
  // @XmlTransient
  // @ManyToOne(fetch = FetchType.EAGER)
  // @JoinColumn(name = "target_id", nullable = false)
  // private BaseEntity target;

  private String sourceCode;
  private String targetCode;
  private String questionCode;
  private String attributeCode;

  // @Embedded
  // @Valid
  // @JsonInclude(Include.NON_NULL)
  // private AnswerList answerList;

  @Embedded
  @Valid
  @JsonInclude(Include.NON_NULL)
  private ContextList contextList;

  /**
   * Constructor.
   * 
   * @param none
   */
  @SuppressWarnings("unused")
  private Ask() {
    // dummy for hibernate
  }

  /**
   * Constructor.
   * 
   * @param aQuestion The associated Question
   */
  public Ask(final Question aQuestion) {
    super(aQuestion.getName());
    setQuestion(aQuestion);
    // answerList = new AnswerList(new ArrayList<AnswerLink>());
    contextList = new ContextList(new ArrayList<Context>());
  }

  /**
   * Constructor.
   * 
   * @param aAttributeCode The associated Attribute
   * @param aSourceCode The person answering the question
   * @param aTargetCode The BaseEntity that the question is about
   */
  public Ask(final String aAttributeCode, final String aSourceCode, final String aTargetCode,
      final String name) {
    super(name);

    // this.source = aSource;
    // this.target = aTarget;
    this.sourceCode = aSourceCode;
    this.targetCode = aTargetCode;
    this.attributeCode = aAttributeCode;
    // answerList = new AnswerList(new ArrayList<AnswerLink>());
    contextList = new ContextList(new ArrayList<Context>());
  }



  /**
   * Constructor.
   * 
   * @param aQuestion The associated Question
   * @param aSource The person answering the question
   * @param aTarget The BaseEntity that the question is about
   */
  public Ask(final Question aQuestion, final String aSourceCode, final String aTargetCode) {
    super(aQuestion.getName());
    setQuestion(aQuestion);

    this.sourceCode = aSourceCode;
    this.targetCode = aTargetCode;
    this.attributeCode = aQuestion.getAttribute().getCode();
    // answerList = new AnswerList(new ArrayList<AnswerLink>());
    contextList = new ContextList(new ArrayList<Context>());
  }

  /**
   * @return the question
   */
  public Question getQuestion() {
    return question;
  }

  /**
   * @param question the question to set
   */
  public void setQuestion(final Question question) {
    this.question = question;
    this.questionCode = question.getCode();
    this.attributeCode = question.getAttribute().getCode();
  }

  // /**
  // * @return the answerList
  // */
  // public AnswerList getAnswerList() {
  // return answerList;
  // }

  // /**
  // * @param answerList the answerList to set
  // */
  // public void setAnswerList(final AnswerList answerList) {
  // this.answerList = answerList;
  // }



  /**
   * @return the contextList
   */
  public ContextList getContextList() {
    return contextList;
  }

  /**
   * @param contextList the contextList to set
   */
  public void setContextList(final ContextList contextList) {
    this.contextList = contextList;
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
  private void setSourceCode(final String sourceCode) {
    this.sourceCode = sourceCode;
  }

  /**
   * @return the targetCode
   */
  public String getTargetCode() {
    return targetCode;
  }



  /**
   * @return the questionCode
   */
  public String getQuestionCode() {
    return questionCode;
  }

  /**
   * @param questionCode the questionCode to set
   */
  public void setQuestionCode(final String questionCode) {
    this.questionCode = questionCode;
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

  /**
   * @param targetCode the targetCode to set
   */
  private void setTargetCode(final String targetCode) {
    this.targetCode = targetCode;
  }

  public void add(final Answer answer) throws BadDataException {
    if ((answer.getSourceCode().equals(sourceCode)) && (answer.getTargetCode().equals(targetCode))
        && (answer.getAttributeCode().equals(attributeCode))) {
      // getAnswerList().getAnswerList().add(new AnswerLink(source, target, answer));
    } else {
      throw new BadDataException("Source / Target ids do not match Ask");
    }

  }

}
