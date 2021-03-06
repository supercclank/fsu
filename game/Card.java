import java.util.Comparator;

// Abstract class representing a single Card

public class Card {

    // value: numeric value of the card
    // point: point value of card in a particular game
    // name: ace, one, two, ... , ten, jack, queen, king
    private Integer value;
    private Integer points;
    private String name;
    private Comparator<Card> comparator;
    private Suit suit;

    public enum Suit {
        HEARTS, DIAMONDS, SPADES, CLUBS, TRUMP
    }

    public void calculatePoints() {

    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public Integer getValue() {
        return value;
    }

    public Integer getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    public Suit getSuit() {
        return suit;
    }

    public int compare(Card c, Comparator<Card> comparator) {
        return comparator.compare(this,c);
    }

    public String toString() {
        return " Value: " + value + " Points: " + points + " Name: " + name + " Comparator: " + comparator + " suit: " + suit;
    }
}