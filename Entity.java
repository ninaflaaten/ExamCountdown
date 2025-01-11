/*
 * Represents an entity in the game. An entity has:
 *  health   (number of lives)
 *  strenght (strength level)
 * One can read the health and strength of an entity with the following getter methods:
 *  getHealth() and getStrength()
 * 
 */
public class Entity {

    int health;
    int strength;

    public Entity(int health, int strength) {
        this.health = health;
        this.strength = strength;

    }

    /* Get entities health */
    public int getHealth() {
        return health;
    }

    /* Get entities strenght */
    public int getStrength() {
        return strength;
    }

    /* Remove wanted number of lives from enity */
    public void takeLife(int life) {
        health = health - life;
        if (health < 0) health = 0;
    }

    /* Add wanted number of lives to entity */
    public void addLife(int life) {
        health = health + life;
    }

    /* Check if entity is alive */
    public boolean isAlive() {
        if (health < 1 ) {
            return false;
        }
        else {
            return true;
        }
    }
    
}
