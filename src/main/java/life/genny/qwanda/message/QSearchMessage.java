package life.genny.qwanda.message;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

import life.genny.qwanda.entity.SearchEntity;

public class QSearchMessage extends QMessage implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE_TYPE = "SCH_MSG";
	@Expose
	private SearchEntity searchEntity;
	@Expose
	private String destination;
	@Expose
	private Boolean replace;


	public QSearchMessage() {
		super(MESSAGE_TYPE);
	}

	public QSearchMessage(SearchEntity searchEntity) {
		super(MESSAGE_TYPE);
		this.searchEntity = searchEntity;
	}

	@Override
	public String toString() {
		return "QSearchMessage [searchEntity=" + searchEntity.getCode() + "]";
	}

	public void setSearchEntity(SearchEntity searchEntity) {
		this.searchEntity = searchEntity;
	}

	public SearchEntity getSearchEntity() {
		return this.searchEntity;
	}
	
	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDestination() {
		return this.destination;
	}

	public void setRelace(Boolean replace) {
		this.replace = replace;
	}

	public Boolean getReplace() {
		return this.replace;
	}
}
