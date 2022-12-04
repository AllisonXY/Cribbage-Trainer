package scoreCalculator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import ch.aplu.jcardgame.Hand;
import ch.aplu.jcardgame.Card;
import cribbage.Cribbage;

public class ShowSumCalculator extends IShowCalculator {
	public static int SUM=15;
	public static int SUM_SCORE=2;


	public ShowSumCalculator() {
		this.scoreType = "fifteen";
	}

	public static void findNumbers(ArrayList<ArrayList<Integer> > ans, ArrayList<Integer> arr, int sum, int index, ArrayList<Integer> temp)
	{
		index = index + 1;

		if (sum == 0) {
			// Adding deep copy of temp to ans
			ans.add(new ArrayList<>(temp));
			return;
		}

		for (int i = index; i < arr.size(); i++) {

			if ((sum - arr.get(i)) >= 0) {  //available to add more values

				temp.add(arr.get(i));

				findNumbers(ans, arr, sum - arr.get(i), i, temp); //recursion

				// removing element from temp (backtracking)
				temp.remove(arr.get(i));
			}
		}
	}


	@Override
	public int score(ArrayList<Card> hand, Hand starter, int playerNum, int curScore) {

		//create an arraylist that stores cardValues
		Card starterCard = starter.getCardList().get(0);
		hand.add(starterCard);

		int card0= Cribbage.cardValue(starterCard);

		ArrayList<Integer> cardValues=new ArrayList<>();  //arraylist that stores card values
		cardValues.add(card0);

		for(int i=0;i<hand.size()-1;i++){
			int cardValue=Cribbage.cardValue(hand.get(i));
			cardValues.add(cardValue);
		}

		ArrayList<ArrayList<Integer> > ans = combinationSum(cardValues, SUM);

		// create a copy of the hand to keep track of which cards have been used in a score
		ArrayList<Card> allCards = new ArrayList<Card>();
		for (Card card: hand) {
			allCards.add(card);
		}
		System.out.println("ALL CARDS: " + Cribbage.getInstance().canonical(allCards));

		// one score event for each combination of cards
		int totalScore = 0;
		for (ArrayList<Integer> combo : ans) {

			ArrayList<Card> scoreHand = new ArrayList<Card>();
			int cardsRequired = 0;

			// for each value in a set summing to 15, find a card matching this description
			for (int value : combo) {
				cardsRequired += 1;
				Card cardAdded = null;
				for (Card card : allCards) {
					int cardValue = Cribbage.cardValue(card);
					if (value == cardValue) {
						cardAdded = card;
						break;
					}
				}

				// if an appropriate card was found, add it to the list. Don't use it for future combinations
				if (cardAdded != null) {
					scoreHand.add(cardAdded);
					allCards.remove(cardAdded);
				} else {
					// otherwise, the card used in this combination was already added to a different combination
					break;
				}


			}

			// create a score event for each combination summing to 15

			if (scoreHand.size() != cardsRequired) {
				// want each combination of cards summing to 15 to be separate (i.e. contain different cards)
				// if a valid combination was found but some cards were used in a different run, the new combination is not counted
				continue;

			} else {
				createScoreShowEvent(playerNum, curScore, SUM_SCORE, scoreType, scoreHand);
				curScore += SUM_SCORE; // log accuracy
				totalScore += SUM_SCORE;
			}



		}


		return totalScore;
	}

	public static ArrayList<ArrayList<Integer> > combinationSum(ArrayList<Integer> arrayList, int sum) {
		ArrayList<ArrayList<Integer> > ans = new ArrayList<>();
		ArrayList<Integer> temp = new ArrayList<>();

		Collections.sort(arrayList);
		findNumbers(ans, arrayList, sum, 0, temp);
		return ans;
	}


}
