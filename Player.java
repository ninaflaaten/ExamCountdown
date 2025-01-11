public class Player extends Entity {


    private int score;

    public Player(int health, int strength) {
        super(health, strength);
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore(int points) {
        this.score += points;
    }
}