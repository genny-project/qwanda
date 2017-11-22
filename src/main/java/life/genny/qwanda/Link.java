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

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
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
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import life.genny.qwanda.attribute.Attribute;
import life.genny.qwanda.datatype.LocalDateTimeAdapter;
import life.genny.qwanda.entity.BaseEntity;
import life.genny.qwanda.exception.BadDataException;

/**
 * A Link object
 * is used as a means of storing information from a source to a target attribute. This link
 * information includes:
 * <ul>
 * <li>The SourceCode
 * <li>The TargetCode
 * <li> The LinkCode
 * <li> The Value String
 * <li> The weight
 * <li>The time at which the answer was created
 * <li>The status of the answer e.g Expired, Refused, Answered
 * </ul>
 * <p>
 * Answers represent the manner in which facts about a target from sources are stored. Each Answer
 * is associated with an attribute.
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

public class Link implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

 
  /**
   * Stores the Created UMT DateTime that this object was created
   */
  // @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
  @Column(name = "created")
  private LocalDateTime created;

  /**
   * Stores the Last Modified UMT DateTime that this object was last updated
   */
  // @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
  @Column(name = "updated")
  private LocalDateTime updated;


  /**
   * A field that stores the human readable value of the answer.
   * <p>
   */
  private String value;

  /**
   * A field that stores the human readable attributecode associated with this answer.
   * <p>
   */
  private String attributeCode;

 
  /**
   * A field that stores the human readable targetcode associated with this answer.
   * <p>
   */
  private String targetCode;

  /**
   * A field that stores the human readable sourcecode associated with this answer.
   * <p>
   */
  private String sourceCode;


  /**
   * Store the relative importance of the attribute for the baseEntity
   */
  private Double weight = 0.0;


  /**
   * Constructor.
   * 
   * @param none
   */
  @SuppressWarnings("unused")
  private Link() {
    // dummy for hibernate
  }



  /**
   * Constructor.
   * 
   * @param source The source associated with this Answer
   * @param target The target associated with this Answer
   * @param attribute The attribute associated with this Answer
   * @param value The associated String value
   * @param weight the associated weight of the value (think credibility) usually 1.0
   */
  public Link(final String source, final String target, final String linkCode, final String value, Double weight) {
    this.sourceCode = sourceCode;
    this.targetCode = targetCode;
    this.attributeCode = linkCode;
    this.weight = weight;
    this.setValue(value);
    autocreateCreated();
  }

  /**
   * Constructor.
   * 
   * @param source The source associated with this Answer
   * @param target The target associated with this Answer
   * @param attribute The attribute associated with this Answer
   */
  public Link(final String source, final String target, final String linkCode) {
    this.sourceCode = sourceCode;
    this.targetCode = targetCode;
    this.attributeCode = linkCode;
    this.weight = 1.0;
    this.setValue("LINK");
    autocreateCreated();
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
  public void setCreated(final LocalDateTime created) {
    this.created = created;
  }

 

  public void autocreateCreated() {
    if (getCreated() == null)
      setCreated(LocalDateTime.now(ZoneId.of("Z")));
  }


  @JsonIgnore
  public Date getCreatedDate() {
    final Date out = Date.from(created.atZone(ZoneId.systemDefault()).toInstant());
    return out;
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

 

}
