package scoreCalculator;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public class PlayGoCalculator extends IPlayCalculator {

    public PlayGoCalculator() {
        this.scoreType = "go"; // shouldn't print this to the log
    }

    @Override
    public int score(Hand segment, Card cardAdded, int playerNum, int curScore) {

        // always return 1 point for a go
        int score = 1;
        createScorePlayEvent(playerNum, curScore, score, scoreType, cardAdded);
        return score;
    }

}
