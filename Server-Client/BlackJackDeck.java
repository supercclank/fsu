import java.lang.reflect.InvocationTargetException;

// Concrete class represents a deck in a blackjack game
public class BlackJackDeck extends Deck {

    public BlackJackDeck() throws NoSuchMethodException, InstantiationException,  IllegalAccessException, 
    InvocationTargetException {
        setDeck(BlackJackCard.class,52);
    }

}