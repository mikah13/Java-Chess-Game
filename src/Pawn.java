
public class Pawn extends Piece {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public Pawn(int player, int[] loc, int id) {
        super(player, loc, id);
    }

    // Unicode for Pawn
    public String getName() {
        return "\u265F";
    }

    // Piece rule for Pawn, only move forward
    public boolean pieceRule(int[] dest, int row, int col) {
        return dest[0] - loc[0] == row && dest[1] - loc[1] == col;
    }
    
    // Promotion check
    public boolean promote() {
        return loc[0] == 0 || loc[0] == 7;
    }
    
    // Returns true if the path is clear
    public boolean moveable(Game g, int[] dest) {
        boolean res = true;
        if (g.getBoard().getSquare(dest).getPiece() != null) {
            return false;
        }
        if (player == 0 && !pieceRule(dest, -1, 0)) {
            res = false;
        }
        if (player == 0 && loc[0] == 6 && pieceRule(dest, -2, 0) && Path.checkPath(g.getBoard(), loc, dest)) {
            res = true;
        }
        if (player == 1 && !pieceRule(dest, 1, 0)) {
            res = false;
        }
        if (player == 1 && loc[0] == 1 && pieceRule(dest, 2, 0) && Path.checkPath(g.getBoard(), loc, dest)) {
            res = true;
        }
        return res;
    }

    // Capture move
    public boolean capture(Game g, int[] dest) {
        Piece p = g.getBoard().getSquare(dest).getPiece();
        if (p == null) {
            return false;
        }
        if (p.player == 1 && player == 0 && (pieceRule(dest, -1, -1) || pieceRule(dest, -1, 1))) {
            return true;
        }
        if (p.player == 0 && player == 1 && (pieceRule(dest, 1, -1) || pieceRule(dest, 1, 1))) {
            return true;
        }
        return false;

    }
}
