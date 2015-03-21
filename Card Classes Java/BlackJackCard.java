import java.util.Comparator;
public class BlackJackCard extends Card {
    public BlackJackCard(int value, String name, Suit suit) {
        this.setValue(new Integer(value));
        calculatePoints();
        this.setName(name);
        this.setSuit(suit);
        this.setComparator(new BlackJackComparator<Card>());
    }

    public void calculatePoints() {
        switch (this.getName()) {
        case "ace" :
            this.setPoints(11);
            break;
        case "jack":
            this.setPoints(10);
            break;
        case "queen":
           this.setPoints(10);
            break;
        case "king":
            this.setPoints(10);
            break;
        default:
            this.setPoints(this.getValue());
            break;
        }
        this.setPoints(this.getValue());
    }
    private static class BlackJackComparator<T> implements Comparator<T> {
        public int compare(T a, T b) {
            Card aCard = (BlackJackCard) a;
            Card bCard = (BlackJackCard) b;
            return aCard.getPoints().compareTo(bCard.getPoints());
        }
    }
}