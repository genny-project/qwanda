package life.genny.qwanda.entity;

import life.genny.qwanda.attribute.AttributeBoolean;
import life.genny.qwanda.attribute.AttributeDouble;
import life.genny.qwanda.attribute.AttributeInteger;
import life.genny.qwanda.attribute.AttributeLong;
import life.genny.qwanda.attribute.AttributeText;
import life.genny.qwanda.attribute.EntityAttribute;
import life.genny.qwanda.exception.BadDataException;

public class SearchEntity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Double colIndex = 1.0;
	Double sortIndex = 1.0;
	
	public enum Sort {
	    ASC {
	        public String toString() {
	            return "ASC";
	        }
	    },
	    DESC {
	        public String toString() {
	            return "DESC";
	        }
	    }
	}

	public enum StringFilter {
	    EQUAL {
	        public String toString() {
	            return "=";
	        }
	    },
	    LIKE {
	        public String toString() {
	            return "LIKE";
	        }
	    }
	}
	public enum Filter {
	    EQUALS {
	        public String toString() {
	            return "=";
	        }
	    },
	    GREATER_THAN {
	        public String toString() {
	            return ">";
	        }
	    },
	    GREATER_THAN_AND_EQUAL {
	        public String toString() {
	            return ">=";
	        }
	    },
	    LESS_THAN_AND_EQUAL {
	        public String toString() {
	            return "<=";
	        }
	    },
	    LESS_THAN {
	        public String toString() {
	            return "<";
	        }
	    },
	}

	public SearchEntity(final String code, final String name) {
		super(code,name);
		setPageStart(0);
		setPageSize(20);
	}
	
	public SearchEntity(final BaseEntity be) {
		super(be.getCode(), be.getName());
		this.setCreated(be.getCreated());
		this.setUpdated(be.getUpdated());
		this.setBaseEntityAttributes(be.getBaseEntityAttributes());
	}
	
	public SearchEntity addColumn(final String attributeCode, final String columnName)
	{
		AttributeText attributeColumn = new AttributeText("COL_"+attributeCode, columnName);
		try {
			addAttribute(attributeColumn, colIndex);
			colIndex += 1.0;
		} catch (BadDataException e) {
			log.error("Bad Column Initialisation");
		}
		return this;
	}
	
	public SearchEntity addSort(final String attributeCode, final String sortHelpText, final Sort sortType)
	{
		AttributeText attributeSort = new AttributeText("SRT_"+attributeCode, sortHelpText);
		try {
			addAttribute(attributeSort, sortIndex, sortType.toString());
			sortIndex += 1.0;
			
		} catch (BadDataException e) {
			log.error("Bad Sort Initialisation");
		}
		
		return this;
	}
	
	public SearchEntity addFilter(final String attributeCode, final Filter filterType, final Integer value)
	{
		AttributeInteger attribute = new AttributeInteger(attributeCode, filterType.toString());
		try {
			addAttribute(attribute, 1.0, value);
		} catch (BadDataException e) {
			log.error("Bad Integer Filter Initialisation");
		}
		
		return this;
	}

	public SearchEntity addFilter(final String attributeCode, final Filter filterType, final Long value)
	{
		AttributeLong attribute = new AttributeLong(attributeCode, filterType.toString());
		try {
			addAttribute(attribute, 1.0, value);
		} catch (BadDataException e) {
			log.error("Bad Long Filter Initialisation");
		}
		
		return this;
	}
	
	public SearchEntity addFilter(final String attributeCode, final Filter filterType, final Double value)
	{
		AttributeDouble attribute = new AttributeDouble(attributeCode, filterType.toString());
		try {
			addAttribute(attribute, 1.0, value);
		} catch (BadDataException e) {
			log.error("Bad Double Filter Initialisation");
		}
		
		return this;
	}	
	
	public SearchEntity addFilter(final String attributeCode, final Boolean value)
	{
		AttributeBoolean attribute = new AttributeBoolean(attributeCode, "=");
		try {
			addAttribute(attribute, 1.0, value);
		} catch (BadDataException e) {
			log.error("Bad Double Filter Initialisation");
		}
		
		return this;
	}	

	public SearchEntity addFilter(final String attributeCode, final StringFilter filterType,final String value)
	{
		AttributeText attribute = new AttributeText(attributeCode, filterType.toString());
		try {
			addAttribute(attribute, 1.0, value);
		} catch (BadDataException e) {
			log.error("Bad String Filter Initialisation");
		}
		
		return this;
	}	
	
	
	public SearchEntity setPageStart(final Integer pageStart)
	{
		AttributeInteger attributePageStart = new AttributeInteger("SCH_PAGE_START", "PageStart");
		try {
			addAttribute(attributePageStart, 3.0, pageStart);
		} catch (BadDataException e) {
			log.error("Bad Page Start ");
		}
		
		return this;
	}
	
	public SearchEntity setPageSize(final Integer pageSize)
	{
		AttributeInteger attributePageSize = new AttributeInteger("SCH_PAGE_SIZE", "PageSize");
		try {
			addAttribute(attributePageSize, 3.0, pageSize);
		} catch (BadDataException e) {
			log.error("Bad Page Size");
		}
		
		return this;
	}

	public SearchEntity setStakeholder(final String stakeholderCode)
	{
		AttributeText attribute = new AttributeText("SCH_STAKEHOLDER_CODE", "Stakeholder");
		try {
			addAttribute(attribute, 1.0, stakeholderCode);
		} catch (BadDataException e) {
			log.error("Bad Stakeholder");
		}
		
		return this;
	}
	
	/*
	 * Setting the stakeholder code of the Source Baseentity
	 */
	public SearchEntity setSourceStakeholder(final String sourceStakeholderCode)
	{
		AttributeText attribute = new AttributeText("SCH_SOURCE_STAKEHOLDER_CODE", "SourceStakeholder");
		try {
			addAttribute(attribute, 1.0, sourceStakeholderCode);
		} catch (BadDataException e) {
			log.error("Bad Source Stakeholder");
		}
		
		return this;
	}
	
	public SearchEntity setLinkCode(final String linkCode)
	{
		AttributeText attribute = new AttributeText("SCH_LINK_CODE", "LinkCode");
		try {
			addAttribute(attribute, 1.0, linkCode);
		} catch (BadDataException e) {
			log.error("Bad Stakeholder");
		}
		
		return this;
	}
	
	public SearchEntity setLinkValue(final String linkValue)
	{
		AttributeText attribute = new AttributeText("SCH_LINK_VALUE", "LinkValue");
		try {
			addAttribute(attribute, 1.0, linkValue);
		} catch (BadDataException e) {
			log.error("Bad Link Value");
		}
		
		return this;
	}
	
	public SearchEntity setSourceCode(final String sourceCode)
	{
		AttributeText attribute = new AttributeText("SCH_SOURCE_CODE", "SourceCode");
		try {
			addAttribute(attribute, 1.0, sourceCode);
		} catch (BadDataException e) {
			log.error("Bad SourceCode");
		}
		return this;
	}
	
	public SearchEntity setTargetCode(final String targetCode)
	{
		AttributeText attribute = new AttributeText("SCH_TARGET_CODE", "TargetCode");
		try {
			addAttribute(attribute, 1.0, targetCode);
		} catch (BadDataException e) {
			log.error("Bad Target Code");
		}
		
		return this;
	}
	
	
}
