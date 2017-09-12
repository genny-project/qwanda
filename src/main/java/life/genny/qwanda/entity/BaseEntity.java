/*
 * (C) Copyright 2017 GADA Technology (http://www.outcome-hub.com/) and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     Adam Crow
 *     Byron Aguirre
 */

package life.genny.qwanda.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.logging.log4j.Logger;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import life.genny.qwanda.Answer;
import life.genny.qwanda.AnswerLink;
import life.genny.qwanda.CodedEntity;
import life.genny.qwanda.attribute.Attribute;
import life.genny.qwanda.attribute.EntityAttribute;
import life.genny.qwanda.exception.BadDataException;

/**
 * BaseEntity represents a base entity that contains many attributes. It is the
 * base parent for many Qwanda classes and serves to establish Hibernate
 * compatibility and datetime stamping. BaseEntity objects may be scored against
 * each other. BaseEntity objects may not have a deterministic code Examples of
 * derivative entities may be Person, Company, Event, Product, TradeService. This
 * attribute information includes:
 * <ul>
 * <li>The List of attributes
 * </ul>
 *
 * 
 * 
 * @author Adam Crow
 * @author Byron Aguirre
 * @version %I%, %G%
 * @since 1.0
 */

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)

@Table(name = "baseentity")
@Entity
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)

//@Inheritance(strategy = InheritanceType.JOINED)
public class BaseEntity extends CodedEntity implements BaseEntityIntf {

	/**
	 * 
	 */
    @Transient
	private static final long serialVersionUID = 1L;

	/**
	 * Stores logger object.
	 */
	protected static final Logger log = org.apache.logging.log4j.LogManager
			.getLogger(MethodHandles.lookup().lookupClass().getCanonicalName());

	private static final String DEFAULT_CODE_PREFIX = "BAS_";
	@JsonIgnore
    @XmlTransient
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.baseEntity", cascade=CascadeType.ALL)
	private Set<EntityAttribute> baseEntityAttributes = new HashSet<EntityAttribute>(0);

    @JsonIgnore
    @XmlTransient
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.source", cascade=CascadeType.ALL)
	private Set<EntityEntity> links = new HashSet<EntityEntity>(0);

	@JsonIgnore
    @XmlTransient
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.source", cascade=CascadeType.ALL)
	private Set<AnswerLink> answers = new HashSet<AnswerLink>(0);


	/**
	 * Constructor.
	 * 
	 * @param none
	 */
	@SuppressWarnings("unused")
	protected BaseEntity() {
		super();
		// dummy
	}

	/**
	 * Constructor.
	 * 
	 * @param Name
	 *            the summary name of the core entity
	 */
	public BaseEntity(final String aName) {
		super(getDefaultCodePrefix() +UUID.randomUUID().toString(),aName);

	}

	/**
	 * Constructor.
	 * 
	 * @param Code
	 *            the unique code of the core entity
	 * @param Name
	 *            the summary name of the core entity
	 */
	public BaseEntity(final String aCode,final String aName) {
		super(aCode, aName);

	}
	

	
	
	/**
	 * @return the answers
	 */
	public Set<AnswerLink> getAnswers() {
		return answers;
	}

	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(final Set<AnswerLink> answers) {
		this.answers = answers;
	}

	/**
	 * @return the baseEntityAttributes
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public Set<EntityAttribute> getBaseEntityAttributes() {
		return baseEntityAttributes;
	}

	/**
	 * @param baseEntityAttributes the baseEntityAttributes to set
	 */
	public void setBaseEntityAttributes(final Set<EntityAttribute> baseEntityAttributes) {
		this.baseEntityAttributes = baseEntityAttributes;
	}

	
	
	/**
	 * @return the links
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public Set<EntityEntity> getLinks() {
		return links;
	}

	/**
	 * @param links the links to set
	 */
	public void setLinks(final Set<EntityEntity> links) {
		this.links = links;
	}

