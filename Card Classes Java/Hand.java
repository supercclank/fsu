package game;

// Concrete class representing a hand of cards
public class Hand {

    // Array of Cards that make up the hand
    private Card[] hand;
    
    private int size;
    private int current;

    public Hand(int maxsize) {
        current = 0;
        hand = new Card[maxsize];
        size = 0;
    }

    public void addToHand(Card c) {
        hand[current++] = c;
        size++;
    }

    public void moveCards(int a, int b) {
        Card temp = hand[a];
        hand[a] = hand[b];
        hand[b] = temp;
    }

    public Card playcard(int index) {
        size--;
        return hand[index];
    }

    public Card[] getHand() {
        return hand;
    }

    public int size() {
        return size;
    }

    public int handValue() {
        return 0;
    }

}