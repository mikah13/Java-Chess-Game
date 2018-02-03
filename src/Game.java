import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class Game {
    private ChessBoard board;
    private Player p1;
    private Player p2;
    private GridPane gp;
    private int[] dest;
    private Piece selectedPiece;
    private ArrayList<int[]> move;

    public Game() {
        gp = new GridPane();
        p1 = new Player(0);
        p2 = new Player(1);
        move = new ArrayList<int[]>();
        renderGUI();
    }

    public void boardToGrid() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                try {
                    gp.add(board.getBoard()[i][j].draw(), j, i);
                } catch (Exception e) {
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

    public Scene getScene() {
        final int appWidth = 640;
        final int appHeight = 640;
        Scene scene = new Scene(gp, appWidth, appHeight);
        return scene;
    }

    public ArrayList<int[]> getMove() {
        return move;
    }

    public void renderGUI() {
        board = new ChessBoard(this, 8);
        board.add(p1.getPieces());
        board.add(p2.getPieces());
        boardToGrid();

    }

    public void move(int player, int id, int[] loc) {
        this.move.remove(0);
        this.move.remove(0);
        this.getPlayer(player).getChessPieces().movePiece(id, loc);
        renderGUI();
    }

    public void addMove(int[] loc) {
        this.move.add(loc);
    }

}
