package game;

public class BlackJackGame extends CardGame {
    private int current;
    private BlackJackDeck deck;
    private int numberHumans;
    private int humansFinished;
    private int numberBlackJacks;
    public BlackJackGame(int numHumans, String ip) throws Exception {
        setSize(numHumans + 1);
        super.addPlayer(new ComputerBlackJackPlayer(ip, 0));
        current = 1;
        deck = new BlackJackDeck();
        this.numberHumans = numHumans;
        humansFinished = 0;
        numberBlackJacks = 0;
    }

    public void firstStep() {
        Player[] players = getPlayers();
        ComputerBlackJackPlayer dealer;
        dealer = (ComputerBlackJackPlayer) players[0];
        BlackJackPlayer bp;
        for(int y = 0; y < 2; y++) {
            for(int x = 0; x < numberHumans; x++) {
                bp = (BlackJackPlayer) players[1 + x];
                bp.getCard(deck.getCard());
            }
            dealer.getCard(deck.getCard());
        }
        for(int x = 0; x < numberHumans; x++) {
                bp = (BlackJackPlayer) players[1 + x];
                bp.setDealersTwo((BlackJackHand)dealer.getHand());
            }
    }

    public void addPlayer(BlackJackPlayer bp) {
        super.addPlayer(bp);
    }

    public void stepGame() {
        Player[] players = getPlayers();
        ComputerBlackJackPlayer dealer;
        dealer = (ComputerBlackJackPlayer) players[0];
        BlackJackPlayer bp;
        for(int x = 0; x < numberHumans; x++) {
            bp = (BlackJackPlayer) players[1 + x];
            if (!bp.isStick() && !bp.isBlackJack() && !bp.isBust()) {
                bp.getCard(deck.getCard());
                if (bp.getHand().size() == 2) {
                    checkDoubles(bp);
                }
            }
                if (bp.isBust()) {
                    bp.lose();
                    humansFinished++;
                } else if (bp.isBlackJack()) {
                    bp.win();
                    numberBlackJacks++;
                    humansFinished++;
                } else if (bp.isStick()) {
                    humansFinished++;
                }
            
        }
        if(humansFinished == numberHumans) {
            while (!dealer.isStick() && !dealer.isBlackJack() && !dealer.isBust()) {
                dealer.getCard(deck.getCard());
            }
            if (dealer.isBust()) {
                    dealer.lose();
                } else if (dealer.isBlackJack()) {
                    dealer.win();
                    numberBlackJacks++;
                }
            end();
        }
    }

    public int end() {
        super.end();
        int index = 0;
        int max = 0;
        int handValue = 0;
        if (numberBlackJacks == 0) {
            Player[] players = getPlayers();
            ComputerBlackJackPlayer dealer;
            dealer = (ComputerBlackJackPlayer) players[0];
            max = dealer.getHand().handValue();
            BlackJackPlayer bp;
            for(int x = 0; x < numberHumans; x++) {
                bp = (BlackJackPlayer) players[1 + x];
                handValue = bp.getHand().handValue();
                if (handValue > max && !bp.isBust()) {
                    max = handValue;
                    index = x + 1;
                }
            }
        players[index].win();
        }
        return 0;
    }

    public void checkDoubles(BlackJackPlayer bp) {
        Card[] bjh = bp.getHand().getHand();
        if(0 == bjh[0].compareTo(bjh[1])) {
            bp.splittable();
        }
    }
}