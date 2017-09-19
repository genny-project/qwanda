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

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import life.genny.qwanda.datatype.LocalDateTimeAdapter;

/**
 * gps is the abstract base class for all gps
 * managed in the Qwanda library.
 * An GPS object is used as a means of storing information
 * from a source about a target GPS.  This
 * gps information includes:
 * <ul>
 * <li>Longitude
 * <li>The time at which the gps was created
 * <li>Latitude
 * <li>Bearing
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
@Table(name = "gps")
@Entity
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)

public class GPS  implements Serializable {
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
	A field that stores the latitude.
	<p>
	*/
	@NotNull
	@NotEmpty
	@Size(max = 32)
	@Column(name = "latitude", updatable = false, nullable = false)
	private String latitude;

    /**
    A field that stores the longitude.
    <p>
    */
    @NotNull
    @NotEmpty
    @Size(max = 32)
    @Column(name = "longitude", updatable = false, nullable = false)
    private String longitude;

    /**
    A field that stores the bearing/heading.
    <p>
    */
    @NotNull
    @NotEmpty
    @Size(max = 32)
    @Column(name = "bearing", updatable = false, nullable = true)
    private String bearing;
    
	/**
	A field that stores the human readable targetcode associated with this answer.
	<p>
	*/
	@NotNull
	@NotEmpty
	@Size(max = 64)
	@Column(name = "targetcode", updatable = true, nullable = true)
	private String targetCode;

    @Column(name = "targetid", updatable = true, nullable = true)
	private Long targetId;

	
	/**
	  * Constructor.
	  * 
	  * @param none
	  */
	@SuppressWarnings("unused")
	private GPS()
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
	public GPS(final String targetCode,final String latitude, final String longitude, final String bearing)
	{
		this.targetCode = targetCode;
		this.setLatitude(latitude);
		this.setLongitude(longitude);
		this.setBearing(bearing);
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


    
    @PrePersist
    public void autocreateCreated() {
    	if (getCreated()==null)
    		setCreated( LocalDateTime.now(ZoneId.of("Z")));
    }

    
	@Transient
	@JsonIgnore
	public Date getCreatedDate()
	{
		final Date out = Date.from(created.atZone(ZoneId.systemDefault()).toInstant());
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
	public void setId(final Long id) {
		this.id = id;
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
	public void setTargetCode(final String targetCode) {
		this.targetCode = targetCode;
	}



  /**
   * @return the latitude
   */
  public String getLatitude() {
    return latitude;
  }



  /**
   * @param latitude the latitude to set
   */
  private void setLatitude(final String latitude) {
    this.latitude = latitude;
  }



  /**
   * @return the longitude
   */
  public String getLongitude() {
    return longitude;
  }



  /**
   * @param longitude the longitude to set
   */
  private void setLongitude(final String longitude) {
    this.longitude = longitude;
  }



  /**
   * @return the bearing
   */
  public String getBearing() {
    return bearing;
  }



  /**
   * @param bearing the bearing to set
   */
  private void setBearing(final String bearing) {
    this.bearing = bearing;
  }



  /**
   * @return the targetId
   */
  public Long getTargetId() {
    return targetId;
  }



  /**
   * @param targetId the targetId to set
   */
  public void setTargetId(final Long targetId) {
    this.targetId = targetId;
  }



  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "GPS [id=" + id + ", created=" + created + ", latitude=" + latitude + ", longitude="
        + longitude + ", bearing=" + bearing + ", targetCode=" + targetCode + "]";
  }


	
}
