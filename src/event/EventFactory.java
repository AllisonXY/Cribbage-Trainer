package event;

public class EventFactory {
	
	public static EventFactory factory = new EventFactory();
	
	public static EventFactory getInstance() {
		return factory;
	}
 	
	
	public Event createSeed(int seed) {
		return new SeedEvent("seed", seed);
	}
	
	public Event createPlayer(String type, int id) {
		return new PlayerInitEvent(type, id);
	}
	
	public Event createDeal(int playerNum, String hand) {
		return new DealEvent("deal", playerNum, hand);
	}
	
	public Event createDiscard(int playerNum, String hand) {
		return new DiscardEvent("discard", playerNum, hand);
	}
	
	public Event createStarter(String starterCard) {
		return new StarterEvent("starter", starterCard);
	}
	
	public Event createPlay(int playerNum, int totalScore, String cardPlayed) {
		return new PlayEvent("play", playerNum, totalScore, cardPlayed);
	}
	
	public Event createShow(int playerNum, String starterCard, String hand) {
		return new ShowEvent("show", playerNum, starterCard, hand);
	}
	
	public Event createScorePlay(int playerNum, int playerScore, int scoredPoints, String play) {
		return new ScorePlayEvent("score", playerNum, playerScore, scoredPoints, play);
	}
	
	public Event createScoreShow(int playerNum, int playerScore, int scoredPoints, String play, String cards) {
		
		System.out.println("CREATING SCORESHOWEVENT\n");
		return new ScoreShowEvent("score", playerNum, playerScore, scoredPoints, play, cards);
	}
}
