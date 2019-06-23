package life.genny.qwanda.llama;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import io.vavr.Tuple;
import io.vavr.Tuple1;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import life.genny.qwanda.entity.BaseEntity;
import life.genny.qwanda.llama.Llama.Builder;
import life.genny.qwanda.message.QDataBaseEntityMessage;



/* Llama class implements the frame of base entities 
 */
public class Frame extends BaseEntity {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	Double colIndex = 1.0;
	Double sortIndex = 1.0;
	
	private FramePosition position;
    private List<Tuple3<String,Frame.ThemeAttribute,String>> themeStrings =  new ArrayList<Tuple3<String, Frame.ThemeAttribute, String>>();;
    private List<Tuple3<String,Frame.ThemeAttribute,Integer>> themeIntegers =  new ArrayList<Tuple3<String, Frame.ThemeAttribute, Integer>>();;
    private List<Tuple1<String>> themeCodes = new ArrayList<Tuple1<String>>();

    private List<Tuple2<String,FramePosition>> frameCodes = new ArrayList<Tuple2<String,FramePosition>>();
    private List<Tuple2<Frame,FramePosition>> frames = new ArrayList<Tuple2<Frame,FramePosition>>();	  

	
	public enum ThemeAttribute {
    backgroundColor ("backgroundColor"),
    width ("width"),
    flexDirection ("flexDirection"),
    justifyContent ("justifyContent");


    private final String name;       

    private ThemeAttribute(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false 
        return name.equals(otherName);
    }

    public String toString() {
       return this.name;
    }
	}
	
	public enum FramePosition {
	    NORTH ("NORTH"),
	    EAST ("EAST"),
	    WEST  ("WEST"),
	    SOUTH  ("SOUTH"),
	    CENTRE ("CENTRE");


	    private final String name;       

	    private FramePosition(String s) {
	        name = s;
	    }

	    public boolean equalsName(String otherName) {
	        // (otherName == null) check is not needed because name.equals(null) returns false 
	        return name.equals(otherName);
	    }

	    public String toString() {
	       return this.name;
	    }
		}


	private Frame() {
	
	}




	   public Frame(Builder builder) 
	    { 
		   /* Set Root */
		   	super(builder.code,builder.name);
			this.position = builder.position;
		
			//
			this.themeCodes = builder.themeCodes;
			this.themeIntegers = builder.themeIntegers;
			this.themeStrings = builder.themeStrings;
			this.frameCodes = builder.frameCodes;
			this.frames = builder.frames;
		
	    } 

	// Static class Builder 
	    public static class Builder { 
	  
	        /// instance fields 

	        private String code;
	        private String name;
	        private FramePosition position;
	        List<Tuple3<String,Frame.ThemeAttribute,String>> themeStrings =  new ArrayList<Tuple3<String, Frame.ThemeAttribute, String>>();;
	        List<Tuple3<String,Frame.ThemeAttribute,Integer>> themeIntegers =  new ArrayList<Tuple3<String, Frame.ThemeAttribute, Integer>>();;
	        List<Tuple1<String>> themeCodes = new ArrayList<Tuple1<String>>();

	        List<Tuple2<String,FramePosition>> frameCodes = new ArrayList<Tuple2<String,FramePosition>>();
	        List<Tuple2<Frame,FramePosition>> frames = new ArrayList<Tuple2<Frame,FramePosition>>();	  
	        
	        public static Builder newInstance(final String code) 
	        { 
	            return new Builder(code); 
	        } 
	  
	        private Builder(final String code) {
	        	this.code = code;

	        } 
	  
	        public Builder name(String name) 
	        { 
	            this.name = name; 
	            return this; 
	        } 
	        
	        public Builder position(FramePosition position)
	        {
	        	this.position = position;
	        	return this;
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
	        
	        public Builder addFrame(final String frameCode, FramePosition position)
	        {
	            Tuple2<String,FramePosition> frame = Tuple.of(frameCode,position);
	            frameCodes.add(frame);
	            return this; 
	        	
	        }
	        
	        public Builder addFrame(final Frame frame, FramePosition position)
	        {
	            Tuple2<Frame,FramePosition> frameTuple = Tuple.of(frame,position);
	            frames.add(frameTuple);
	            return this; 
	        	
	        }

	        public Builder addFrame(final Frame frame)
	        {
	            Tuple2<Frame,FramePosition> frameTuple = Tuple.of(frame,Frame.FramePosition.CENTRE);
	            frames.add(frameTuple);
	            return this; 	        	
	        }

	        
	        // build method to deal with outer class 
	        // to return outer instance 
	        public Frame build() 
	        { 
	        	if (StringUtils.isBlank(name)) {
	        		this.name = this.code;
	        	}
	        	
	        	if (position == null) {
	        		position = FramePosition.CENTRE;
	        	}
	            return new Frame(this); 
	        } 
	    }

		/**
		 * @return the position
		 */
		public FramePosition getPosition() {
			return position;
		}




		/**
		 * @return the serialversionuid
		 */
		public static long getSerialversionuid() {
			return serialVersionUID;
		}




		/**
		 * @return the colIndex
		 */
		public Double getColIndex() {
			return colIndex;
		}




		/**
		 * @return the sortIndex
		 */
		public Double getSortIndex() {
			return sortIndex;
		}




		/**
		 * @return the themeStrings
		 */
		public List<Tuple3<String, Frame.ThemeAttribute, String>> getThemeStrings() {
			return themeStrings;
		}




		/**
		 * @return the themeIntegers
		 */
		public List<Tuple3<String, Frame.ThemeAttribute, Integer>> getThemeIntegers() {
			return themeIntegers;
		}




		/**
		 * @return the themeCodes
		 */
		public List<Tuple1<String>> getThemeCodes() {
			return themeCodes;
		}




		/**
		 * @return the frameCodes
		 */
		public List<Tuple2<String, FramePosition>> getFrameCodes() {
			return frameCodes;
		}




		/**
		 * @return the frames
		 */
		public List<Tuple2<Frame, FramePosition>> getFrames() {
			return frames;
		}




}
