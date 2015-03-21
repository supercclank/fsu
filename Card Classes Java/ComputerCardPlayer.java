public class ComputerCardPlayer extends Computer {
    private Hand hand;
    
    public ComputerCardPlayer(int difficulty, String ip, int score, int playerNumber, Hand hand) {
        this.difficulty = difficulty;
        this.setScore(score);
        this.setPlayerNumber(playerNumber);
        this.hand = hand;
        this.setIp(ip);
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }
}