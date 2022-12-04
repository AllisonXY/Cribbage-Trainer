package scoreCalculator;

import java.util.ArrayList;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;

public class ShowJackStarterCalculator extends IShowCalculator {
	Card jackCard;


	public ShowJackStarterCalculator() {
		this.scoreType = "jack"; 
	}


	//jack card (order 11) has same suit with starter card -->1 point

	@Override
	public int score(ArrayList<Card> hand, Hand starter, int playerNum, int curScore) {
		int score = 0;
		String starterSuit=Cribbage.getInstance().canonical(starter);
		Card starterCard = starter.getCardList().get(0);

		for(int i=0;i<hand.size();i++){
			int cardOrder=((Cribbage.Rank) hand.get(i).getRank()).order;
			if (cardOrder==11) {
				String jackSuit = Cribbage.getInstance().canonical(hand.get(i));
				jackCard=hand.get(i);
				if (jackSuit.equals(starterSuit)){
					score=1;
				}
			}
		}


		if (score > 0) {
			ArrayList<Card> scoredCard=new ArrayList<>();
			scoredCard.add(starterCard);
			scoredCard.add(jackCard);

			createScoreShowEvent(playerNum, curScore, score, scoreType, scoredCard);
		}

		return score;
	}
	
}
