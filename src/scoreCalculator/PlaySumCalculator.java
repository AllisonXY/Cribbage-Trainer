package scoreCalculator;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;

public class PlaySumCalculator extends IPlayCalculator {

	public PlaySumCalculator() {
		this.scoreType = "sum"; // shouldn't print this to the log, replace with thirtyone or fifteen
	}

	@Override
	public int score(Hand segment, Card cardAdded, int playerNum, int curScore) {

		// calculate the total value of the segment (including the added card)
		int totalHandValue = Cribbage.getInstance().total(segment);

		// determine if hand sums to 15 or 31
		int score = 0;
		if (totalHandValue == 15) {
			this.scoreType = "fifteen";
			score = 2;
		}
		if (totalHandValue == 31) {
			this.scoreType = "thirtyone";
			score = 2;
		}

		// log score if points earned
		if (score > 0) {
			//System.out.println("PLAY: " + score + " points from strategy " + scoreType);

			createScorePlayEvent(playerNum, curScore, score, scoreType, cardAdded);
		}

		return score;

	}

}
