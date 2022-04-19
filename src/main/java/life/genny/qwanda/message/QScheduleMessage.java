package life.genny.qwanda.message;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;


public class QScheduleMessage implements Serializable {

	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		public LocalDateTime created = LocalDateTime.now(ZoneId.of("UTC"));
		public LocalDateTime updated;

		@Expose
		public String cron;
		
		@Expose
		@JsonProperty("triggerTime")
		public LocalDateTime triggertime;
		
		@Expose
		public String realm;

		@Expose
		public String jsonMessage;
		
		@Expose
		public String sourceCode;

		@Expose
		public String channel;
		
		@Expose
		public String code;


	
	public QScheduleMessage()
	{}
	
	public QScheduleMessage(final String code,final String jsonMessage, final String sourceCode, final String channel, final String cron, final String realm)
	{
		this.code = code;
		this.cron = cron;
		this.jsonMessage = jsonMessage;
		this.channel = channel;
		this.sourceCode = sourceCode;
	}
	
	public QScheduleMessage(final String code,final String jsonMessage, final String sourceCode, final String channel, final LocalDateTime triggertime, final String realm)
	{
		this.code = code;
		this.triggertime = triggertime;
		this.jsonMessage = jsonMessage;
		this.channel = channel;
		this.sourceCode = sourceCode;
	}
	
}
