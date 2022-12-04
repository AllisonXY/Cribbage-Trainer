package scoreCalculator;

import java.util.ArrayList;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;

public class PlayJackStarterCalculator extends IPlayCalculator {

    public PlayJackStarterCalculator() {
        this.scoreType = "starter"; // shouldn't print this to the log
    }

    @Override
    public int score(Hand segment, Card cardAdded, int playerNum, int curScore) {

        // get the starter card
        Card starterCard = ((ArrayList<Card>) segment.getCardList()).get(0);
        int cardOrder = ((Cribbage.Rank) starterCard.getRank()).order;

        //System.out.println("Starter card: " + cardOrder);

        // 2 points if the starter is a jack
        int score = 0;
        if (cardOrder == 11) {
            score = 2;
        }


        // log a score if points were earned
        if (score > 0) {
            //System.out.println("PLAY: " + score + " points from strategy " + scoreType);

            createScorePlayEvent(playerNum, curScore, score, scoreType, cardAdded);
        }

        return 0;
    }

}
