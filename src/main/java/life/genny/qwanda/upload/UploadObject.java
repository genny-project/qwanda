package life.genny.qwanda.upload;

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
	
	

}
