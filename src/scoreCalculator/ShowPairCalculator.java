package scoreCalculator;

import java.util.ArrayList;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;

public class ShowPairCalculator extends IShowCalculator {

	public ShowPairCalculator() {
		this.scoreType = "pair"; // this should never be printed to the log
	}


	// TODO kind-same rank(order&value)
	public int getPairNum(ArrayList<Card> hand){
		int pairNum=0;
		for (int i = 0; i < hand.size()-1 ; i++) {
			for (int j=i+1;j<hand.size();j++) {


				int card1Order = ((Cribbage.Rank) hand.get(i).getRank()).order;
				int card2Order = ((Cribbage.Rank) hand.get(j).getRank()).order;

				if (card1Order == card2Order ) {  //2 cards have same order
					pairNum++;
				}
			}
		}
		return pairNum;
	}



	@Override
	public int score(ArrayList<Card> hand, Hand starter, int playerNum, int curScore) {
		// two points for a pair of cards of a kind (pair2)
		// six points for three cards of a kind (pair3)
		// twelve points for four cards of a kind (pair4)


		int finalScore = 0;

		// iterate over all card types
		for (Cribbage.Rank rank : Cribbage.Rank.values()) {

			int score=0;
			int curOrder = rank.order;

			// get the number of cards associated with the same suit
			ArrayList<Card> matching = findMatches(curOrder, hand);
			int numMatches = matching.size();


			// calculate and log the number of points earned
			if (numMatches == 2) {
				this.scoreType = "pair2";
				score = 2;
			} else if (numMatches == 3) {
				this.scoreType = "pair3";
				score = 6;
			} else if (numMatches == 4) {
				this.scoreType = "pair4";
				score = 12;
			}

			finalScore += score;

			if (score > 0) {
				createScoreShowEvent(playerNum, curScore, score, scoreType, matching);
			}
		}


		return finalScore;
	}

	private ArrayList<Card> findMatches(int value, ArrayList<Card> allCards) {

		ArrayList<Card> matchingCards = new ArrayList<>();

		for (Card card: allCards) {
			int cardOrder = ((Cribbage.Rank) card.getRank()).order;
			if (cardOrder == value) {
				matchingCards.add(card);
			}
		}


		return matchingCards;
	}


	// get the string representation of an ArrayList of cards (same format as canonical hand)
	String canonical(ArrayList<Card> cards) {

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
		return canonicalCards;
	}

}
