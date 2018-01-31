package life.genny.qwanda.message;

import java.util.Properties;

public class QEventSystemMessage extends QEventMessage {
	  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String EVENT_TYPE_SYSTEM = "EVT_SYSTEM";
	
	private static final String DEPLOY_TYPE = System.getenv("DEPLOY_TYPE") != null ? System.getenv("DEPLOY_TYPE") : null;

	public QEventSystemMessage(final String systemCode) {
		this(systemCode,new Properties(),null);
	}	
	public QEventSystemMessage(final String systemCode, final Properties properties) {
		this(systemCode,properties,null);
	}	
	
	public QEventSystemMessage(final String systemCode, final Properties properties, String token) {
		super(EVENT_TYPE_SYSTEM, systemCode);
		setToken(token);
	}
	
	@Override
	public String toString() {
		return "QEventSystemMessage [data=" + data + ", toString()=" + super.toString() + ", getEvent_type()="
				+ getEvent_type() + ", getData()=" + getData() + ", getMsg_type()=" + getMsg_type() + ", getToken()="
				+ getToken() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
	public static String getDeployType() {
		return DEPLOY_TYPE;
	}
	

	
	
}