	/**
	 * getDefaultCodePrefix This method is expected to be overridden in
	 * specialised child classes.
	 * 
	 * @return the default Code prefix for this class.
	 */

	static public String getDefaultCodePrefix() {
		return DEFAULT_CODE_PREFIX;
	}
	
	/**
	 * containsEntityAttribute This checks if an attribute exists in the baseEntity.
	 * @param attributeCode
	 * @returns boolean
	 */
	public boolean containsEntityAttribute(final String attributeCode)
	{
		boolean ret = false;
		
		// Check if this code exists in the baseEntityAttributes
		if (getBaseEntityAttributes().parallelStream().anyMatch(ti -> ti.getAttribute().getCode().equals(attributeCode))) {
		    ret = true;
		}
		return ret;
	}

	/**
	 * containsLink This checks if an attribute link code is linked to the baseEntity.
	 * @param attributeCode
	 * @returns boolean
	 */
	public boolean containsLink(final String linkAttributeCode)
	{
		boolean ret = false;
		
		// Check if this code exists in the baseEntityAttributes
		if (getLinks().parallelStream().anyMatch(ti -> ti.getLinkAttribute().getCode().equals(linkAttributeCode))) {
		    ret = true;
		}
		return ret;
	}
	
	/**
	 * containsTarget This checks if another baseEntity is linked to the baseEntity.
	 * @param targetCode
	 * @param linkAttributeCode
	 * @returns boolean
	 */
	public boolean containsTarget(final String targetCode, final String linkAttributeCode)
	{
		boolean ret = false;
		
		// Check if this code exists in the baseEntityAttributes
		if (getLinks().parallelStream().anyMatch(ti -> (ti.getLinkAttribute().getCode().equals(linkAttributeCode)&&(ti.getTarget().getCode().equals(targetCode))))) {
		    ret = true;
		}
		return ret;
	}
	
	/**
	 * findEntityAttribute This returns an attributeEntity if it exists in the baseEntity.
	 * @param attributeCode
	 * @returns Optional<EntityAttribute>
	 */
	public Optional<EntityAttribute> findEntityAttribute(final String attributeCode)
	{
		final Optional<EntityAttribute> foundEntity = Optional.of(getBaseEntityAttributes().parallelStream()
        .filter(x -> (x.getAttribute().getCode().equals(attributeCode)))
        .findFirst()
        .get());
		
	
		return foundEntity;
	}
	
	/**
	 * findEntityAttribute This returns an attributeEntity if it exists in the baseEntity.
	 * Could be more efficient in retrival (ACC: test)
	 * @param attribute
	 * @returns EntityAttribute
	 */
	public EntityAttribute findEntityAttribute(final Attribute attribute)
	{
		final EntityAttribute foundEntity = getBaseEntityAttributes().parallelStream()
        .filter(x -> (x.getAttribute().equals(attribute)))
        .findFirst()
        .get();
		
		return foundEntity;
	}

	
	/**
	 * addAttribute This adds an attribute with default weight of 0.0 to the baseEntity. It auto creates the
	 * EntityAttribute object.
	 * For efficiency we assume the attribute does not already exist
	 * @param ea
	 * @throws BadDataException
	 */
	public void addAttribute(final EntityAttribute ea) throws BadDataException
	{
		if (ea == null ) throw new BadDataException("missing Attribute");
		
		addAttribute(ea.getAttribute(),ea.getWeight(),ea.getValueString());
	}

	/**
	 * addAttribute This adds an attribute and associated weight to the baseEntity. It auto creates the
	 * EntityAttribute object.
	 * For efficiency we assume the attribute does not already exist
	 * @param attribute
	 * @param weight
	 * @throws BadDataException
	 */
	public void addAttribute(final Attribute attribute) throws BadDataException
	{
		
		addAttribute(attribute, 1.0);
	}

