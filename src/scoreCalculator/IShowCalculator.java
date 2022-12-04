package scoreCalculator;

import java.util.ArrayList;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;
import event.EventFactory;

public abstract class IShowCalculator {
	
	String scoreType; // e.g. run, sum. This is its representation on the log


	// calculate and return the score associated with the hand
	public abstract int score(ArrayList<Card> hand, Hand starter, int playerNum, int curScore);
	
	// return the string representation of this scoring strategy
	public String getScoreType() {
		return scoreType;
	}
	
	// TODO - this function will create a ScoreShowEvent object to notify the logger if score was greater than zero
	// Don't return void once the ScoreShowEvent is implemented, return the event object
	// note: totalScore = score before this play, eventScore = points earned because of this strategy, cards = subset of
	// player's hand that earned the points

	public void createScoreShowEvent(int playerNum, int totalScore, int eventScore, String scoreType, ArrayList<Card> cards) {
		int finalScore = totalScore + eventScore;
		System.out.println("Score Event will log:\t score,P" + playerNum + "," + finalScore + "," + eventScore + "," + scoreType + ",[CARDS]");
		
		// convert the arraylist of cards to a string
		String canonicalCards = "[";
		int numCards = cards.size();
		for (int i = 0; i<numCards; i++) {
			canonicalCards += Cribbage.getInstance().canonical(cards.get(i));
			if (i < numCards -1) {
				canonicalCards += ",";
			} else {
				canonicalCards += "]";
			}
		}
		 
		
		
		
		System.out.println("CARDS = " + canonicalCards);
		
		EventFactory.getInstance().createScoreShow(playerNum, finalScore, eventScore, scoreType, canonicalCards);
		
	}


	
}
