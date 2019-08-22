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

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.builder.CompareToBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.Expose;

import life.genny.qwanda.entity.BaseEntity;

/**
 * Context is the class for all entity contexts managed in the Qwanda library. A
 * Context object is used as a means of supplying information about a question
 * that assists in understanding what answer is required. This context
 * information includes:
 * <ul>
 * <li>The name of the context class
 * <li>The context unique code
 * <li>The key String for this context e.g. "employee" or "footballer" this is
 * saved in name field
 * </ul>
 * <p>
 * Contexts represent the major way of supplying info about a question that
 * permits a source to make a full decision. Contexts are also used in message
 * merging.
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
@Table(name = "context", 
indexes = {
        @Index(columnList = "id", name =  "code_idx"),
        @Index(columnList = "realm", name = "code_idx")
    },
uniqueConstraints = @UniqueConstraint(columnNames = {"id", "realm"}))
@Entity
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)

public class Context extends CoreEntity implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@XmlTransient
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "attribute_id", nullable = false)
	private BaseEntity entity;

	@Expose
	private Double weight = 1.0;

	@Expose
	private String contextCode;

	@Expose
	private String dataType;

	@Expose
	private VisualControlType visualControlType;


	/**
	 * @return the visualControlType
	 */
	public VisualControlType getVisualControlType() {
		return visualControlType;
	}

	/**
	 * @param visualControlType the visualControlType to set
	 */
	public void setVisualControlType(VisualControlType visualControlType) {
		this.visualControlType = visualControlType;
	}

	/**
	 * Constructor.
	 *
	 * @param none
	 */
	@SuppressWarnings("unused")
	private Context() {
		// dummy for hibernate
	}

	/**
	 * Constructor.
	 *
	 * @param aCode
	 *            The unique code for this Question
	 * @param aName
	 *            The human readable summary name
	 * @param attribute
	 *            The associated attribute
	 */
	public Context(ContextType key, BaseEntity aEntity) {
		super(key.contextType());
		this.entity = aEntity;
		this.contextCode = aEntity.getCode();
	}

	public Context(ContextType key, BaseEntity aEntity, VisualControlType visualControlType) {
		super(key.contextType());
		this.entity = aEntity;
		this.contextCode = aEntity.getCode();
		this.visualControlType = visualControlType;
	}
	public Context(ContextType key, BaseEntity aEntity, VisualControlType visualControlType, Double weight) {
		super(key.contextType());
		this.entity = aEntity;
		this.contextCode = aEntity.getCode();
		this.visualControlType = visualControlType;
		this.weight = weight;
	}

	/**
	 * @return the entity
	 */
	public BaseEntity getEntity() {
		return entity;
	}

	/**
	 * @return the weight
	 */
	public Double getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(final Double weight) {
		this.weight = weight;
	}




	/**
	 * @return the dataType
	 */
	public String getDataType() {
		return dataType;
	}

	/**
	 * @param dataType the dataType to set
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	@Override
	 public int compareTo(Object o) {
		 Context myClass = (Context) o;
	     return new CompareToBuilder()
	       .append(entity,myClass.getEntity())
	       .toComparison();
	   }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getName()+":"+getEntity().getCode();
	}


}