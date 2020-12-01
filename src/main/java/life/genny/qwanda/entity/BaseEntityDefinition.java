package life.genny.qwanda.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.google.gson.annotations.Expose;

import life.genny.qwanda.attribute.Attribute;
import life.genny.qwanda.attribute.AttributeBoolean;
import life.genny.qwanda.attribute.AttributeDate;
import life.genny.qwanda.attribute.AttributeDateTime;
import life.genny.qwanda.attribute.AttributeDouble;
import life.genny.qwanda.attribute.AttributeInteger;
import life.genny.qwanda.attribute.AttributeLong;
import life.genny.qwanda.attribute.AttributeText;
import life.genny.qwanda.attribute.EntityAttribute;
import life.genny.qwanda.exception.BadDataException;

import life.genny.qwandautils.QwandaJsonUtils;


/* BaseEntityDefinition class implements the search of base entities applying different filters/search to the
 * baseEntity and its attributes
 */
public class BaseEntityDefinition extends BaseEntity {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public enum ELookupType {
		STATIC_FIXED,    // Never changes, supply values directly e.g. TRUE, FALSE,    1,2,3,4
		STATIC_SEARCH,   // return all from a dynamic search and send them all e.g. host company rep
		SEARCH,           //  return pagination list from search using initial search chars
		GROUP,           // return list from baseentitys link to this group code
	}

	/* Constructor to create BaseEntityDefinition with code and name */
	public BaseEntityDefinition() {
		
	}

	/* Constructor to create BaseEntityDefinition with code and name */
	public BaseEntityDefinition(final String code, final String baseEntityName, final String prefix) {
		super(code, baseEntityName);
		AttributeText prefixAttribute = new AttributeText("PRI_PREFIX", "Prefix"); 
		AttributeText mandateAttribute = new AttributeText("PRI_MANDATED", "Mandated"); 

		EntityAttribute ea = new EntityAttribute();
		ea.setAttribute(prefixAttribute);
		ea.setValue(prefix);
		ea.setReadonly(true);
		
		this.getBaseEntityAttributes().add(ea);
	
		EntityAttribute eb = new EntityAttribute();
		eb.setAttribute(mandateAttribute);
		
		this.getBaseEntityAttributes().add(eb);

	}


	/*
	 * This method allows to add the attributes to the BaseEntityDefinition that is required
	 * in the result BaseEntities
	 */
	public BaseEntityDefinition addDependency(final Attribute parentAttribute, final Attribute... childrenAttributes) {
		// Check if parent Attribute already in list
		Optional<EntityAttribute> optParent = this.findEntityAttribute("DEP_"+parentAttribute.getCode());
		EntityAttribute parent = null;
		if (optParent.isPresent()) {
			parent = optParent.get();
		} else {
			parent = new EntityAttribute();
			AttributeText depAttribute = new AttributeText("DEP_" + parentAttribute.getCode(), ""); // no parental dependency for the parent initially
			parent.setAttribute(depAttribute);
			this.getBaseEntityAttributes().add(parent);
		}
		
		// add children codes in value and the parent in their name
		String childCodes = "";
		for (Attribute kid : childrenAttributes) {
			Optional<EntityAttribute> optEaKid = this.findEntityAttribute("DEP_"+kid.getCode());
			EntityAttribute eaKid = null;
			if (optEaKid.isPresent()) {
				eaKid = optEaKid.get();
			} else {
				eaKid = new EntityAttribute();
				eaKid.setAttribute(kid);
				eaKid.setAttributeName(parentAttribute.getCode());
				this.getBaseEntityAttributes().add(eaKid);
			}
			childCodes += kid.getCode()+":";
		}	
		parent.setValue(childCodes);
		return this;
	}
	
	/*
	 * This method allows to add the attributes to the BaseEntityDefinition that is required
	 * in the result BaseEntities
	 */
	public BaseEntityDefinition addLookup(final String attributeCode, final String searchEntityCode, final ELookupType lookupType ) {
		// Check if parent Attribute already in list
		Optional<EntityAttribute> optParent = this.findEntityAttribute("LOO_"+attributeCode);
		EntityAttribute parent = null;
		if (optParent.isPresent()) {
			parent = optParent.get();
			parent.setAttributeName(lookupType.toString());
		} else {
			parent = new EntityAttribute();
			AttributeText depAttribute = new AttributeText("LOO_" + attributeCode, lookupType.toString()); // no parental dependency for the parent initially
			parent.setAttribute(depAttribute);
			this.getBaseEntityAttributes().add(parent);
		}
		
		parent.setValue(searchEntityCode);
		return this;
	}

	/*
	 * This method allows to add the mandated attributes to the BaseEntityDefinition that is required
	 * in the result BaseEntities
	 */
	public BaseEntityDefinition mandateAttribute(final String attributeCode ) {
		// Check if parent Attribute already in list
		Optional<EntityAttribute> optParent = this.findEntityAttribute("PRI_MANDATED");
		EntityAttribute parent = null;
		if (optParent.isPresent()) {
			parent = optParent.get();
			String mandates = parent.getValueString();
			mandates += ","+attributeCode;
			parent.setValue(mandates);
		} else {
			AttributeText mandateAttribute = new AttributeText("PRI_MANDATED", "Mandated"); 

			EntityAttribute eb = new EntityAttribute();
			eb.setAttribute(mandateAttribute);		
			eb.setValue(attributeCode);
			this.getBaseEntityAttributes().add(eb);

		}
		
		return this;
	}
		/*
	 * This method allows to add the action attributes to the BaseEntityDefinition that is required
	 * in the result BaseEntities
	 */
	public BaseEntityDefinition setPrefix(final String prefix) {
		AttributeText attributeColumn = new AttributeText("PRE_PREFIX", "Prefix");
		try {
			EntityAttribute ea = addAttribute(attributeColumn);
			ea.setValue(prefix);
		} catch (BadDataException e) {
			log.error("Bad Prefix Initialisation");
		}
		return this;

	}


	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return QwandaJsonUtils.toJson(this);
	}


}
