package life.genny.qwanda.message;

import life.genny.qwanda.rule.Rule;

public class QDataRuleMessage extends QDataMessage {

  private static final long serialVersionUID = 1L;
  private Rule[] items;
  private static final String DATATYPE_RULE = Rule.class.getSimpleName();
  private String token;

  public QDataRuleMessage(final Rule[] items) {
    super(DATATYPE_RULE);
    setItems(items);
  }

  public Rule[] getItems() {
    return items;
  }

  public void setItems(final Rule[] items) {
    this.items = items;
  }

/**
 * @return the token
 */
public String getToken() {
	return token;
}

/**
 * @param token the token to set
 */
public void setToken(String token) {
	this.token = token;
}
  
  
}
