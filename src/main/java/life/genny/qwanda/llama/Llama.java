package life.genny.qwanda.llama;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import life.genny.qwanda.entity.BaseEntity;
import life.genny.qwanda.llama.Frame.Builder;
import life.genny.qwanda.message.QDataBaseEntityMessage;
import io.vavr.Tuple;
import io.vavr.Tuple1;
import io.vavr.Tuple2;
import io.vavr.Tuple3;



/* Llama class implements the frame of base entities 
 */
public class Llama extends QDataBaseEntityMessage {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	Double colIndex = 1.0;
	Double sortIndex = 1.0;
	

	private Llama() {
	
	}




	   public Llama(Builder builder) 
	    { 
		   /* Set Root */
		   this.add(builder.root);
	        /* set parent */
	        this.setParentCode(builder.root.getCode());
	        /* Set Kids */
	        this.setItems(builder.children.toArray(new BaseEntity[0]));
	    } 

	// Static class Builder 
	    public static class Builder { 
	  
	        /// instance fields 

	        private BaseEntity root;
	        List<BaseEntity> children = new ArrayList<BaseEntity>();
	        List<Tuple3<String,Frame.ThemeAttribute,String>> themeStrings =  new ArrayList<Tuple3<String, Frame.ThemeAttribute, String>>();;
	        List<Tuple3<String,Frame.ThemeAttribute,Integer>> themeIntegers =  new ArrayList<Tuple3<String, Frame.ThemeAttribute, Integer>>();;
	        List<Tuple1<String>> themeCodes = new ArrayList<Tuple1<String>>();
	        
	        public static Builder newInstance(final String llamaCode) 
	        { 
	            return new Builder(llamaCode); 
	        } 
	  
	        private Builder(final String llamaCode) {
	        	this.root = new BaseEntity(llamaCode);
	        } 
	  
	        public Builder addTheme(final String themeCode,Frame.ThemeAttribute attributeCode,String value) 
	        { 
	            Tuple3<String,Frame.ThemeAttribute,String> theme = Tuple.of(themeCode,attributeCode,value);
	            themeStrings.add(theme);
	            return this; 
	        } 

	        public Builder addTheme(final String themeCode,Frame.ThemeAttribute attributeCode,Integer value) 
	        { 
	            Tuple3<String,Frame.ThemeAttribute,Integer> theme = Tuple.of(themeCode,attributeCode,value);
	            themeIntegers.add(theme);
	            return this; 
	        } 

	        public Builder addTheme(final String themeCode) 
	        { 
	            Tuple1<String> theme = Tuple.of(themeCode);
	            themeCodes.add(theme);
	            return this; 
	        } 

	        // build method to deal with outer class 
	        // to return outer instance 
	        public Llama build() 
	        { 
	            return new Llama(this); 
	        } 
	    } 
	    


}
