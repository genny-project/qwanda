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

import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.logging.log4j.Logger;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * CoreEntity represents a base level core set of class attributes.
 * It is the base parent for many Qwanda classes and serves to establish
 * Hibernate compatibility and datetime stamping.
 *  This
 * attribute information includes:
 * <ul>
 * <li>The Human Readable name for this class (used for summary lists)
 * <li>The unique code for the class object
 * <li>The description of the class object
 * <li>The created date time
 * <li>The last modified date time for the object
 * </ul>
 *
 * 
 * 
 * @author      Adam Crow
 * @author      Byron Aguirre
 * @version     %I%, %G%
 * @since       1.0
 */

@MappedSuperclass
public abstract class CodedEntity extends CoreEntity {

	/**
	 * Stores logger object.
	 */
	protected static final Logger log =org.apache.logging.log4j.LogManager.getLogger(MethodHandles.lookup().lookupClass().getCanonicalName());

	static public final String REGEX_CODE = "[A-Z]{3}\\_[A-Z0-9\\.\\-\\@\\_]*";



	/**
	A field that stores the unique code name of the attribute.
	<p>p
	Note that the prefix of the attribute can specify the source.
	e.g. FBK_BIRTHDATE indicates that the attribute represents the facebook value
	*/
	@NotNull
	@NotEmpty
	@Size(max = 64)
	@Pattern(regexp = REGEX_CODE, message = "Must be valid Code!")
	@Column(name = "code", updatable = false, nullable = false)	
	private String code;
 
	/**
	  * Constructor.
	  * 
	  * @param none
	  */
	@SuppressWarnings("unused")	
	protected CodedEntity()
	{
		// dummy
		super();
	}
	
	/**
	  * Constructor.
	  * 
	  * @param Name the summary name of the coded entity
	  * @param Code the unique code of the coded entity 
	  */
	public CodedEntity(String aCode, String aName)
	{
		super(aName);
		setCode(aCode);
	}
	
  
	
	
	/**
	 * @return code 
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 
	 * @param aName human readable text representing the unique code
	 */
	public void setCode(String aCode) {
		if (aCode == null) {
			log.error("Null Code passed. Will result in error if saved");
		} else {
			this.code = aCode.toUpperCase();
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString()+"[code=" + code + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
	      HashCodeBuilder hcb = new HashCodeBuilder();
	        hcb.append(code);
	        return hcb.toHashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CodedEntity other = (CodedEntity) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	
	
  
}