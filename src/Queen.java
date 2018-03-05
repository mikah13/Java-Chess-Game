
public class Queen extends Piece {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public Queen(int player, int[] loc, int id) {
        super(player, loc, id);
    }

    public String getName() {
        return  "\u265B";
    }

    // Get path for horizontal, vertical and diagonal move.
    public boolean getPath(Game g, int[] dest) {
        int rowDif = Math.abs(dest[0] - loc[0]);
        int colDif = Math.abs(dest[1] - loc[1]);
        if (rowDif == 0 || colDif == 0) {
            return Path.checkPath(g.getBoard(), loc, dest);
        } else if (rowDif == colDif) {
            return Path.checkDiag(g.getBoard(), loc, dest);
        }
        return false;
    }
    
    // Piece rule returns true if follow the horizontal, vertical and diagonal move.
    public boolean pieceRule(int[] dest) {
        int rowDif = Math.abs(dest[0] - loc[0]);
        int colDif = Math.abs(dest[1] - loc[1]);
        return rowDif == 0 || colDif == 0 || rowDif == colDif;
    }

    public boolean moveable(Game g, int[] dest) {
        if (g.getBoard().getSquare(dest).getPiece() != null) {
            return false;
        }
        if (pieceRule(dest) && getPath(g, dest)) {
            return true;
        }
        return false;

    }

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
