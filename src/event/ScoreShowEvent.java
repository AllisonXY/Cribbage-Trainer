package event;

public class ScoreShowEvent extends Event{
	private Integer playerNum;
	private Integer playerScore;
	private Integer scoredPoints;
	private String play;
	private String cards;
	
	public ScoreShowEvent(String eventType, int playerNum, int playerScore, int scoredPts, String play, String cards) {
		super(eventType);
		this.playerNum = new Integer(playerNum);
		this.playerScore = new Integer(playerScore);
		this.scoredPoints = new Integer(scoredPts);
		this.play = play;
		this.cards = cards;
		log.logEvent(this);
	}
	
	@Override
	public String toString() {
		return eventType + ",P" + playerNum.toString() + "," + playerScore.toString() + "," + scoredPoints.toString() + "," + play + "," + cards;
	}
}
