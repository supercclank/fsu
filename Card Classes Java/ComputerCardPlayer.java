public class ComputerCardPlayer extends Computer {
    public ComputerCardPlayer(int difficulty, int score, int playerNumber) {
        this.difficulty = difficulty;
        this.setScore(score);
        this.setPlayerNumber(playerNumber);
    }
}