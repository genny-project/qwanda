package life.genny.qwanda;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

public class GennyItem {
	List<AttributeCodeValueString> b2bdata = new ArrayList<>();
	
	public GennyItem()
	{
		
	}
	
	public GennyItem(List<AttributeCodeValueString> b2bdata ) {
		this.b2bdata = b2bdata;
	}


	public void addB2B(AttributeCodeValueString item)
	{
		Optional<AttributeCodeValueString> existing = get(item.attributeCode);
		if (existing.isPresent()) {
			existing.get().value = item.value;
		} else {
			b2bdata.add(item);
		}
	}
	
	public void removeB2B(final String attributeCode)
	{
		if (StringUtils.isBlank(attributeCode)) {
			return;
		}
		final String cleanAttributeCode = attributeCode.trim().toUpperCase();
		Optional<AttributeCodeValueString> existing = get(cleanAttributeCode);
		if (existing.isPresent()) {
			b2bdata.remove(existing.get());
		} 
	
		
	}
	

	/**
	 * @return the b2bdata
	 */
	public List<AttributeCodeValueString> getB2bdata() {
		return b2bdata;
	}




	/**
	 * @param b2bdata the b2bdata to set
	 */
	public void setB2bdata(List<AttributeCodeValueString> b2bdata) {
		this.b2bdata = b2bdata;
	}

	public Optional<AttributeCodeValueString> get(final String attributeCode)
	{
		if (StringUtils.isBlank(attributeCode)) {
			return null;
		}
		final String cleanAttributeCode = attributeCode.trim().toUpperCase();
		// TODo optimise
		Optional<AttributeCodeValueString> matchingObject = b2bdata.stream().
			    filter(p -> p.getAttributeCode().equals(cleanAttributeCode)).
			    findFirst();
		return matchingObject; 
		
	}

}
