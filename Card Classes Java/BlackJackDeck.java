package game;

import java.lang.reflect.InvocationTargetException;

public class BlackJackDeck extends Deck {
    public BlackJackDeck() throws NoSuchMethodException, InstantiationException,  IllegalAccessException, 
    InvocationTargetException {
        setDeck(BlackJackCard.class,52);
    }

}