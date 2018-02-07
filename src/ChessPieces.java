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
        if (player == 0) {
            startRow = 6;
        }
        for (int i = 0; i < 8; i++) {
            int[] loc = { startRow + player, startCol + i };
            set[i] = new Pawn(player, loc, i);
        }
        set[8] = new Rook(player, new int[] { startRow - player + 1, 0 }, 8);
        set[9] = new Knight(player, new int[] { startRow - player + 1, 1 }, 9);
        set[10] = new Bishop(player, new int[] { startRow - player + 1, 2 }, 10);
        set[11] = new Queen(player, new int[] { startRow - player + 1, 3 }, 11);
        set[12] = new King(player, new int[] { startRow - player + 1, 4 }, 12);
        set[13] = new Bishop(player, new int[] { startRow - player + 1, 5 }, 13);
        set[14] = new Knight(player, new int[] { startRow - player + 1, 6 }, 14);
        set[15] = new Rook(player, new int[] { startRow - player + 1, 7 }, 15);
    }

    public void promotePiece(int id, Piece newPc) {
        for (int i = 0; i < set.length; i++) {
            if (set[i].getID() == id) {
                set[i] = newPc;
            }
        }
    }

    public Piece getPiece(int id) {
        for (int i = 0; i < set.length; i++) {
            if (set[i].getID() == id) {
                return set[i];
            }
        }
        return null;
    }

    public Piece[] getPieces() {
        return this.set;
    }

    public void removePiece(int id) {
        int len = set.length;
        Piece[] newSet = new Piece[len - 1];
        int i = 0;
        for (Piece p : set) {
            if (p.getID() != id) {
                newSet[i] = p;
                i++;
            }
        }
        set = new Piece[len - 1];
        set = newSet;

    }

}
