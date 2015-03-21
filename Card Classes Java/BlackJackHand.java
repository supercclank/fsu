public class BlackJackHand extends Hand {
    private int handValue;

    public BlackJackHand() {
        super(9);
    }

    public BlackJackHand splitHand(int... splitCards) {
        BlackJackHand hand2 = new BlackJackHand();
        for(int index : splitCards) {
            hand2.addToHand(playcard(index));
        }
        return hand2;
    }

    public int handValue() {
        int numAces = 0;
        int total = 0;
        for(Card c : this.getHand()) {
            if (c.getName().equals("ace")) {
                numAces++;
            }
            total = total + c.getPoints();
        }
        while(total > 21 && numAces >= 0) {
            total = total - 10;
            numAces--;
        }
        return total;
    }

}