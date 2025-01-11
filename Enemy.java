public class Enemy extends Entity {

    private String type;

    public Enemy(int health, int strength, String type) {
        super(health, strength);
        this.type = type;
    }

    /* Get type of enemy type */
    public String getType() {
        return type;
    }
}