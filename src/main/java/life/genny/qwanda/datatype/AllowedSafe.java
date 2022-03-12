package life.genny.qwanda.datatype;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util.Objects;

import org.apache.logging.log4j.Logger;

public class AllowedSafe implements Serializable {
	protected static final Logger log = org.apache.logging.log4j.LogManager
			.getLogger(MethodHandles.lookup().lookupClass().getCanonicalName());
			
	public static final String CAP_CODE_PREFIX = "PRM_";
	
	public final String code;
	public final CapabilityMode[] modes;
	public final boolean validCode;
	
	public AllowedSafe(final String capCode, final boolean validCode, final CapabilityMode... modes)
	{
		this.code = capCode.toUpperCase();
		this.modes = modes;
		this.validCode = isValidCode(capCode);
	}

	@Override
	public String toString() {
		return "Allowed [" + (code != null ? "code=" + code + ", " : "") + (modes.length > 0 ? "mode=" + "" : "") + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, modes);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof AllowedSafe))
			return false;
		AllowedSafe other = (AllowedSafe) obj;
		return Objects.equals(code, other.code) && modes == other.modes;
	}
	
	public static boolean isValidCode(String capCode) {
		if(!capCode.startsWith(CAP_CODE_PREFIX))
			return false;
		String[] components = capCode.split("_");
		if(components.length < 3) {
			log.error("Missing OWN or OTHER in " + capCode);
			return false;
		}

		return true;
	}	
}
