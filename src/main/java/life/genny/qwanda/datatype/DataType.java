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

package life.genny.qwanda.datatype;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;

import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import life.genny.qwanda.converter.ValidationListConverter;
import life.genny.qwanda.validation.Validation;
import life.genny.qwanda.validation.ValidationList;


/**
 * DataType represents a distinct abstract Data Representation in the Qwanda library. The data types
 * express the format and the validations required for values collected. In addition to the extended
 * CoreEntity this information includes:
 * <ul>
 * <li>The code type of the base data e.g. Text, Integer, etc.
 * <li>The List of default Validation items
 * <li>The default mask used for data entry
 * </ul>
 * <p>
 * 
 * <p>
 * 
 * 
 * @author Adam Crow
 * @author Byron Aguirre
 * @version %I%, %G%
 * @since 1.0
 */



@Embeddable
public class DataType implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @NotNull
  @NotEmpty
  @Size(max = 120)
  @Expose
  private String className; // e.g. java.util.String


  /**
   * A fieldlist that stores the validations for this object.
   * <p>
   * Note that this is stored into a single object
   */

  @Column(name = "validation_list", length = 512)
  @Convert(converter = ValidationListConverter.class)
  @Expose
  private List<Validation> validationList = new ArrayList<Validation>(0);


  /**
   * Constructor.
   * 
   * @param none
   */
  @SuppressWarnings("unused")
  protected DataType() {
    super();
    // dummy for hibernate
  }

  public DataType(final Class clazz) {
    this(clazz, new ValidationList());
  }

  public DataType(final String className, final ValidationList aValidationList) {
    setClassName(className);
    setValidationList(aValidationList.getValidationList());
  }

  public DataType(final Class clazz, final ValidationList aValidationList) {
    this(clazz.getCanonicalName(), aValidationList);
  }

  /**
   * @return the validationList
   */
  public List<Validation> getValidationList() {
    return validationList;
  }

  /**
   * @param validationList the validationList to set
   */
  public void setValidationList(final List<Validation> validationList) {
    this.validationList = validationList;
  }

  /**
   * @return the className
   */
  public String getClassName() {
    return className;
  }

  /**
   * @param className the className to set
   */
  public void setClassName(final String className) {
    this.className = className;
  }

  @JsonIgnore
  @Transient
  @XmlTransient
  public void setClass(final Class clazz) {
    final String simpleClassName = clazz.getCanonicalName();
    setClassName(simpleClassName);
  }



  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "DType(" + className + ")";
  }

static public DataType getInstance(final String className)
{
	final List<Validation> validationList = new ArrayList<Validation>(0);
	ValidationList vlist = new ValidationList(validationList);
	DataType dataTypeInstance = new DataType(className,vlist);
	return dataTypeInstance;
}
  
}
