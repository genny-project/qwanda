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
	}

	/* Constructor to create BaseEntityDefinition with code and name */
	public BaseEntityDefinition() {
		
	}

	/* Constructor to create BaseEntityDefinition with code and name */
	public BaseEntityDefinition(final String code, final String baseEntityName, final String prefix) {
		super(code, baseEntityName);
		AttributeText prefixAttribute = new AttributeText("PRI_PREFIX", "Prefix"); 

		EntityAttribute ea = new EntityAttribute();
		ea.setAttribute(prefixAttribute);
		ea.setReadonly(true);
		ea.setValue(prefix);
		this.getBaseEntityAttributes().add(ea);
	
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
	public BaseEntityDefinition addLookup(final Attribute attribute, final String searchEntityCode, final ELookupType lookupType ) {
		// Check if parent Attribute already in list
		Optional<EntityAttribute> optParent = this.findEntityAttribute("LOO_"+attribute.getCode());
		EntityAttribute parent = null;
		if (optParent.isPresent()) {
			parent = optParent.get();
			parent.setAttributeName(lookupType.toString());
		} else {
			parent = new EntityAttribute();
			AttributeText depAttribute = new AttributeText("LOO_" + attribute.getCode(), lookupType.toString()); // no parental dependency for the parent initially
			parent.setAttribute(depAttribute);
			this.getBaseEntityAttributes().add(parent);
		}
		
		parent.setValue(searchEntityCode);
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
