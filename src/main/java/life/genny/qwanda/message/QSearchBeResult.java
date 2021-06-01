package life.genny.qwanda.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QSearchBeResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String[] codes;
	Long total;
	
	
	
	/**
	 * @param codes
	 * @param total
	 */
	public QSearchBeResult(List<String> codes, Long total) {
		this.codes = codes.toArray(new String[0]);
		this.total = total;
	}

	/**
	 * @param total
	 */
	public QSearchBeResult(Long total) {
		this.total = total;
	}
	/**
	 * @return the codes
	 */
	public List<String> getCodes() {
		return Arrays.asList(codes);
	}
	/**
	 * @param codes the codes to set
	 */
	public void setCodes(List<String> codes) {
		this.codes = codes.toArray(new String[0]);
	}
	/**
	 * @return the total
	 */
	public Long getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(Long total) {
		this.total = total;
	}
	
	
}
