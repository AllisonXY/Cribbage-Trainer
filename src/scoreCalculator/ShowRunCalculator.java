package scoreCalculator;

import java.util.ArrayList;


import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;


public class ShowRunCalculator extends IShowCalculator {

	public ShowRunCalculator() {
		this.scoreType = "run"; // this should never be printed to the log
	}

	ArrayList<Card> scoredCard = new ArrayList<>();


	//3 consecutive cards --> 3 pt (run 3)
	//4 consecutive cards --> 4 pt (run 4)
	//5 consecutive cards --> 5 pt (run 5)
	//			int cardOrder=((Cribbage.Rank) hand.get(i).getRank()).order;


	@Override
	public int score(ArrayList<Card> hand, Hand starter, int playerNum, int curScore) {
		int score = 0;
		Card starterCard = starter.getCardList().get(0);

		int card0 = ((Cribbage.Rank) starterCard.getRank()).order; //card order of starter card

		ArrayList<Integer> cardOrders = new ArrayList<>();  //arraylist that stores card orders
		cardOrders.add(card0);

		for (int i = 0; i < hand.size(); i++) {
			int cardOrder = ((Cribbage.Rank) hand.get(i).getRank()).order;
			cardOrders.add(cardOrder);
		}

		int cardValue0 = cardOrders.get(0);
		int cardValue1 = cardOrders.get(1);
		int cardValue2 = cardOrders.get(2);
		int cardValue3 = cardOrders.get(3);
		int cardValue4 = cardOrders.get(4);

		//for 5 consecutive runs
		if (cardValue0+1 == cardValue1 && cardValue1+1 == cardValue2 && cardValue2+1 == cardValue3 && cardValue3+1 == cardValue4) {
			score += 5;
			this.scoreType = "run5";
			scoredCard.add(starterCard);
			scoredCard.add(hand.get(0));
			scoredCard.add(hand.get(1));
			scoredCard.add(hand.get(2));
			scoredCard.add(hand.get(3));
		}




		//for 4 consecutive runs
		for (int i = 0; i < cardOrders.size() - 3; i++) {
			for (int j = i + 1; j < cardOrders.size() - 2; j++) {
				for (int k = j + 1; k < cardOrders.size() - 1; k++) {
					for (int m = k + 1; m < cardOrders.size(); m++) {
						if (cardOrders.get(i)+1 == cardOrders.get(j) && cardOrders.get(j)+1 == cardOrders.get(k)+1 && cardOrders.get(k)+1 == cardOrders.get(m)) {
							score += 4;
							if (i == 0) {
								scoredCard.add(starterCard);
								scoredCard.add(hand.get(j - 1));
								scoredCard.add(hand.get(k - 1));
								scoredCard.add(hand.get(m - 1));
							} else {
								this.scoreType = "run4";
								scoredCard.add(hand.get(i - 1));
								scoredCard.add(hand.get(j - 1));
								scoredCard.add(hand.get(k - 1));
								scoredCard.add(hand.get(m - 1));
							}
						}
					}
				}
			}
		}


		//for 3 consecutive runs
		for (int i = 0; i < cardOrders.size() - 2; i++) {
			for (int j = i + 1; j < cardOrders.size() - 1; j++) {
				for (int k = j + 1; k < cardOrders.size(); k++) {
					if (cardOrders.get(i)+1 == cardOrders.get(j) && cardOrders.get(j)+1 == cardOrders.get(k)) {
						score += 3;
						this.scoreType = "run3";
						if (i == 0) {
							scoredCard.add(starterCard);
							scoredCard.add(hand.get(j - 1));
							scoredCard.add(hand.get(k - 1));
						} else {
							scoredCard.add(hand.get(i - 1));
							scoredCard.add(hand.get(j - 1));
							scoredCard.add(hand.get(k - 1));
						}
					}
				}
			}
		}



		if (score > 0) {
			createScoreShowEvent(playerNum, curScore, score, scoreType, scoredCard);
		}

		return score;


	}

}








