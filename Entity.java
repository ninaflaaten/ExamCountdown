public abstract class Entity {
    protected int health; // Helse for enheten
    protected int attackPower; // Angrepskraft

    public Entity(int health, int attackPower) {
        this.health = health;
        this.attackPower = attackPower;
    }

    // Hent helse
    public int getHealth() {
        return health;
    }

    // Sjekk om enheten er i live
    public boolean isAlive() {
        return health > 0;
    }

    // Ta skade
    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0; // Helse skal ikke være negativ
        }
    }

    // Angrep: returnerer hvor mye skade denne enheten gjør
    public int attack() {
        return attackPower;
    }

    // Hent angrepskraft
    public int getAttackPower() {
        return attackPower;
    }
}
