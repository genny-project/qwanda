package life.genny.qwanda.message;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class MessageData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return " MessageData [code=" + code + " id= " + id + "]";
	}

	@Expose
	private String code;

	@Expose
	private String parentCode;

	@Expose
	private String rootCode;

	@Expose
	private String targetCode;
	
	@Expose
	private String sourceCode;
	

	
	
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the parentCode
	 */
	public String getParentCode() {
		return parentCode;
	}

	/**
	 * @param parentCode the parentCode to set
	 */
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	/**
	 * @return the rootCode
	 */
	public String getRootCode() {
		return rootCode;
	}

	/**
	 * @param rootCode the rootCode to set
	 */
	public void setRootCode(String rootCode) {
		this.rootCode = rootCode;
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
	public void setTargetCode(String targetCode) {
		this.targetCode = targetCode;
	}
	
	/**
	 * @return the targetCode
	 */
	public String getSourceCode() {
		return sourceCode;
	}

	/**
	 * @param targetCode the targetCode to set
	 */
	public void getSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	
	
	
	

	@Expose
	private Long id;
	@Expose
	private String value;
	@Expose
	private String content;

	public MessageData(String code) {
		this.code = code;
	}
}