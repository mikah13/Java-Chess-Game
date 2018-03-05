
public class Bishop extends Piece {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public Bishop(int player, int[] loc, int id) {
        super(player, loc, id);
    }

    // Unicode for Bishop
    public String getName() {
        return "\u265D";
    }

    // check the diagonal path.
    public boolean getPath(Game g, int[] dest) {
        return Path.checkDiag(g.getBoard(), loc, dest);
    }

    // Piece rule of the bishop to check if the path is diagonal
    public boolean pieceRule(int[] dest) {
        int rowDif = Math.abs(dest[0] - loc[0]);
        int colDif = Math.abs(dest[1] - loc[1]);
        return rowDif == colDif;
    }

    // Return true if the move follows the rule and the path is clear
    public boolean moveable(Game g, int[] dest) {
        Piece opp = g.getBoard().getSquare(dest).getPiece();
        if (opp != null) {
            return false;
        }
        if (pieceRule(dest) && getPath(g, dest)) {
            return true;
        }
        return false;

    }

    // Return true if the moveable returns true and the destination is an opp's
    // piece.
    public boolean capture(Game g, int[] dest) {
        Piece opp = g.getBoard().getSquare(dest).getPiece();
        if (opp == null) {
            return false;
        }
        if (pieceRule(dest) && getPath(g, dest) && player != opp.player) {
            return true;
        }
        return false;

    }

}
