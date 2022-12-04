package event;

/*
 * 
 */
class ShowEvent extends Event {
	private Integer playerNum;
	private String starter;
	private String hand;
	
	public ShowEvent (String eventType, int playerNum, String starter, String hand) {
		super(eventType);
		this.playerNum = new Integer(playerNum);
		this.starter = starter;
		this.hand = hand;
		log.logEvent(this);
	}
	
	@Override
	public String toString() {
		return eventType + ",P" + playerNum.toString() + "," + starter + "+" + hand;
	}
}
