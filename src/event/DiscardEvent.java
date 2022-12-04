package event;

/*
 * 
 */
class DiscardEvent extends Event {
	private Integer playerNum;
	private String disCards;
	
	public DiscardEvent (String eventType, int playerNum, String disCards) {
		super(eventType);
		this.playerNum = new Integer(playerNum);
		this.disCards = disCards;
		log.logEvent(this);
	}
	
	@Override
	public String toString() {
		return eventType + ",P" + playerNum.toString() + "," + disCards;
	}
}
