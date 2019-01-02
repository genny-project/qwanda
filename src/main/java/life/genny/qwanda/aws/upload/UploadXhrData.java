package life.genny.qwanda.aws.upload;

import com.google.gson.annotations.Expose;

public class UploadXhrData {

	@Expose
	private String method;
	
	@Expose
	private Boolean formData;
	
	@Expose
	private String endpoint;
	
	@Expose
	private int[] metaFields;

	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @return the formData
	 */
	public Boolean getFormData() {
		return formData;
	}

	/**
	 * @param formData the formData to set
	 */
	public void setFormData(Boolean formData) {
		this.formData = formData;
	}

	/**
	 * @return the endpoint
	 */
	public String getEndpoint() {
		return endpoint;
	}

	/**
	 * @param endpoint the endpoint to set
	 */
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	/**
	 * @return the metaFields
	 */
	public int[] getMetaFields() {
		return metaFields;
	}

	/**
	 * @param metaFields the metaFields to set
	 */
	public void setMetaFields(int[] metaFields) {
		this.metaFields = metaFields;
	}
	
	
}
