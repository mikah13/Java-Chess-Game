

public class ChessBoard extends Board {
    public ChessBoard(Game game, int size) {
        super(game, size);
    }

    // Render the Background with all the squares having no Pieces
    public void renderBackground() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.board[i][j] = new Square(game, null, (i + j) % 2 == 0 ? "white" : "#21818c", new int[] { i, j });
            }
        }
    }

    // Set the Piece of the square
    public void setSquare(Piece p, int[] loc) {

        this.board[loc[0]][loc[1]].setPiece(p);
    }

    // Get the piece of the square
    public Square getSquare(int[] loc) {
        return this.board[loc[0]][loc[1]];
    }

    // Make the color of the square back to two-tone after highlighting the possible
    // moves and the selected piece.
    public void normalizeColor() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                try {
                    this.board[i][j].draw();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Add all the Pieces from each player's set to the Board.
    public void add(Piece[] p) {
        for (int i = 0; i < p.length; i++) {
            this.setSquare(p[i], p[i].getLoc());
        }
    }

    // Get this board
    public Square[][] getBoard() {
        return this.board;
    }

}
