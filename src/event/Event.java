package event;

import logger.Logger;

/*
 * 
 */
public abstract class Event {
	String eventType;
	
	protected static Logger log = new Logger();
	
	public Event(String eventType) {
		this.eventType = eventType;
	}
	
	@Override
	public abstract String toString();
	
	
	public static void closeLog() {
		log.closeLog();
	}
}
