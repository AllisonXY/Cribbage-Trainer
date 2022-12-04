package scoreCalculator;

import java.util.ArrayList;
import java.util.Collections;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;

public class PlayPairCalculator extends IPlayCalculator {

	public PlayPairCalculator() {
		this.scoreType = "pair"; // shouldn't print this to the log
	}

	@Override
	public int score(Hand segment, Card cardAdded, int playerNum, int curScore) {

		System.out.println("CURRENT SEGMENT: ");
		ArrayList<Card> segmentTest = (ArrayList<Card>) segment.getCardList().clone();
		for (Card card: segmentTest) {
			System.out.println(Cribbage.getInstance().canonical(card));
		}

		// Iterate over played cards in reverse chronological order
		@SuppressWarnings("unchecked")
		ArrayList<Card> inSegment = (ArrayList<Card>) segment.getCardList().clone();
		Collections.reverse(inSegment);

		// Check for matches with the previous cards
		int numMatches = 0;
		int cardOrder = ((Cribbage.Rank) cardAdded.getRank()).order;
		//int cardValue = Cribbage.cardValue(cardAdded);
		for (Card card: inSegment) {

			int nextCardOrder = ((Cribbage.Rank) card.getRank()).order;

			// check match
			if (cardOrder == nextCardOrder) {
				numMatches++;
			} else {
				break;
			}
		}

		numMatches--; // don't count match with self

		// determine the score type and number of points earned
		int score;
		switch (numMatches) {
			case 1:
				this.scoreType = "pair2";
				score = 2;
				break;
			case 2:
				this.scoreType = "pair3";
				score = 6;
				break;
			case 3:
				this.scoreType = "pair4";
				score = 12;
				break;
			default:
				score = 0;
				break;
		}

		// log a score if points were earned
		if (score > 0) {
			//System.out.println("PLAY: " + score + " points from strategy " + scoreType);

			createScorePlayEvent(playerNum, curScore, score, scoreType, cardAdded);
		}

		return score;
	}

}
