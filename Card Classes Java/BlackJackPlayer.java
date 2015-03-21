public class BlackJackPlayer extends CardPlayer {
    private boolean stick;
    private boolean bust;
    private boolean blackJack;
    private BlackJackHand hand2;

    public BlackJackPlayer(String ip,  int playerNumber){
        super(ip, 0, playerNumber, new BlackJackHand());
        stick = false;
        bust = false;
    }

    public void getCard(Card c) {
        this.getHand().addToHand(c);
        int handValue = ((BlackJackHand)this.getHand()).handValue();
        int hand2Value = (hand2 != null) ? hand2.handValue() : 0;
        //add split check here
        if (handValue > 21) {
            bust();
        } else if (handValue == 21) {
            blackJack = true;
            stick = true;
        }
        if (hand2Value > 21) {
            bust();
        } else if (handValue == 21) {
            blackJack = true;
            stick = true;
        }
    }

    public boolean isStick() {
        return stick;
    }

    public boolean isBust() {
        return bust;
    }

    public void bust() {
        bust = true;
    }

    public void stick() {
        stick = true;
    }
}