/**
 * @author mikah
 *
 */
public class ChessBoard extends Board {
    public ChessBoard(Game game, int size) {
        super(game, size);
    }

    public void renderBackground() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.board[i][j] = new Square(game, null, (i + j) % 2 == 0 ? "#eff2d5" : "#5c8e83", new int[] { i, j });
            }
        }
    }

    public void setSquare(Piece p, int[] loc) {
        this.board[loc[0]][loc[1]].setPiece(p);
    }

    public Square getSquare(int[] loc) {

        return this.board[loc[0]][loc[1]];
    }

    public void add(Piece[] p) {
        for (int i = 0; i < p.length; i++) {
            this.setSquare(p[i], p[i].getLoc());
        }
    }

    public Square[][] getBoard() {
        return this.board;
    }

    public Game getGame() {
        return this.game;
    }
}