	/**
	 * addAttribute This adds an attribute and associated weight to the baseEntity. It auto creates the
	 * EntityAttribute object.
	 * For efficiency we assume the attribute does not already exist
	 * @param attribute
	 * @param weight
	 * @throws BadDataException
	 */
	public void addAttribute(final Attribute attribute, final Double weight) throws BadDataException
	{
		addAttribute(attribute,weight,null);
	}

	/**
	 * addAttribute This adds an attribute and associated weight to the baseEntity. It auto creates the
	 * EntityAttribute object.
	 * For efficiency we assume the attribute does not already exist
	 * @param attribute
	 * @param weight
	 * @param value (of type String, LocalDateTime, Long, Integer, Boolean
	 * @throws BadDataException
	 */
	public void addAttribute(final Attribute attribute, final Double weight, final Object value) throws BadDataException
	{
		if (attribute == null ) throw new BadDataException("missing Attribute");
		if (weight == null ) throw new BadDataException("missing weight");
		
		final EntityAttribute entityAttribute = new EntityAttribute(this, attribute, weight, value);
		getBaseEntityAttributes().add(entityAttribute);
	}
	
	/**
	 * removeAttribute This removes an attribute and associated weight from the baseEntity.
	 * For efficiency we assume the attribute exists
	 * @param attributeCode
	 * @param weight
	 */
	public void removeAttribute(final String attributeCode)
	{
		final Optional<EntityAttribute> optEntityAttribute = findEntityAttribute(attributeCode);
		getBaseEntityAttributes().remove(optEntityAttribute);
	}

	/**
	 * addTarget This links this baseEntity to a target BaseEntity and associated weight,value to the baseEntity. It auto creates the
	 * EntityEntity object and sets itself to be the source.
	 * For efficiency we assume the link does not already exist
	 * @param target Entity
	 * @param linkAttribute
	 * @param weight
	 * @throws BadDataException
	 */
	public void addTarget(final BaseEntity target, final Attribute linkAttribute, final Double weight) throws BadDataException
	{
		addTarget(target, linkAttribute, weight, null);
	}

	/**
	 * addTarget This links this baseEntity to a target BaseEntity and associated weight,value to the baseEntity. It auto creates the
	 * EntityEntity object and sets itself to be the source.
	 * For efficiency we assume the link does not already exist
	 * @param target
	 * @param linkAttribute
	 * @param weight
	 * @param value (of type String, LocalDateTime, Long, Integer, Boolean
	 * @throws BadDataException
	 */
	public void addTarget(final BaseEntity target, final Attribute linkAttribute, final Double weight, final Object value) throws BadDataException
	{
		if (target == null ) throw new BadDataException("missing Target Entity");
		if (linkAttribute == null) throw new BadDataException("missing Link Attribute");
		if (weight == null ) throw new BadDataException("missing weight");
		
		final EntityEntity entityEntity = new EntityEntity(this, target, linkAttribute,  weight, value);
		getLinks().add(entityEntity);
	}

	/**
	 * addAnswer This links this baseEntity to a target BaseEntity and associated Answer. It auto creates the
	 * AnswerLink object and sets itself to be the source and assumes itself to be the target.
	 * For efficiency we assume the link does not already exist and weight = 0
	 * @param answer
	 * @throws BadDataException
	 */
	public AnswerLink addAnswer(final Answer answer) throws BadDataException
	{
		return addAnswer(this,answer,0.0);
	}

	/**
	 * addAnswer This links this baseEntity to a target BaseEntity and associated Answer. It auto creates the
	 * AnswerLink object and sets itself to be the source and assumes itself to be the target.
	 * For efficiency we assume the link does not already exist
	 * @param answer
	 * @param weight
	 * @throws BadDataException
	 */
	public AnswerLink addAnswer(final Answer answer, final Double weight) throws BadDataException
	{
		return addAnswer(this,answer,weight);
	}
	
