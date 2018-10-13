package life.genny.qwanda.message;


import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.Logger;

import com.google.gson.annotations.Expose;

import life.genny.qwanda.attribute.AttributeText;
import life.genny.qwanda.entity.BaseEntity;
import life.genny.qwanda.entity.SearchEntity;
import life.genny.qwanda.entity.SearchEntity.Sort;
import life.genny.qwanda.exception.BadDataException;

public class QSearchEntityMessage extends QDataBaseEntityMessage  {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Stores logger object.
	 */
	protected static final Logger log = org.apache.logging.log4j.LogManager
			.getLogger(MethodHandles.lookup().lookupClass().getCanonicalName());

	@Expose
	private SearchEntity parent ;
	
	private static final String DATATYPE_SEARCHENTITY =  QSearchEntityMessage.class.getSimpleName();

	Double colIndex = 1.0;
	Double sortIndex = 1.0;
	


	
	private QSearchEntityMessage(final SearchEntity searchSettings, final SearchEntity[] searches) {
		super(searches);
		this.setData_type(DATATYPE_SEARCHENTITY);
		this.parent = searchSettings;

	}

	/*
	 * This method allows to set the filter for the integer value in the search 
	 * @param attributeCode - the attributeCode which holds integer value where we apply the filter
	 * @param filterType - type of the filter
	 * @param value - filter against (search for)  this value
	 */
	public QSearchEntityMessage addSearchOr(SearchEntity searchEntity)
	{
		List<BaseEntity> searchItems = Arrays.asList(this.getItems());
		searchItems.add(searchEntity);
		this.setItems(searchItems.toArray(new BaseEntity[0]));
		return this;
	}

	/**
	 * @return the parent
	 */
	public SearchEntity getParent() {
		return parent;
	}



	/**
	 * @param parent the parent to set
	 */
	public void setParent(SearchEntity parent) {
		this.parent = parent;
	}

	/*
	 * This method allows to add the attributes to the SearchEntity that is required in the result 
	 * BaseEntities
	 */
	public QSearchEntityMessage addColumn(final String attributeCode, final String columnName)
	{
		AttributeText attributeColumn = new AttributeText("COL_"+attributeCode, columnName);
		try {
			parent.addAttribute(attributeColumn, colIndex);
			colIndex += 1.0;
		} catch (BadDataException e) {
			log.error("Bad Column Initialisation");
		}
		return this;
	}
	
	/*
	 * This method allows to add sorting to the attributes of the search results
	 * It can either sort in ascending or descending order
	 */
	public QSearchEntityMessage addSort(final String attributeCode, final String sortHelpText, final Sort sortType)
	{
		AttributeText attributeSort = new AttributeText("SRT_"+attributeCode, sortHelpText);
		try {
			parent.addAttribute(attributeSort, sortIndex, sortType.toString());
			sortIndex += 1.0;
			
		} catch (BadDataException e) {
			log.error("Bad Sort Initialisation");
		}
		
		return this;
	}

    public static class Builder {

        private String code; //This is important, so we'll pass it to the constructor.

        private String name;
        
        private SearchEntity searchEntity = new SearchEntity("SBE_"+UUID.randomUUID().toString().substring(0, 20),"New Search");

        private List<SearchEntity> searchEntityList = new ArrayList<SearchEntity>();

 
        public Builder(final String code, final String name) {

            this.searchEntity = new SearchEntity(code,name);
            

        }

        public Builder pageSize(int size){
        	if (size > 0) {
            this.searchEntity.setPageSize(size);
        	} else {
        		log.error("Size must be > 0");
        	}

            return this;  //By returning the builder each time, we can create a fluent interface.

        }

        public Builder pageStart(int index){
        	if (index > 0) {
        		 this.searchEntity.setPageStart(index);
        	} else {
        		log.error("Index must be > 0");
        	}
            return this;  //By returning the builder each time, we can create a fluent interface.
        }

    	/*
    	 * This method allows to add the attributes to the SearchEntity that is required in the result 
    	 * BaseEntities
    	 */

        public Builder column(final String attributeCode, final String columnName) {
        	this.searchEntity.addColumn(attributeCode, columnName);      	
            return this;  //By returning the builder each time, we can create a fluent interface.
        }
        
    	/*
    	 * This method allows to add sorting to the attributes of the search results
    	 * It can either sort in ascending or descending order
    	 */
    	public Builder addSort(final String attributeCode, final String sortHelpText, final SearchEntity.Sort sortType)
    	{
        	this.searchEntity.addSort(attributeCode, sortHelpText,sortType);      	
            return this;  //By returning the builder each time, we can create a fluent interface.
   		
    	}
   
       	/*
    	 * This method allows to add searches that are ORd
     	 */
    	public Builder searchOr(final SearchEntity searchEntity)
    	{
        	this.searchEntityList.add(searchEntity);      	
            return this;  //By returning the builder each time, we can create a fluent interface.
   		
    	}   	
    	
        public QSearchEntityMessage build(){

            //Here we create the actual searchEntityMessage object, which is always in a fully initialised state when it's returned.
        	SearchEntity[] searchEntitys = searchEntityList.toArray(new SearchEntity[searchEntityList.size()]);
        	
            QSearchEntityMessage searchEntityMessage = new QSearchEntityMessage(searchEntity,searchEntitys);  //Since the builder is in the QSearch class, we can invoke its private constructor.

 
            return searchEntityMessage;

        }

    }
}
