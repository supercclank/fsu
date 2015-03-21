import java.lang.reflect.Array;
import java.util.Random;
import java.lang.reflect.InvocationTargetException;

// abstract class to represent a deck

public abstract class Deck {

    // cards that make up the deck
    private Card[] deck;
    int size;
    // permissable names for the cards
    private String[] names = {"ace", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "jack", "queen", "king", "lJoker", "bJoker"};
    
    public int size() {
        return size;
    }

    public boolean empty() {
        return size() == 0;
    }

    public void setDeck(Class<? extends Card> c, int size) throws NoSuchMethodException, InstantiationException,  IllegalAccessException, 
    InvocationTargetException {

        deck = (Card[]) Array.newInstance(c,size);

        // Polish gypsy magic here using Reflect to access the constructors of the particular card directly

        for(int x = 0; x < size; x++) {
            if (x / 13  < 1) {
                deck[x] = c.getConstructor(int.class, String.class, Card.Suit.class).newInstance(x % 13 + 1, names[x % 13], Card.Suit.DIAMONDS);
            } else if (x / 13 < 2) {
                deck[x] = c.getConstructor(int.class, String.class, Card.Suit.class).newInstance(x % 13 + 1, names[x % 13], Card.Suit.HEARTS);
            } else if (x / 13 < 3) {
                deck[x] = c.getConstructor(int.class, String.class, Card.Suit.class).newInstance(x % 13 + 1, names[x % 13], Card.Suit.SPADES);
            } else if (x / 13 < 4) {
                deck[x] = c.getConstructor(int.class, String.class, Card.Suit.class).newInstance(x % 13 + 1, names[x % 13], Card.Suit.CLUBS);
            } else {
                deck[x] = c.getConstructor(int.class, String.class, Card.Suit.class).newInstance(-1 * (x % 13), names[x % 13 + 13], Card.Suit.TRUMP);
            }
        }
        shuffle(deck);
        this.size = size;
    }

    public Card getCard() {
        return deck[--size];
    }

    public void shuffle(Card[] a) {
        int n = a.length;
        Random gen = new Random();
        gen.nextInt();
        for (int i = 0; i < n; i++) {
          int change = i + gen.nextInt(n - i);
          swap(a, i, change);
        }
      }

    private void swap(Card[] a, int i, int change) {
        Card temp = a[i];
        a[i] = a[change];
        a[change] = temp;
      }

}