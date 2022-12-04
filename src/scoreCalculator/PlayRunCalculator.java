package scoreCalculator;

import java.util.ArrayList;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;

public class PlayRunCalculator extends IPlayCalculator {

	public PlayRunCalculator() {
		this.scoreType = "run"; // shouldn't print this to the log
	}

	@Override
	public int score(Hand segment, Card cardAdded, int playerNum, int curScore) {

		ArrayList<Card> inSegment = (ArrayList<Card>) segment.getCardList().clone();

		// calculate length of the longest run, with and without the card
		int longestRunWithCard = longestRunLen(inSegment);

		inSegment.remove(cardAdded); // remove the card that was just added
		int longestRunWithoutCard = longestRunLen(inSegment);

		// calculate the score
		int score = 0;

		// check if adding the card increased the length of the run, find appropriate score
		if (longestRunWithoutCard != longestRunWithCard) {
			switch (longestRunWithCard) {
				case 3:
					this.scoreType = "run3";
					score = 3;
					break;
				case 4:
					this.scoreType = "run4";
					score = 4;
					break;
				case 5:
					this.scoreType = "run5";
					score = 5;
					break;
				case 6:
					this.scoreType = "run6";
					score = 6;
					break;
				case 7:
					this.scoreType = "run7";
					score = 7;
					break;
			}
		}

		// log score if points were achieved
		if (score > 0) {
			//System.out.println("PLAY: " + score + " points from strategy " + scoreType);

			createScorePlayEvent(playerNum, curScore, score, scoreType, cardAdded);
		}

		return score;

	}

	// return the length of the longest run
	private int longestRunLen(ArrayList<Card> cards) {

		int curRunLen = 0;
		int longestLen = 0;

		for (int i = 1; i<14; i++) {
			boolean matchFound = false;
			boolean runBroken = false;

			// check if there is a card that will continue the current run
			for (Card card: cards) {
				int cardOrder = ((Cribbage.Rank) card.getRank()).order;

				if (cardOrder == i) {
					// if a card belongs at this position, place it here

					if (!matchFound) {
						// this card continues the run
						//System.out.println("card " + Cribbage.getInstance().canonical(card) + " adds to run");
						matchFound = true;
						curRunLen++;

					} else if (i != 1) {
						// this card is a duplicate and breaks the run (will not break the run if duplicate ace)
						//System.out.println("card " + Cribbage.getInstance().canonical(card) + " breaks the run");
						runBroken = true;
						break;
					}
				}
			}

			// the run is over if a king was just counted
			if (i == 13) {
				runBroken = true;
			}

			// if the run is over, report the longest length
			if (!matchFound || runBroken) {
				//System.out.println("Run over. Length = " + curRunLen);
				if (curRunLen > longestLen) {
					longestLen = curRunLen;
					//System.out.println("New longest run");
				}
				curRunLen = 0;
			} else {
				//System.out.println("Run will continue. Length = " + curRunLen);
			}

		}


		return longestLen;
	}


}
