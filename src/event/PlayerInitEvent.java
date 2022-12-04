package event;

class PlayerInitEvent extends Event {
	private Integer playerId;
	
	public PlayerInitEvent(String eventType, int playerId) {
		super(eventType);
		this.playerId = new Integer(playerId);
		log.logEvent(this);
	}
	
	@Override
	public String toString() {
		return eventType + ",P" + playerId.toString();
	}
}
