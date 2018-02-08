package life.genny.qwanda.message;

import java.io.Serializable;
import java.time.LocalDateTime;

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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.gson.annotations.Expose;

import life.genny.qwanda.datatype.LocalDateTimeAdapter;

@SuppressWarnings("deprecation")
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
@Table(name = "template")
@Entity
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class QBaseMSGMessageTemplate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", updatable = false, nullable = false)
	@Expose
	private Long id;
	
	
	/**
	 * Stores the Created UMT DateTime that this object was created
	 */
    @Column(name = "created")
    @Expose
    private LocalDateTime created;
    
    
    /**
	A field that stores the code.
	<p>
	*/
	@NotNull
	@NotEmpty
	@Size(max = 32)
	@Column(name = "code", updatable = true, nullable = false)
	@Expose
	private String code;
	
	
	/**
	A field that stores the description.
	<p>
	*/
	@NotNull
	@NotEmpty
	@Column(name = "description", updatable = true, nullable = false)
	@Expose
	private String description;
	
	
	/**
	A field that stores the message subject.
	<p>
	*/
	@NotNull
	@NotEmpty
	@Column(name = "subject", updatable = true, nullable = false)
	@Expose
	private String subject;
	
	
	/**
	A field that stores the email template doc id.
	<p>
	*/
	@NotNull
	@NotEmpty
	@Column(name = "email", updatable = true, nullable = false)
	@Expose
	private String email_templateId;
	
	
	/**
	A field that stores the email template doc id.
	<p>
	*/
	@NotNull
	@Column(name = "sms", updatable = true, nullable = false)
	@Expose
	private String sms_template;
	
	
	/**
	 * A field that stores the toast template doc id.
	 */
	@NotNull
	@Column(name = "toast", updatable = true, nullable = false)
	@Expose
	private String toast_template;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	public LocalDateTime getCreated() {
		return created;
	}


	public void setCreated(LocalDateTime created) {
		this.created = created;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getEmail_templateId() {
		return email_templateId;
	}


	public void setEmail_templateId(String email_templateId) {
		this.email_templateId = email_templateId;
	}


	public String getSms_template() {
		return sms_template;
	}


	public void setSms_template(String sms_template) {
		this.sms_template = sms_template;
	}
	

	/**
	 * @return the toast_template
	 */
	public String getToast_template() {
		return toast_template;
	}


	/**
	 * @param toast_template the toast_template to set
	 */
	public void setToast_template(String toast_template) {
		this.toast_template = toast_template;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QBaseMSGMessageTemplate [id=" + id + ", created=" + created + ", code=" + code + ", description="
				+ description + ", subject=" + subject + ", email_templateId=" + email_templateId + ", sms_template="
				+ sms_template + ", toast_template=" + toast_template + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QBaseMSGMessageTemplate other = (QBaseMSGMessageTemplate) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
