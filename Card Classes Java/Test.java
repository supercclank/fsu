public class Test {
    public static void main(String[] args) {
        Player player1 = new CardPlayer("derp", 2, 1);
        Computer cpu1 = new ComputerCardPlayer(1,2,2);
        System.out.println(player1);
        System.out.println(cpu1);
        makeBlackJackDeck();
    }

}