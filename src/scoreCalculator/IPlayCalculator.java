package scoreCalculator;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;
import event.Event;
import event.EventFactory;

public abstract class IPlayCalculator {
	String scoreType; // e.g. run, sum. This is its representation on the log

	// calculate and return the score earned by the play
	public abstract int score(Hand segment, Card cardAdded, int playerNum, int curScore);

	// return the string representation of this scoring strategy
	public String getScoreType() {
		return scoreType;
	}

	// TODO - this function will create a ScorePlayEvent object to notify the logger if score was greater than zero
	// Don't return void once the ScorePlayEvent is implemented, return the event object
	// note: totalScore = score before this play, eventScore = points earned because of this strategy, cards = subset of
	// player's hand that earned the points

	public void createScorePlayEvent(int playerNum, int totalScore, int eventScore, String scoreType, Card cardPlayed) {
		int finalScore = totalScore + eventScore;
		System.out.println("Play Event will log:\t score,P" + playerNum + "," + finalScore + "," + eventScore + "," + scoreType + ",[CARD PLAYED]");
		
		
		
		
		EventFactory.getInstance().createScorePlay(playerNum, finalScore, eventScore, scoreType);
	}
}
