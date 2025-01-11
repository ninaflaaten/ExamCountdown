import java.util.Random;

public class GameBoard {
    private Tile[][] tiles; // 2D-array som representerer brettet
    private Player player; // Spilleren
    private int size; // Størrelsen på brettet
    private int playerX; // Spillerens nåværende posisjon (x)
    private int playerY; // Spillerens nåværende posisjon (y)

    public GameBoard(int size, Player player) {
        this.size = size;
        this.player = player;
        this.tiles = new Tile[size][size];
        this.playerX = 0; // Startposisjon for spilleren
        this.playerY = 0; // Startposisjon for spilleren
        generateBoard(); // Generer brettets innhold
    }

    // Generer brettet med tilfeldige fiender, skatter og tomme ruter
    private void generateBoard() {
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int rand = random.nextInt(3); // 0: tom, 1: fiende, 2: skatt
                if (rand == 0) {
                    tiles[i][j] = new Tile("empty");
                } else if (rand == 1) {
                    tiles[i][j] = new Tile("enemy");
                    tiles[i][j].setEntity(new Enemy(20, 5, "Goblin"));
                } else {
                    tiles[i][j] = new Tile("item");
                    tiles[i][j].setItem(new Item("Gold", 10));
                }
            }
        }
        // Plasser spilleren på startposisjonen
        tiles[playerX][playerY] = new Tile("player");
        tiles[playerX][playerY].setEntity(player);
    }

    // Hent en spesifikk Tile
    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

    // Flytt spilleren til en ny posisjon
    public void movePlayer(int newX, int newY) {
        // Sjekk om posisjonen er innenfor brettet
        if (newX < 0 || newX >= size || newY < 0 || newY >= size) {
            System.out.println("Ugyldig bevegelse! Du kan ikke gå utenfor brettet.");
            return;
        }

        // Fjern spilleren fra nåværende posisjon
        tiles[playerX][playerY].setEntity(null);
        tiles[playerX][playerY].setType("empty");

        // Oppdater spillerens posisjon
        playerX = newX;
        playerY = newY;

        // Plasser spilleren på den nye posisjonen
        Tile newTile = tiles[playerX][playerY];
        if (newTile.getType().equals("enemy")) {
            System.out.println("Du møtte en fiende!");
        } else if (newTile.getType().equals("item")) {
            System.out.println("Du fant en skatt!");
        }
        newTile.setEntity(player);
        newTile.setType("player");
    }

    // Fjern en fiende fra en Tile
    public void removeEntity(Entity entity) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tiles[i][j].getEntity() == entity) {
                    tiles[i][j].setEntity(null);
                    tiles[i][j].setType("empty");
                    return;
                }
            }
        }
    }

    // Fjern en gjenstand fra en Tile
    public void removeItem(Item item) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tiles[i][j].getItem() == item) {
                    tiles[i][j].setItem(null);
                    tiles[i][j].setType("empty");
                    return;
                }
            }
        }
    }

    // Debug: Skriv ut brettet i konsollen
    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Tile tile = tiles[i][j];
                if (tile.getEntity() instanceof Player) {
                    System.out.print("P ");
                } else if (tile.getEntity() instanceof Enemy) {
                    System.out.print("E ");
                } else if (tile.getItem() != null) {
                    System.out.print("I ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}
