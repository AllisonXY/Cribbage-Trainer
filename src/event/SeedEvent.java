package event;

/*
 * 
 */
class SeedEvent extends Event {
	private Integer seed;
	
	public SeedEvent(String eventType, int seed) {
		super(eventType);
		this.seed = new Integer(seed);	// cast to Integer to use toString
		log.logEvent(this);
	}
	
	@Override
	public String toString() {
		return eventType + "," + seed.toString();
	}
}
