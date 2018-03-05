import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Game implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 
     */

    private transient ChessBoard board = new ChessBoard(this, 8);
    private Player p1;
    private Player p2;
    private transient GridPane gp;
    private ArrayList<int[]> move;
    private int turn;
    public final static int SIZE = 8;

    public Game() {
        gp = new GridPane();
        Screens.startMenu(this, gp);
    }

    // Create a new game.
    public void newGame() {
        turn = 0;
        p1 = new Player(0);
        p2 = new Player(1);
        move = new ArrayList<int[]>();
        board = new ChessBoard(this, 8);
        renderGUI();
    }

    // Render GUI with the board.
    public void boardToGrid() {
        gp.getChildren().clear();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                try {
                    gp.add(board.getBoard()[i][j].draw(), j, i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void s() {
        Button saveGame = new Button("Save Game");
        Button menu = new Button("Menu");
        gp.add(menu, SIZE + 1, 0);
        gp.add(saveGame, SIZE + 1, 1);
        menu.setOnMouseClicked(this::openMenu);
        saveGame.setOnMouseClicked(this::saveGame);
    }

    public void openMenu(Event event) {
        Screens.startMenu(this, gp);
    }

    public void saveGame(Event event) {
        try {
            File f = new File("chess.x");
            FileOutputStream fileOut = new FileOutputStream(f);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    // Get this board.
    public ChessBoard getBoard() {
        return board;
    }

    // Get this player from player ID
    public Player getPlayer(int player) {
        return player == 0 ? p1 : p2;
    }

    // Get this GridPane
    public GridPane getGrid() {
        return gp;
    }

    // Get the turn
    public int getTurn() {
        return this.turn;
    }

    // Get the current piece moving
    public ArrayList<int[]> getMove() {
        return move;
    }

    // Render GUI
    public void renderGUI() {
        board.renderBackground();
        board.add(p1.getPieces());
        board.add(p2.getPieces());
        boardToGrid();
        Screens.sideMenu(this, gp);
    }

    // Next turn
    public void nextTurn(int[] newLoc) {
        this.move.remove(0);
        this.turn = 1 - turn;
        renderGUI();
    }

    // Reset if previous move is invalid
    public void invalidMove() {
        this.move.remove(0);
        board.normalizeColor();
    }

    // Add a move
    public void addMove(int[] loc) {
        this.move.add(loc);
    }

    // Win condition, true if one of the king is captured
    public boolean winCondition() {
        return p1.getChessPieces().getPiece(12) == null || p2.getChessPieces().getPiece(12) == null;
    }

    public int noValidMove() {
        if (checked() == 0) {
            for (Piece el : p1.getPieces()) {
                if (!el.noValidMove(this)) {
                    return -1;
                }
            }
            return 1;
        } else if (checked() == 1) {
            for (Piece el : p2.getPieces()) {
                if (!el.noValidMove(this)) {
                    return -1;
                }
            }
            return 0;
        }
        return -1;
    }

    // if wincondition returns true, the execute game Over
    public void checkWin() {
        if (noValidMove() != -1) {
            gameOver();
        }

    }

    public int checked() {
        Piece k1 = p1.getChessPieces().getPiece(12);
        Piece k2 = p2.getChessPieces().getPiece(12);
        if (k1.checked(this, k1.loc)) {
            return 0;
        } else if (k2.checked(this, k2.loc)) {
            return 1;
        }
        return -1;
    }

    // Render the screen for Game Over
    public void gameOver() {
        int won = 1;
        if (noValidMove() == 0) {
            won = 0;
        }
        Screens.endGameScreen(this, gp, won);
    }

    // Render the screeen for the Pawn Promotion
    public void promote(Player p, Piece pc) {
        Screens.promoteScreen(this, gp, p, pc);
    }

    public void setPlayer(Player p, int i) {
        if (i == 0) {
            p1 = p;
        } else if (i == 1) {
            p2 = p;
        }
    }

    public void setMove(ArrayList<int[]> m) {
        this.move = m;
    }

    public void setTurn(int t) {
        this.turn = t;
    }
}
