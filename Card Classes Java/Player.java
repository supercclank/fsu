// Abstract class representing a Player

package game;

abstract class Player {
    // ip - IP address in dotted quad notation?
    private int score;
    private int playerNumber;
    private String ip;
    private boolean won;
    
    public void setScore(int score) {
        this.score = score;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public int getScore() {
        return score;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public String toString() {
        return "IP: "+ ip + " Score: " + score + " PlayerNumber: " + playerNumber;
    }

    
    public void win() {
        won = true;
    }

    public void lose() {
        won = false;
    }

    public boolean isWinner() {
        return won;
    }
}