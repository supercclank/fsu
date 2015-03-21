package game;

// Abstract class representing a Computer Player

abstract class Computer extends Player {
    int difficulty;

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String toString() {
        return super.toString() + " Difficulty:" + difficulty;
    }
}