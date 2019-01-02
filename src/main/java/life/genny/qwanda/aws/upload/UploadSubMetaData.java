package life.genny.qwanda.aws.upload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadSubMetaData {
	
	@Expose
	private String name;
	
	@Expose
	private String type;
	
	@Expose
	private String acl;
	
	@Expose
	private String key;
	
	@Expose
	private int success_action_status;
	
	@Expose
	@SerializedName("content-type")
	private String contentType;
	
	@Expose
	private String bucket;
	
	@Expose
	@SerializedName("X-Amz-Algorithm")
	private String XAmzAlgorithm;
	
	@Expose
	@SerializedName("X-Amz-Credential")
	private String XAmzCredential;
	
	@Expose
	@SerializedName("X-Amz-Date")
	private String XAmzDate;
	
	@Expose
	@SerializedName("Policy")
	private String policy;
	
	@Expose
	@SerializedName("X-Amz-Signature")
	private String XAmzSignature;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the acl
	 */
	public String getAcl() {
		return acl;
	}

	/**
	 * @param acl the acl to set
	 */
	public void setAcl(String acl) {
		this.acl = acl;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the success_action_status
	 */
	public int getSuccess_action_status() {
		return success_action_status;
	}

	/**
	 * @param success_action_status the success_action_status to set
	 */
	public void setSuccess_action_status(int success_action_status) {
		this.success_action_status = success_action_status;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * @return the bucket
	 */
	public String getBucket() {
		return bucket;
	}

	/**
	 * @param bucket the bucket to set
	 */
	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	/**
	 * @return the xAmzAlgorithm
	 */
	public String getXAmzAlgorithm() {
		return XAmzAlgorithm;
	}

	/**
	 * @param xAmzAlgorithm the xAmzAlgorithm to set
	 */
	public void setXAmzAlgorithm(String xAmzAlgorithm) {
		XAmzAlgorithm = xAmzAlgorithm;
	}

	/**
	 * @return the xAmzCredential
	 */
	public String getXAmzCredential() {
		return XAmzCredential;
	}

	/**
	 * @param xAmzCredential the xAmzCredential to set
	 */
	public void setXAmzCredential(String xAmzCredential) {
		XAmzCredential = xAmzCredential;
	}

	/**
	 * @return the xAmzDate
	 */
	public String getXAmzDate() {
		return XAmzDate;
	}

	/**
	 * @param xAmzDate the xAmzDate to set
	 */
	public void setXAmzDate(String xAmzDate) {
		XAmzDate = xAmzDate;
	}

	/**
	 * @return the policy
	 */
	public String getPolicy() {
		return policy;
	}

	/**
	 * @param policy the policy to set
	 */
	public void setPolicy(String policy) {
		this.policy = policy;
	}

	/**
	 * @return the xAmzSignature
	 */
	public String getXAmzSignature() {
		return XAmzSignature;
	}

	/**
	 * @param xAmzSignature the xAmzSignature to set
	 */
	public void setXAmzSignature(String xAmzSignature) {
		XAmzSignature = xAmzSignature;
	}
	
	
}
