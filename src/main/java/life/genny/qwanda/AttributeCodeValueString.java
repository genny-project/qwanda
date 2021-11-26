package life.genny.qwanda;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

public class AttributeCodeValueString {
	@JsonProperty
	@Expose
	public String attributeCode;
	
	@JsonProperty
	@Expose
	public String value;
	
	public AttributeCodeValueString()
	{
		
	}
	
	public AttributeCodeValueString(final String attributeCode, final String value)
	{
		this.attributeCode = attributeCode;
		this.value = value;
	}

	/**
	 * @return the attributeCode
	 */
	public String getAttributeCode() {
		return attributeCode;
	}

	/**
	 * @param attributeCode the attributeCode to set
	 */
	public void setAttributeCode(String attributeCode) {
		this.attributeCode = attributeCode;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}
