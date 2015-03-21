public class Test {
    public static void main(String[] args) throws Exception {
        Player player1 = new BlackJackPlayer("derp", 1);
        Computer cpu1 = new ComputerBlackJackPlayer("q",1);
        System.out.println(player1);
        System.out.println(cpu1);
        Deck x = new BlackJackDeck();
    }

}