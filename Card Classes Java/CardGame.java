package game;

abstract class CardGame{
    private Player[] playerList;
    private boolean over;

    public void setSize(int numPlayers) {
        playerList = new Player[numPlayers];
    }

    public void addPlayer(Player player) {
        playerList[player.getPlayerNumber()] = player;
    }

    abstract void stepGame();

    public boolean isOver() {
        return over;
    }

    public int end() {
        over = true;
        return 0;
    }

    public Player[] getPlayers() {
        return playerList;
    }
}