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

package life.genny.qwanda.attribute;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.gson.annotations.Expose;

import life.genny.qwanda.CodedEntity;
import life.genny.qwanda.datatype.DataType;

/**
 * Attribute represents a distinct abstract Fact about a target entity
 * managed in the Qwanda library.
 * An attribute may be used directly in processing meaning for a target
 * entity. Such processing may be in relation to a comparison score against
 * another target entity, or to generate more attribute information via
 * inference and induction  This
 * attribute information includes:
 * <ul>
 * <li>The Human Readable name for this attibute (used for summary lists)
 * <li>The unique code for the attribute
 * <li>The description of the attribute
 * <li>The answerType that represents the format of the attribute
 * </ul>
 * <p>
 * Attributes represent facts about a target.
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
@Table(name = "attribute", uniqueConstraints = @UniqueConstraint(columnNames = "code"))
@Entity
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)


public class Attribute extends CodedEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_CODE_PREFIX = "PRI_";

//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "pk.attribute")
//	@JsonManagedReference(value="attribute")
//	 @JsonIgnore
//	private Set<EntityAttribute> baseEntityAttributes = new HashSet<EntityAttribute>(0);

	@Embedded
	@NotNull
	@Expose
	public DataType dataType;
	
	@Expose
	private Boolean defaultPrivacyFlag = false;


	
	/**
	  * Constructor.
	  * 
	  * @param none
	  */
	@SuppressWarnings("unused")
	protected Attribute()
	{
		super();
		// dummy for hibernate
	}
	

	public Attribute(String aCode, String aName, DataType dataType)
	{
		super(aCode, aName);
		setDataType(dataType);
	}
	
	/**
	 * @return the dataType
	 */
	public DataType getDataType() {
		return dataType;
	}

	/**
	 * @param dataType the dataType to set
	 */
	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	/**
	 * getDefaultCodePrefix This method is overrides the Base class
	 * 
	 * @return the default Code prefix for this class.
	 */
	static public String getDefaultCodePrefix() {
		return DEFAULT_CODE_PREFIX;
	}


	@Override
	public String toString() {
		return getCode()+ ",dataType=" + dataType;
	}


	/**
	 * @return the defaultPrivacyFlag
	 */
	public Boolean getDefaultPrivacyFlag() {
		return defaultPrivacyFlag;
	}


	/**
	 * @param defaultPrivacyFlag the defaultPrivacyFlag to set
	 */
	public void setDefaultPrivacyFlag(Boolean defaultPrivacyFlag) {
		this.defaultPrivacyFlag = defaultPrivacyFlag;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	/*@Override
	public String toString() {
		return "Attribute:"+getCode()+"(" + getDataType()+") ";
	}*/
	
	
	

}