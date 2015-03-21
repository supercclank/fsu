public class ComputerBlackJackPlayer extends ComputerCardPlayer {
    private boolean stick;
    private boolean bust;
    private boolean blackJack;

    public ComputerBlackJackPlayer(String ip,  int playerNumber){
        super(1, ip, 0, playerNumber, new BlackJackHand());
        stick = false;
        bust = false;
        blackJack = false;
    }

    public void getCard(Card c) {
        this.getHand().addToHand(c);
        int handValue = ((BlackJackHand)this.getHand()).handValue();
        if (handValue > 21) {
                bust();
            } else if (handValue == 21) {
                blackJack();
                stick();
            } else if (handValue > 17) {
                stick();
            }
    }

    public boolean isStick() {
        return stick;
    }

    public boolean isBust() {
        return bust;
    }

    private void bust() {
        bust = true;
    }

    public void stick() {
        stick = true;
    }

    public boolean isBlackJack() {
        return blackJack;
    }

    private void blackJack() {
        blackJack = true;
    }
    
}