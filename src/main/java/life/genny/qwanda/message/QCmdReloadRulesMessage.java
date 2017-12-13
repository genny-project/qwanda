package life.genny.qwanda.message;

public class QCmdReloadRulesMessage extends QCmdMessage{
	private static final String CMD_TYPE = "CMD_RELOAD_RULES";
	private static final String CODE = "RELOAD_RULES_FROM_FILES";

	private String rulesDir;
	
	public QCmdReloadRulesMessage() {
		super(CMD_TYPE, CODE);
		rulesDir = "rules"; // save rules in a rules dir in src/main/resources
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @return the rulesDir
	 */
	public String getRulesDir() {
		return rulesDir;
	}

	/**
	 * @param rulesDir the rulesDir to set
	 */
	public void setRulesDir(String rulesDir) {
		this.rulesDir = rulesDir;
	}


	
}
