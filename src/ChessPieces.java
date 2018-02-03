/**
 * 
 */

/**
 * @author mikah
 *
 */
public class ChessPieces {
    private Piece[] set;

    public ChessPieces(int player) {
        int startRow = 0;
        int startCol = 0;
        set = new Piece[16];
        if (player == 1) {
            startRow = 6;
        }
        for (int i = 0; i < 8; i++) {
            int[] loc = { startRow + 1 - player, startCol + i };
            set[i] = new Pawn(player, loc, i);
        }
        set[8] = new Rook(player, new int[] { startRow + player, 0 }, 8);
        set[9] = new Knight(player, new int[] { startRow + player, 1 }, 9);
        set[10] = new Bishop(player, new int[] { startRow + player, 2 }, 10);
        set[11] = new Queen(player, new int[] { startRow + player, 3 }, 11);
        set[12] = new King(player, new int[] { startRow + player, 4 }, 12);
        set[13] = new Bishop(player, new int[] { startRow + player, 5 }, 13);
        set[14] = new Knight(player, new int[] { startRow + player, 6 }, 14);
        set[15] = new Rook(player, new int[] { startRow + player, 7 }, 15);
    }

    public Piece[] getPieces() {
        return this.set;
    }

    public void movePiece(int id, int[] loc) {
        this.set[id].setLoc(loc);
    }

}
