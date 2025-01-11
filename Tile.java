public class Tile {
    private String type; // Kan være "empty", "player", "enemy", "item"
    private Entity entity; // Referanse til en fiende eller spiller
    private Item item; // Referanse til en gjenstand (f.eks. skatt)

    public Tile(String type) {
        this.type = type;
        this.entity = null;
        this.item = null;
    }

    // Hent type
    public String getType() {
        return type;
    }

    // Sett type
    public void setType(String type) {
        this.type = type;
    }

    // Hent entity (spiller eller fiende)
    public Entity getEntity() {
        return entity;
    }

    // Sett entity
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    // Hent item
    public Item getItem() {
        return item;
    }

    // Sett item
    public void setItem(Item item) {
        this.item = item;
    }
}
