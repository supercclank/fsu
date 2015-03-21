// Concrete class representing a human blackjack player

public class BlackJackPlayer extends CardPlayer {

    private boolean stick;
    private boolean bust;
    private boolean blackJack;
    private boolean canSplit;
    private Hand dealersTwo;

    public BlackJackPlayer(String ip,  int playerNumber){
        super(ip, 0, playerNumber, new BlackJackHand());
        stick = false;
        bust = false;
        blackJack = false;
        canSplit = false;
        dealersTwo = new BlackJackHand();
    }

    public void getCard(Card c) {
        this.getHand().addToHand(c);
        int handValue = ((BlackJackHand)this.getHand()).handValue();
        if (handValue > 21) {
            bust();
        } else if (handValue == 21) {
            blackJack = true;
            stick();
        }
    }

    public void setDealersTwo(BlackJackHand bh) {
        dealersTwo = bh;
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

    public boolean isSplittable() {
        return canSplit;
    }

    public void splittable() {
        canSplit = true;
    }

    public void split() {
        canSplit = false;
    }

    public Hand getDealersTwo() {
        return dealersTwo;
    }
}