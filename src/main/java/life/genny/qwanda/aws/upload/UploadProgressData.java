package life.genny.qwanda.aws.upload;

import com.google.gson.annotations.Expose;

public class UploadProgressData {
	
	@Expose
	private String uploadStarted;
	
	@Expose
	private Boolean uploadComplete;
	
	@Expose
	private int percentage;
	
	@Expose
	private int bytesUploaded;
	
	@Expose
	private int bytesTotal;

	/**
	 * @return the uploadStarted
	 */
	public String getUploadStarted() {
		return uploadStarted;
	}

	/**
	 * @param uploadStarted the uploadStarted to set
	 */
	public void setUploadStarted(String uploadStarted) {
		this.uploadStarted = uploadStarted;
	}

	/**
	 * @return the uploadComplete
	 */
	public Boolean getUploadComplete() {
		return uploadComplete;
	}

	/**
	 * @param uploadComplete the uploadComplete to set
	 */
	public void setUploadComplete(Boolean uploadComplete) {
		this.uploadComplete = uploadComplete;
	}

	/**
	 * @return the percentage
	 */
	public int getPercentage() {
		return percentage;
	}

	/**
	 * @param percentage the percentage to set
	 */
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	/**
	 * @return the bytesUploaded
	 */
	public int getBytesUploaded() {
		return bytesUploaded;
	}

	/**
	 * @param bytesUploaded the bytesUploaded to set
	 */
	public void setBytesUploaded(int bytesUploaded) {
		this.bytesUploaded = bytesUploaded;
	}

	/**
	 * @return the bytesTotal
	 */
	public int getBytesTotal() {
		return bytesTotal;
	}

	/**
	 * @param bytesTotal the bytesTotal to set
	 */
	public void setBytesTotal(int bytesTotal) {
		this.bytesTotal = bytesTotal;
	}


}
