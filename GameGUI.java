import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI extends JFrame {
    private final int BOARD_SIZE = 15; // Størrelse på spillbrettet
    private JButton[][] buttons; // Knappene som representerer spillbrettet
    private GameBoard gameBoard; // Logikken for brettet
    private Player player; // Spilleren

    public GameGUI() {
        // Initialiser spilleren og brettet
        player = new Player(100, 10); // Helse: 100, Styrke: 10
        gameBoard = new GameBoard(BOARD_SIZE, player);

        // Opprett GUI-komponentene
        buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
        setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        initializeBoard();

        // Oppsett for vinduet
        setTitle("Rollespill med GUI");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Initialiser brettet med knapper
    private void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                JButton button = new JButton();
                button.setFocusable(false);
                button.setFont(new Font("Arial", Font.PLAIN, 18));
                button.setBackground(Color.LIGHT_GRAY);

                // Legg til knappene i rutenettet
                buttons[i][j] = button;
                add(button);

                // Klikkbar interaksjon for testing
                int x = i;
                int y = j;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleTileClick(x, y);
                    }
                });
            }
        }
        updateBoard(); // Oppdater brettet for første gang
    }

    // Oppdater GUI for å vise gjeldende status på brettet
    private void updateBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Tile tile = gameBoard.getTile(i, j);
                JButton button = buttons[i][j];

                // Oppdater knappens tekst basert på type
                if (tile.getEntity() instanceof Player) {
                    button.setText("P"); // Spiller
                    button.setBackground(Color.GREEN);
                } else if (tile.getEntity() instanceof Enemy) {
                    button.setText("E"); // Fiende
                    button.setBackground(Color.RED);
                } else if (tile.getItem() != null) {
                    button.setText("I"); // Gjenstand
                    button.setBackground(Color.YELLOW);
                } else {
                    button.setText(""); // Tom rute
                    button.setBackground(Color.LIGHT_GRAY);
                }
            }
        }
    }

    // Håndterer klikk på en rute
    private void handleTileClick(int x, int y) {
        Tile tile = gameBoard.getTile(x, y);
        if (tile.getEntity() instanceof Enemy) {
            JOptionPane.showMessageDialog(this, "Du møtte en fiende!");
            fightEnemy((Enemy) tile.getEntity());
        } else if (tile.getItem() != null) {
            JOptionPane.showMessageDialog(this, "Du fant en skatt!");
            collectItem(tile.getItem());
        } else {
            JOptionPane.showMessageDialog(this, "Tom rute.");
        }
        updateBoard();
    }

    private void fightEnemy(Enemy enemy) {
        while (player.isAlive() && enemy.isAlive()) {
            enemy.takeDamage(player.attack());
            player.takeDamage(enemy.attack());
        }
        if (!player.isAlive()) {
            JOptionPane.showMessageDialog(this, "Du tapte!");
            System.exit(0); // Avslutt spillet
        } else if (!enemy.isAlive()) {
            JOptionPane.showMessageDialog(this, "Du vant kampen!");
            gameBoard.removeEntity(enemy);
        }
    }

    private void collectItem(Item item) {
        player.increaseScore(item.getValue());
        JOptionPane.showMessageDialog(this, "Score: " + player.getScore());
        gameBoard.removeItem(item);
    }

    public static void main(String[] args) {
        new GameGUI();
    }
}
