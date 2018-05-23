package life.genny.qwanda.message;

public class QEventCronMessage extends QEventMessage {


	private static final long serialVersionUID = 1L;

	private static final String EVENT_TYPE_CRON = "CRON_EVENT";
  private String cronCode;

	public QEventCronMessage(String btnCode) {
		super(EVENT_TYPE_CRON, btnCode);
	}

	public String getCronCode() {
		return this.cronCode;
	}

	public void setCronCode(String cronCode) {
		this.cronCode = cronCode;
	}
}
