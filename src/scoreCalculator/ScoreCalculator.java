package scoreCalculator;

import java.util.ArrayList;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import logger.Logger;


// consider adding the call to updateScore() to the end of every score function. Will require playerNum as a parameter
public class ScoreCalculator {

	// responsible for calculating total score during the show
	private TotalShowScoreCalculator showCalculator;

	// responsible for calculating total score during the play
	private TotalPlayScoreCalculator playCalculator;

	// scores starter card
	private PlayJackStarterCalculator starterCalculator;

	// scores 'go'
	private PlayGoCalculator goCalculator;

	public ScoreCalculator() {
		this.showCalculator = new TotalShowScoreCalculator();
		this.playCalculator = new TotalPlayScoreCalculator();
		this.starterCalculator = new PlayJackStarterCalculator();
		this.goCalculator = new PlayGoCalculator();
	}

	public int scoreShow(ArrayList<Card> hand, Hand starter, int playerNum, int curScore) {
		int score = showCalculator.score(hand, starter, playerNum, curScore);
		return score;
	}

	public int scorePlay(Hand segment, Card cardAdded, int playerNum, int curScore) {
		int score = playCalculator.score(segment, cardAdded, playerNum, curScore);
		return score;
	}

	public int scoreStarter(Hand starter) {
		int score = starterCalculator.score(starter, null, 1, 0);
		return score;
	}

	// points for being the last player to place a card
	public int scoreGo(int playerNum, int curScore) {
		int score = goCalculator.score(null, null, playerNum, curScore);
		return score;
	}
}
