package life.genny.qwanda.datatype;

import java.io.Serializable;

public class Allowed implements Serializable {
	public String code;
	public CapabilityMode mode;
	
	public Allowed(final String code, final CapabilityMode mode)
	{
		this.code = code;
		this.mode = mode;
	}
	
	
}
