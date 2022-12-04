package event;

/*
 * 
 */
class DealEvent extends Event {
	private Integer playerNum;
	private String cardsDealt;
	
	public DealEvent (String eventType, int playerNum, String cardsDealt) {
		super(eventType);
		this.playerNum = new Integer(playerNum);
		this.cardsDealt = cardsDealt;
		log.logEvent(this);
	}
	
	@Override
	public String toString() {
		return eventType + ",P" + playerNum.toString() + "," + cardsDealt;
	}
}
