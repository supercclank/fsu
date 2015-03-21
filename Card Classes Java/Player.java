abstract class Player {
    private int score;
    private int playerNumber;
    private String ip;

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

}