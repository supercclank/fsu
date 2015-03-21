public class ComputerBlackJackPlayer extends ComputerCardPlayer {
    private boolean fold;
    private boolean bust;

    public ComputerBlackJackPlayer(String ip,  int playerNumber){
        super(1, ip, 0, playerNumber, new BlackJackHand());
        fold = false;
        bust = false;
    }

    public void getCard(Card c) {
        this.getHand().addToHand(c);
    if (((BlackJackHand)this.getHand()).handValue() > 21) {
            bust();
        }
    }

    public boolean isFold() {
        return fold;
    }

    public boolean isBust() {
        return bust;
    }

    public void bust() {
        bust = true;
    }

    public void fold() {
        fold = true;
    }
}