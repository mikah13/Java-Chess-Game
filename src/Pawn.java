
/**
 * 
 */

/**
 * @author mikah
 *
 */
public class Pawn extends Piece {

    public Pawn(int player, int[] loc, int id) {
        super(player, loc, id);
    }

    public String getName() {
        return player == 0 ? "\u2659" : "\u265F";
    }

    public boolean pieceRule(int[] dest, int row, int col) {
        return dest[0] - loc[0] == row && dest[1] - loc[1] == col;
    }

    public boolean promote() {
        return loc[0] == 0 || loc[0] == 7;
    }

    public boolean moveable(Game g, int[] dest) {
        // only move forward
        // dest must be null
        boolean res = true;
        if (g.getBoard().getSquare(dest).getPiece() != null) {
            res = false;
        }
        // if (dest[0] == 0 || dest[0] == 7) {
        // System.out.println(123);
        // System.out.println(b.getGame().getPlayer(player).getChessPieces().getPiece(id));
        // res = true;
        // }
        if (player == 0 && !pieceRule(dest, -1, 0)) {
            res = false;
        }
        if (player == 0 && loc[0] == 6 && pieceRule(dest, -2, 0)) {
            res = true;
        }
        if (player == 1 && !pieceRule(dest, 1, 0)) {
            res = false;
        }
        if (player == 1 && loc[0] == 1 && (pieceRule(dest, 2, 0))) {
            res = true;
        }

        return res;
    }

    public boolean capture(Game g, int[] dest) {

        if (player == 0 && (pieceRule(dest, -1, -1) || pieceRule(dest, -1, 1))) {
            return true;
        }
        if (player == 1 && (pieceRule(dest, 1, -1) || pieceRule(dest, 1, 1))) {
            return true;
        }

        return false;

    }
}
