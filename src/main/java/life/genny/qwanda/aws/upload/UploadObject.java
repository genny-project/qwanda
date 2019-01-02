package life.genny.qwanda.aws.upload;

import com.google.gson.annotations.Expose;

public class UploadObject {
	
	@Expose
	private String source;
	
	@Expose
	private String id;
	
	@Expose
	private String name;
	
	@Expose
	private String extensions;
	
	@Expose
	private String type;
	
	@Expose
	private String size;
	
	@Expose
	private Boolean isRemote;
	
	@Expose
	private String remote;
	
	@Expose
	private String uploadURL;
	
	@Expose
	private Boolean isPaused;
	
	@Expose
	private Boolean uploaded;
	
	@Expose
	private UploadSubMetaData meta;
	
	@Expose
	private UploadData data;
	
	@Expose
	private UploadProgressData progress;
	
	@Expose
	private UploadXhrData xhrUpload;
	
	@Expose
	private UploadResponse response;

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

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
	 * @return the extensions
	 */
	public String getExtensions() {
		return extensions;
	}

	/**
	 * @param extensions the extensions to set
	 */
	public void setExtensions(String extensions) {
		this.extensions = extensions;
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
	 * @return the size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * @return the isRemote
	 */
	public Boolean getIsRemote() {
		return isRemote;
	}

	/**
	 * @param isRemote the isRemote to set
	 */
	public void setIsRemote(Boolean isRemote) {
		this.isRemote = isRemote;
	}

	/**
	 * @return the remote
	 */
	public String getRemote() {
		return remote;
	}

	/**
	 * @param remote the remote to set
	 */
	public void setRemote(String remote) {
		this.remote = remote;
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
	 * @return the isPaused
	 */
	public Boolean getIsPaused() {
		return isPaused;
	}

	/**
	 * @param isPaused the isPaused to set
	 */
	public void setIsPaused(Boolean isPaused) {
		this.isPaused = isPaused;
	}

	/**
	 * @return the uploaded
	 */
	public Boolean getUploaded() {
		return uploaded;
	}

	/**
	 * @param uploaded the uploaded to set
	 */
	public void setUploaded(Boolean uploaded) {
		this.uploaded = uploaded;
	}

	/**
	 * @return the meta
	 */
	public UploadSubMetaData getMeta() {
		return meta;
	}

	/**
	 * @param meta the meta to set
	 */
	public void setMeta(UploadSubMetaData meta) {
		this.meta = meta;
	}

	/**
	 * @return the data
	 */
	public UploadData getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(UploadData data) {
		this.data = data;
	}

	/**
	 * @return the progress
	 */
	public UploadProgressData getProgress() {
		return progress;
	}

	/**
	 * @param progress the progress to set
	 */
	public void setProgress(UploadProgressData progress) {
		this.progress = progress;
	}

	/**
	 * @return the xhrUpload
	 */
	public UploadXhrData getXhrUpload() {
		return xhrUpload;
	}

	/**
	 * @param xhrUpload the xhrUpload to set
	 */
	public void setXhrUpload(UploadXhrData xhrUpload) {
		this.xhrUpload = xhrUpload;
	}

	/**
	 * @return the response
	 */
	public UploadResponse getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(UploadResponse response) {
		this.response = response;
	}
	
	
}
