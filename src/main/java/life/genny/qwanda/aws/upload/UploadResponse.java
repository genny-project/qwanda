package life.genny.qwanda.aws.upload;

import com.google.gson.annotations.Expose;

public class UploadResponse {
	
	@Expose
	private int status;
	
	@Expose
	private String uploadURL;
	
	@Expose
	private UploadResponseBody body;

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the uploadURL
	 */
	public String getUploadURL() {
		return uploadURL;
	}

	/**
	 * @param uploadURL the uploadURL to set
	 */
	public void setUploadURL(String uploadURL) {
		this.uploadURL = uploadURL;
	}

	/**
	 * @return the body
	 */
	public UploadResponseBody getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(UploadResponseBody body) {
		this.body = body;
	}

	
}
