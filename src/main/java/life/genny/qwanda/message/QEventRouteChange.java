package life.genny.qwanda.message;

import java.time.LocalDateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import life.genny.qwanda.DateTimeDeserializer;

public class QEventRouteChange extends QEventMessage {
	
	private String itemCode;
	private String hint;
	private String value;
	private String message;
	
	private static final long serialVersionUID = 1L;

	private static final String EVENT_TYPE_ROUTE_CHANGE = "ROUTE_CHANGE";

	public QEventRouteChange(String route) {
		super(EVENT_TYPE_ROUTE_CHANGE, route);
	}
	
	public String getItemCode() {
		
		String value= null;
		if (this.getData()==null) {
			return null;
		}
		value = this.getData().getValue();
		
		GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new DateTimeDeserializer());
        Gson gson = gsonBuilder.create();
		
		JsonObject valueJson = null;
		
		String itemCode = null;
		try {
			valueJson = gson.fromJson(value, JsonObject.class);
			itemCode = valueJson.get("itemCode").toString().replaceAll("^\"|\"$", "");
		} catch (JsonSyntaxException e) {
			return value;
		}
		return itemCode;
	}
	
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getMessage() {
		
		String value= null;
		if (this.getData()==null) {
			return null;
		}
		value = this.getData().getValue();
		
		GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new DateTimeDeserializer());
        Gson gson = gsonBuilder.create();
		
		JsonObject valueJson = null;
		
		String message = null;
		try {
			valueJson = gson.fromJson(value, JsonObject.class);
			message = valueJson.get("message").toString().replaceAll("^\"|\"$", "");
		} catch (JsonSyntaxException e) {
			return value;
		}
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getHint() {
		String value= this.getData().getValue();
		
		GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new DateTimeDeserializer());
        Gson gson = gsonBuilder.create();
		
		JsonObject valueJson = gson.fromJson(value, JsonObject.class);
		String hint= valueJson.get("hint").toString().replaceAll("^\"|\"$", "");
		return hint;
	}
	
	public void setHint(String hint) {
		this.hint = hint;
	}
	
	public String getValue() {
		String value= this.getData().getValue();
		
		GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new DateTimeDeserializer());
        Gson gson = gsonBuilder.create();
		
		JsonObject valueJson = gson.fromJson(value, JsonObject.class);
		String childValue= valueJson.get("value").toString().replaceAll("^\"|\"$", "");
		return childValue;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "QEventRouteChange [itemCode=" + itemCode + ", hint=" + hint + ", value=" + value + ", message="
				+ message + "]";
	}
	
	
}
