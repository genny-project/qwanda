package life.genny.qwanda.message;

import java.util.Arrays;

import com.google.gson.annotations.Expose;

public class QCmdViewTableMessage extends QCmdViewMessage {
	
	private static final String CODE = "TABLE_VIEW";
	
	public QCmdViewTableMessage(Object root) {
		super(CODE, root);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	
	@Expose
	private QCmdTableMessage[] columns;
	
	@Expose
	private String contextRoot;

	/**
	 * @return the columns
	 */
	public QCmdTableMessage[] getColumns() {
		return columns;
	}

	/**
	 * @param columns the columns to set
	 */
	public void setColumns(QCmdTableMessage[] columns) {
		this.columns = columns;
	}

	/**
	 * @return the contextRoot
	 */
	public String getContextRoot() {
		return contextRoot;
	}

	/**
	 * @param contextRoot the contextRoot to set
	 */
	public void setContextRoot(String contextRoot) {
		this.contextRoot = contextRoot;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QCmdViewTableMessage [columns=" + Arrays.toString(columns) + ", contextRoot=" + contextRoot + "]";
	}


}
