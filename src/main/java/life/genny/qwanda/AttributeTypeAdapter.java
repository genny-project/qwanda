package life.genny.qwanda;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import life.genny.qwanda.attribute.Attribute;

class AttributeAdapter extends TypeAdapter<Attribute> { 
	   @Override 
	   public Attribute read(JsonReader reader) throws IOException { 
		   Attribute attribute = null;
           String type = null;
           reader.beginObject();
            reader.endObject();
           return attribute;
	   } 
	   
	   @Override 
	   public void write(JsonWriter writer, Attribute attribute) throws IOException { 
		   writer.beginObject(); 
		      writer.name("attributeCode"); 
		      writer.value(attribute.getCode()); 
		      writer.endObject(); 
	   } 
	}
