public class TestGson{
    public static void main(String[] args) {
        Hand h = new Hand(2);
        h.addToHand(new BlackJackCard(1,"1",Card.Suit.TRUMP));
        String secret = Message.makeMessage(h);
        System.out.println("asdf" + secret);
        Hand h2 = Message.unpackMessage(secret);
    }
}