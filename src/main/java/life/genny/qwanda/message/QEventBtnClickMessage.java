package life.genny.qwanda.message;

import java.time.LocalDateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import life.genny.qwanda.DateTimeDeserializer;

public class QEventBtnClickMessage extends QEventMessage {
	
	private String itemCode;
	private String hint;
	
	private static final long serialVersionUID = 1L;

	private static final String EVENT_TYPE_BTN_CLICK = "BTN_CLICK";

	public QEventBtnClickMessage(String btnCode) {
		super(EVENT_TYPE_BTN_CLICK, btnCode);
	}
	
	public String getItemCode() {
		String value= this.getData().getValue();
		
		GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new DateTimeDeserializer());
        Gson gson = gsonBuilder.create();
		
		JsonObject valueJson = gson.fromJson(value, JsonObject.class);
		String itemCode= valueJson.get("itemCode").toString().replaceAll("^\"|\"$", "");
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
		String hint= valueJson.get("hint").toString();
		return hint;
	}
	
	public void setHint(String hint) {
		this.hint = hint;
	}

	@Override
	public String toString() {
		return "QEventBtnClickMessage [code="+this.getData().getCode()
				+ ", event_type=" + getEvent_type() + ", msg_type=" + getMsg_type() + ", itemCode=" + itemCode + ", hint=" + hint + "]";
	}
	

	
	
}
