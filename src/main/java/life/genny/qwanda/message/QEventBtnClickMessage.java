package life.genny.qwanda.message;

import java.time.LocalDateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import life.genny.qwanda.DateTimeDeserializer;

public class QEventBtnClickMessage extends QEventMessage {
	
	private String itemCode;
	private String hint;
	private String value;
	
	private static final long serialVersionUID = 1L;

	private static final String EVENT_TYPE_BTN_CLICK = "BTN_CLICK";

	public QEventBtnClickMessage(String btnCode) {
		super(EVENT_TYPE_BTN_CLICK, btnCode);
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
		return "QEventBtnClickMessage [code="+this.getData().getCode()
				+ ", event_type=" + getEvent_type() + ", msg_type=" + getMsg_type() + ", itemCode=" + itemCode + ", hint=" + hint + "]";
	}
	
	
}
