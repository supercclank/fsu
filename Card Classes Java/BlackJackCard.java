import java.util.Comparator;
public class BlackJackCard extends Card {
    public BlackJackCard(int value, String name, Suit suit) {
        this.setValue(new Integer(value));
        this.setName(name);
        calculatePoints();
        this.setSuit(suit);
        this.setComparator(new BlackJackComparator<Card>());
    }

    public void calculatePoints() {
        switch (this.getValue()) {
        case 1 :
            this.setPoints(11);
            break;
        case 11:
            this.setPoints(10);
            break;
        case 12:
           this.setPoints(10);
            break;
        case 13:
            this.setPoints(10);
            break;
        default:
            this.setPoints(this.getValue());
            break;
        }
    }
    private static class BlackJackComparator<T> implements Comparator<T> {
        public int compare(T a, T b) {
            Card aCard = (BlackJackCard) a;
            Card bCard = (BlackJackCard) b;
            return aCard.getPoints().compareTo(bCard.getPoints());
        }
    }
}