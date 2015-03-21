    public class BlackJackHand extends Hand {
    private int handValue;

    public BlackJackHand() {
        super(9);
    }

    public int handValue() {
        int numAces = 0;
        int total = 0;
        Card c;
        for(int x = 0; x < this.size(); x++) {
            c = this.getHand()[x];
            if (c.getName().equals("ace")) {
                numAces++;
            }
            total = total + c.getPoints();
        }
        while(total > 21 && numAces > 0) {
            total = total - 10;
            numAces--;
        }
        return total;
    }

}