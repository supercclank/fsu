import java.util.Scanner;

// Driver class to test the blackjack game

public class Test {

    private static BlackJackCard[] dealersTwo;
    
    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        BlackJackGame myGame = new BlackJackGame(1,"b");
        BlackJackPlayer p = new BlackJackPlayer("a", 1);
        myGame.addPlayer(p);
        myGame.firstStep();
        dealersTwo = p.getDealersTwo();
        while(!myGame.isOver()) {
            Card[] hand = p.getHand().getHand();
                int x = s.nextInt();
                if (x == 0) {
                    p.stick();
                }
            myGame.stepGame();
            }
            if(p.isWinner()) {
                System.out.println("won");
            } else {
                System.out.println("loser");
            }
        }

}