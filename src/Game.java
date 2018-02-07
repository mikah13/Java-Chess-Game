import java.util.ArrayList;

import javafx.scene.layout.GridPane;

public class Game {
    private ChessBoard board;
    private Player p1;
    private Player p2;
    private GridPane gp;
    private ArrayList<int[]> move;
    private int turn;

    public Game() {
        gp = new GridPane();
        newGame();
    }

    public void newGame() {
        turn = 0;
        p1 = new Player(0);
        p2 = new Player(1);
        move = new ArrayList<int[]>();
        board = new ChessBoard(this, 8);
        renderGUI();
    }

    public void boardToGrid() {
        gp.getChildren().clear();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                try {
                    gp.add(board.getBoard()[i][j].draw(), j, i);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }
    }

    public ChessBoard getBoard() {
        return board;
    }

    public Player getPlayer(int player) {
        return player == 0 ? p1 : p2;
    }

    public GridPane getGrid() {
        return gp;
    }

    public int getTurn() {
        return this.turn;
    }

    public ArrayList<int[]> getMove() {
        return move;
    }

    public void renderGUI() {
        board.renderBackground();
        board.add(p1.getPieces());
        board.add(p2.getPieces());
        boardToGrid();
    }

    public void nextTurn() {
        this.move.remove(0);
        this.turn = 1 - turn;
        renderGUI();
    }

    public void invalidMove() {
        this.move.remove(0);
    }

    public void addMove(int[] loc) {
        this.move.add(loc);
    }

    public void checkWin() {
        if (p1.getChessPieces().getPiece(12) == null || p2.getChessPieces().getPiece(12) == null) {
            gameOver();
        }
    }

    public void gameOver() {
        int won = 1;
        if (p2.getChessPieces().getPiece(12) == null) {
            won = 0;
        }
        Screens.endGameScreen(this, gp, won);

    }
    public void promote(Player p, Piece pc) {
        Screens.promoteScreen(this, gp, p, pc);
    }
}
