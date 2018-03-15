package life.genny.qwanda.message;

import com.google.gson.annotations.Expose;

public class QDataAssemblyCallback extends QDataMessage{

	private static final long serialVersionUID = 1L;
	
	private static final String DATATYPE_ASSEMBLY_CALLBACK = "DATATYPE_ASSEMBLY_CALLBACK";
	
	
	public QDataAssemblyCallback(String data_type, String objectId, ObjectType object_type, ObjectStatus status) {
		super(DATATYPE_ASSEMBLY_CALLBACK);
		this.objectId = objectId;
		this.object_status = status;
		this.object_type = object_type;
	}
	
	@Expose
	private ObjectType object_type;
	
	@Expose
	private ObjectStatus object_status;
	
	@Expose
	private String objectId;
	

	public enum ObjectType {
		ITEM,
		DISBURSEMENT
	}
	
	public enum ObjectStatus {
		
		/* Item related callbacks */
		ITEM_PAYMENT_CREATION,
		ITEM_PAYMENT_MADE,
		ITEM_PAYMENT_RELEASED,
		ITEM_PAYMENT_FAILURE,
		ITEM_PAYMENT_SUCCESS,
		
		/* Disbursement related callbacks */
		DISBURSEMENT_PAYMENT_SUCCESS;
	}

	public ObjectType getObject_type() {
		return object_type;
	}

	public void setObject_type(ObjectType object_type) {
		this.object_type = object_type;
	}

	public ObjectStatus getObject_status() {
		return object_status;
	}

	public void setObject_status(ObjectStatus object_status) {
		this.object_status = object_status;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	@Override
	public String toString() {
		return "QDataAssemblyCallback [object_type=" + object_type + ", object_status=" + object_status + ", objectId="
				+ objectId + "]";
	}



	
	
}
