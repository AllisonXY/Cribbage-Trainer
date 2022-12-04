package scoreCalculator;

import java.util.ArrayList;

import ch.aplu.jcardgame.Hand;
import ch.aplu.jcardgame.Card;
import cribbage.Cribbage;

public class TotalShowScoreCalculator extends IShowCalculator {

	ArrayList<IShowCalculator> calculators;
	
	
	public TotalShowScoreCalculator() {
		this.calculators = new ArrayList<>();
		this.calculators.add(new ShowSumCalculator());
		this.calculators.add(new ShowRunCalculator());
		this.calculators.add(new ShowPairCalculator());
		this.calculators.add(new ShowFlushCalculator());
		this.calculators.add(new ShowJackStarterCalculator());
		this.scoreType = "Total"; // this should NEVER be printed to the log
	}
	
	
	// aggregate scores from component strategies
	@Override
	public int score(ArrayList<Card> hand, Hand starter, int playerNum, int curScore) {
		int totalScore = 0;
		for (IShowCalculator calculator : calculators) {
			
			// calculate score using the next strategy
			int nextScore = calculator.score(hand, starter, playerNum, curScore);
			
			// increment curScore by nextScore (to ensure log accuracy)
			curScore += nextScore;
			
			// keep track of the total score
			totalScore += nextScore;
		}
		
		return totalScore;
	}


	

}
