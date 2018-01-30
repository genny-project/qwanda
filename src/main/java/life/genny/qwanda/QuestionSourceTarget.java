package life.genny.qwanda;

import java.io.Serializable;

public class QuestionSourceTarget implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String questionCode;
	private String sourceCode;
	private String targetCode;
	
	public QuestionSourceTarget(String questionCode, String sourceCode, String targetCode) {
		super();
		this.questionCode = questionCode;
		this.sourceCode = sourceCode;
		this.targetCode = targetCode;
	}

	/**
	 * @return the questionCode
	 */
	public String getQuestionCode() {
		return questionCode;
	}

	/**
	 * @param questionCode the questionCode to set
	 */
	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}

	/**
	 * @return the sourceCode
	 */
	public String getSourceCode() {
		return sourceCode;
	}

	/**
	 * @param sourceCode the sourceCode to set
	 */
	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
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
	
	
}
