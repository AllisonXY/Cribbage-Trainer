package scoreCalculator;

import java.util.ArrayList;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;

public class ShowFlushCalculator extends IShowCalculator {

	public ShowFlushCalculator() {
		this.scoreType = "flush";
	}


	//when all four cards in the hand are of the same suit --> 4 pt (flush4)
	//& when starter card also has same suit               --> 5 pt (flush5)

	@Override
	public int score(ArrayList<Card> hand, Hand starter, int playerNum, int curScore) {
		int score=0;
		String starterSuit=  Cribbage.getInstance().canonical(starter);
		String card1Suit = Cribbage.getInstance().canonical(hand.get(0));
		String card2Suit = Cribbage.getInstance().canonical(hand.get(1));
		String card3Suit = Cribbage.getInstance().canonical(hand.get(2));
		String card4Suit = Cribbage.getInstance().canonical(hand.get(3));

		if (card1Suit.equals(card2Suit) && card2Suit.equals(card3Suit)&& card3Suit.equals(card4Suit)) {
			this.scoreType = "flush4";
			score=4;

			if (card4Suit.equals(starterSuit)){
				this.scoreType = "flush5";
				score=5;
//				Card starterCard = starter.getCardList().get(0);
				hand.add(starter.getCard(0));

			}
		}


		// create a ScoreShowEvent and notify the logger
		if (score > 0) {
			createScoreShowEvent(playerNum, curScore, score, scoreType,hand);
		}
		
		return score;
	}
	
}
