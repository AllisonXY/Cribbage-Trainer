package event;

class StarterEvent extends Event {
	private String starter;
	
	public StarterEvent (String eventType, String starter) {
		super(eventType);
		this.starter = starter;
		log.logEvent(this);
	}
	
	@Override
	public String toString() {
		return eventType + "," + starter;
	}
}