	/**
	 * addAnswer This links this baseEntity to a target BaseEntity and associated Answer. It auto creates the
	 * AnswerLink object and sets itself to be the source.
	 * For efficiency we assume the link does not already exist
	 * @param target
	 * @param answer
	 * @param weight
	 * @throws BadDataException
	 */
	public AnswerLink addAnswer(final BaseEntity target, final Answer answer, final Double weight) throws BadDataException
	{
		if (target == null ) throw new BadDataException("missing Target Entity");
		if (answer == null) throw new BadDataException("missing Answer");
		if (weight == null ) throw new BadDataException("missing weight");
		
		final AnswerLink answerLink =new AnswerLink(this, target, answer,  weight);
	//	Set<AnswerLink> answerLinkSet = new HashSet<AnswerLink>();
	//	answerLinkSet.addAll(answer.getAsk().getAnswerList().getAnswerList());
		getAnswers().add(answerLink);
		return answerLink;
		// update attributes!
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getClass().getSimpleName()+":"+super.toString()+" [attributeList=" + baseEntityAttributes + "]" +" [links=" + links + "]";
	}

	public List<EntityAttribute> merge(final BaseEntity entity) {
		final List<EntityAttribute> changes = new ArrayList<EntityAttribute>();
		
		// go through the attributes in the entity and check if already existing , if so then check the value and override, else add new attribute
		
		for (final EntityAttribute ea : entity.getBaseEntityAttributes()) {
			final Attribute attribute = ea.getAttribute();
			if (this.containsEntityAttribute(attribute.getCode())) {
				// check for update value
				final String oldValue = this.getValue(attribute);
				final String newValue = this.getValue(ea);
				if (newValue != null) {
					if (!newValue.equals(oldValue)) {
						// override the old value  // TODO allow versioning
						
					}
				}
			} else {
				// add this new entityAttribute
				try {
					addAttribute(ea);
					changes.add(ea);
				} catch (final BadDataException e) {
					// TODO - log error and continue
				}
			}
		}
		
		return changes;
	}

	@JsonIgnore
	@Transient
	@XmlTransient
	private <T> T getValue(final Attribute attribute) {
		// TODO Dumb find for attribute. needs a hashMap
	
		for (final EntityAttribute ea  : this.getBaseEntityAttributes()) {
			if (ea.getAttribute().getCode().equalsIgnoreCase(attribute.getCode())) {
				return getValue(ea);
			}
		}
		return null;
	}

	@JsonIgnore
	@Transient
	@XmlTransient
	private <T> T getValue(final EntityAttribute ea) {
			final String dataType = ea.getAttribute().getDataType().getClassName();
			switch (dataType) {
			case "Integer": return (T)ea.getValueInteger();
			case "LocalDateTime": return (T)ea.getValueDateTime();
			case "Long": return (T)ea.getValueLong();
			case "Double": return (T)ea.getValueDouble();

			case "String":
				default:
					return (T) ea.getValueString();
			}

	}
	
	@JsonIgnore
	@Transient
	@XmlTransient
	private <T> void setValue(final Attribute attribute, final Object value, final Class<T> type) {
		// TODO Dumb find for attribute. needs a hashMap
	
		for (final EntityAttribute ea  : this.getBaseEntityAttributes()) {
			if (ea.getAttribute().getCode().equalsIgnoreCase(attribute.getCode())) {
				setValue(ea, value, type);
			}
		}

	}

	
	
	@JsonIgnore
	@Transient
	@XmlTransient
	private <T> void setValue(final EntityAttribute ea, final Object value, final Class<T> type) {
			switch (type.getSimpleName()) {
			case "Integer": ea.setValueInteger((Integer)value); break;
			case "LocalDateTime": ea.setValueDateTime((LocalDateTime)value); break;
			case "Long": ea.setValueLong((Long)value); break;
			case "Double": ea.setValueDouble((Double)value); break;

			case "String":
				default:
					ea.setValueString((String)value); break;
			}

	}

}