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

package life.genny.qwanda.validation;

import java.io.Serializable;
import java.util.regex.PatternSyntaxException;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import life.genny.qwanda.CodedEntity;

/**
 * Validation represents a distinct abstract Validation Representation in the Qwanda library.
 * The validations are applied to values.
 * In addition to the extended CoreEntity this information includes:
 * <ul>
 * <li>Regex 
 * </ul>
 * <p>
 * 
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
@Table(name = "validation", uniqueConstraints = @UniqueConstraint(columnNames = "code"))
@Entity
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)

public class Validation extends CodedEntity implements Serializable {
	
	/* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Validation [regex=" + regex + "]";
  }

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_CODE_PREFIX = "VLD_";

	/**
	A field that stores the validation regex.
	<p>
	Note that this regex needs to be applied to the complete value (Not partial).
	*/
	@NotNull
	@NotEmpty
	@Size(max = 128)
	@Column(name = "regex", updatable = true, nullable = false)	
	private String regex;
 
	
	/**
	  * Constructor.
	  * 
	  * @param none
	  */
	@SuppressWarnings("unused")
	protected Validation()
	{
		super();
		// dummy for hibernate
	}
	
	public Validation(String aCode, String aName, String aRegex) throws PatternSyntaxException
	{
		super(aCode, aName);
		setRegex(aRegex);
	}

	/**
	 * @return the regex
	 */
	public String getRegex() {
		return regex;
	}

	/**
	 * @param regex the regex to set
	 */
	public void setRegex(String regex) throws PatternSyntaxException {
		
		validateRegex(regex);  // confirm the regex is valid, if invalid throws PatternSyntaxException
		this.regex = regex;
	}

	
	/**
	 * @param regex
	 */
	static public void validateRegex(String regex) {
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(regex);
	}

	/**
	 * getDefaultCodePrefix This method is overrides the Base class
	 * 
	 * @return the default Code prefix for this class.
	 */
	static public String getDefaultCodePrefix() {
		return DEFAULT_CODE_PREFIX;
	}
}