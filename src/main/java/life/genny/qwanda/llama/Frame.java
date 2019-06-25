package life.genny.qwanda.llama;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import io.vavr.Tuple4;
import io.vavr.Tuple5;
import life.genny.qwanda.entity.BaseEntity;
import life.genny.qwanda.llama.Frame.ThemeAttribute;

/* Llama class implements the frame of base entities 
 */
public class Frame extends BaseEntity {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private FramePosition position;
	private BaseEntity parent;
	private List<Tuple4<String, Frame.ThemeAttribute,  Object, Double>> themeObjects = new ArrayList<Tuple4<String, Frame.ThemeAttribute, Object, Double>>();;

	private List<Tuple3<String, FramePosition, Double>> frameCodes = new ArrayList<Tuple3<String, FramePosition, Double>>();
	private List<Tuple3<Frame, FramePosition, Double>> frames = new ArrayList<Tuple3<Frame, FramePosition, Double>>();

	public enum ThemeAttribute {
		PRI_CONTENT("PRI_CONTENT"), PRI_CONTENT_HOVER("PRI_CONTENT_HOVER"), PRI_CONTENT_ACTIVE("PRI_CONTENT_ACTIVE"),
		PRI_CONTENT_DISABLED("PRI_CONTENT_DISABLED"), PRI_CONTENT_CLOSED("PRI_CONTENT_CLOSED"),
		PRI_IS_INHERITABLE("PRI_IS_INHERITABLE"), PRI_IS_EXPANDABLE("PRI_IS_EXPANDABLE"),
		PRI_HAS_QUESTION_GRP_INPUT("PRI_HAS_QUESTION_GRP_INPUT"), PRI_HAS_LABEL("PRI_HAS_LABEL"),
		PRI_HAS_REQUIRED("PRI_HAS_REQUIRED"), PRI_HAS_HINT("PRI_HAS_HINT"), PRI_HAS_DESCRIPTION("PRI_HAS_DESCRIPTION"),
		PRI_HAS_ICON("PRI_HAS_ICON"),

		codeOnly("codeOnly"); // used to pass an existing theme

		private final String name;

		private ThemeAttribute(String s) {
			name = s;
		}

		public boolean equalsName(String otherName) {
			// (otherName == null) check is not needed because name.equals(null) returns
			// false
			return name.equals(otherName);
		}

		public String toString() {
			return this.name;
		}
	}


	public enum FramePosition {
		NORTH("NORTH"), EAST("EAST"), WEST("WEST"), SOUTH("SOUTH"), CENTRE("CENTRE");

		private final String name;

		private FramePosition(String s) {
			name = s;
		}

		public boolean equalsName(String otherName) {
			// (otherName == null) check is not needed because name.equals(null) returns
			// false
			return name.equals(otherName);
		}

		public String toString() {
			return this.name;
		}
	}

	private Frame() {

	}

	public Frame(Builder builder) {
		/* Set Root */
		super(builder.code, builder.name);
		this.position = builder.position;

		//
		this.themeObjects = builder.themeObjects;
		this.frames = builder.frames;

	}

	// Static class Builder
	public static class Builder {
		Double frameWeight = 1.0; // used to set the order
		Double themeWeight = 1000.0; // themes weight goes backward

		/// instance fields

		private String code;
		private String name;
		private FramePosition position;
		List<Tuple4<String, Frame.ThemeAttribute,  Object, Double>> themeObjects = new ArrayList<Tuple4<String, Frame.ThemeAttribute, Object, Double>>();;

		List<Tuple3<Frame, FramePosition, Double>> frames = new ArrayList<Tuple3<Frame, FramePosition, Double>>();

		public static Builder newInstance(final String code) {
			return new Builder(code);
		}

		private Builder(final String code) {
			this.code = code;

		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder position(FramePosition position) {
			this.position = position;
			return this;
		}

		public Builder addTheme(final String themeCode, String property, Object value) {
			return addTheme(themeCode,ThemeAttribute.PRI_CONTENT,property,value);
		}
		
		public Builder addTheme(final String themeCode, Frame.ThemeAttribute attributeCode, String property, Object value) {
			String stringValue = null;
			if (value instanceof Integer) {
				stringValue = value+"";
			} else if (value instanceof String) {
				stringValue = "\""+value+"\"";
			} else {
				stringValue = value.toString();
			}
			String propertyValue = "\""+property+"\":"+stringValue+"";
			
			Tuple4<String, Frame.ThemeAttribute, Object, Double> theme = Tuple.of(themeCode, attributeCode, propertyValue,
					themeWeight);
			themeObjects.add(theme);
			themeWeight = themeWeight - 1.0;
			return this;
		}

		public Builder addTheme(final String themeCode) {
			Frame.ThemeAttribute codeOnly = Frame.ThemeAttribute.codeOnly;
			Tuple4<String, Frame.ThemeAttribute, Object, Double> theme = Tuple.of(themeCode, codeOnly, "codeOnly",
					themeWeight);
			themeObjects.add(theme);
			themeWeight = themeWeight - 1.0;
			return this;
		}

		public Builder addFrame(final Frame frame, FramePosition position) {
			Tuple3<Frame, FramePosition, Double> frameTuple = Tuple.of(frame, position, frameWeight);
			frames.add(frameTuple);
			frameWeight = frameWeight + 1.0;
			return this;

		}

		public Builder addFrame(final Frame frame) {
			return this.addFrame(frame, Frame.FramePosition.CENTRE);
		}

		public Builder addFrame(final String frameCode) {
			return this.addFrame(frameCode, Frame.FramePosition.CENTRE);
		}

		public Builder addFrame(final String frameCode, FramePosition position) {
			Frame frame = new Frame.Builder(frameCode).build();
			Tuple3<Frame, FramePosition, Double> frameTuple = Tuple.of(frame, Frame.FramePosition.CENTRE, frameWeight);
			frames.add(frameTuple);
			frameWeight = frameWeight + 1.0;
			return this;
		}

		// build method to deal with outer class
		// to return outer instance
		public Frame build() {
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
	 * @return the frameCodes
	 */
	public List<Tuple3<String, FramePosition, Double>> getFrameCodes() {
		return frameCodes;
	}

	/**
	 * @return the frames
	 */
	public List<Tuple3<Frame, FramePosition, Double>> getFrames() {
		return frames;
	}

	/**
	 * @return the themeObjects
	 */
	public List<Tuple4<String, Frame.ThemeAttribute, Object, Double>> getThemeObjects() {
		return themeObjects;
	}

	/**
	 * @return the parent
	 */
	public BaseEntity getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(BaseEntity parent) {
		this.parent = parent;
	}

	
	
}
