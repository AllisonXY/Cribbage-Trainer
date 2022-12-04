package scoreCalculator;

import java.util.ArrayList;
import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public class TotalPlayScoreCalculator extends IPlayCalculator {

	ArrayList<IPlayCalculator> calculators;
	
	public TotalPlayScoreCalculator() {
		this.calculators = new ArrayList<>();
		this.calculators.add(new PlaySumCalculator());
		this.calculators.add(new PlayRunCalculator());
		this.calculators.add(new PlayPairCalculator());
	}
	
	// aggregate scores from component strategies
	@Override
	public int score(Hand segment, Card cardAdded, int playerNum, int curScore) {
		int totalScore = 0;
		for (IPlayCalculator calculator : calculators) {
			
			// calculate score using the next strategy
			int nextScore = calculator.score(segment, cardAdded, playerNum, curScore);
			
			// increment curScore by nextScore (to ensure log accuracy)
			curScore += nextScore;
			
			// keep track of the total score
			totalScore += nextScore;
		}
		
		return totalScore;
	}

}
