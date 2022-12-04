package event;

/*
 * 
 */
class PlayEvent extends Event {
	private Integer playerNum;
	private Integer segmentValue;
	private String lastPlayed;
	
	public PlayEvent (String eventType, int playerNum, int segmentValue, String lastPlayed) {
		super(eventType);
		this.playerNum = new Integer(playerNum);
		this.segmentValue = new Integer(segmentValue);
		this.lastPlayed = lastPlayed;
		log.logEvent(this);
	}
	
	@Override
	public String toString() {
		return eventType + ",P" + playerNum.toString() + "," + segmentValue.toString() + "," + lastPlayed;
	}
}
