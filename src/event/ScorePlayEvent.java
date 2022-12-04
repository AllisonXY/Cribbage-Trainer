package event;

public class ScorePlayEvent extends Event{
	private Integer playerNum;
	private Integer playerScore;
	private Integer scoredPoints;
	private String play;
	
	public ScorePlayEvent(String eventType, int playerNum, int playerScore, int scoredPts, String play) {
		super(eventType);
		this.playerNum = new Integer(playerNum);
		this.playerScore = new Integer(playerScore);
		this.scoredPoints = new Integer(scoredPts);
		this.play = play;
		log.logEvent(this);
	}
	
	@Override
	public String toString() {
		return eventType + ",P" + playerNum.toString() + "," + playerScore.toString() + "," + scoredPoints.toString() + "," + play;
	}
}
