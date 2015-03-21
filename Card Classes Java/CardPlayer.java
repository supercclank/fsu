package game;

// Concrete class representing a human player in a card game

public class CardPlayer extends Player {

    private Hand hand;

    public CardPlayer(String ip, int score, int playerNumber, Hand hand) {
        this.setIp(ip);
        this.setScore(score);
        this.setPlayerNumber(playerNumber);
        this.hand = hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }
}