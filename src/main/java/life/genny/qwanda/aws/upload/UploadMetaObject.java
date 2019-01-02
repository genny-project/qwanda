package life.genny.qwanda.aws.upload;

import com.google.gson.annotations.Expose;

public class UploadMetaObject {
	
	@Expose
	private UploadObject[] uploadObj;

	/**
	 * @return the uploadObj
	 */
	public UploadObject[] getUploadObj() {
		return uploadObj;
	}

	/**
	 * @param uploadObj the uploadObj to set
	 */
	public void setUploadObj(UploadObject[] uploadObj) {
		this.uploadObj = uploadObj;
	}

}
