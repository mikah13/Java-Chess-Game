
public class Knight extends Piece {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public Knight(int player, int[] loc, int id) {
        super(player, loc, id);
    }

    public String getName() {
        return "\u265E";
    }

    public boolean pieceRule(int[] dest) {
        int rowDif = dest[0] - loc[0];
        int colDif = dest[1] - loc[1];
        return (Math.abs(rowDif) == 2 && Math.abs(colDif) == 1) || (Math.abs(rowDif) == 1 && Math.abs(colDif) == 2);
    }

    public boolean moveable(Game g, int[] dest) {
        if (g.getBoard().getSquare(dest).getPiece() != null) {
            return false;
        }
        if (pieceRule(dest)) {
            return true;
        }
        return false;
    }

    public boolean capture(Game g, int[] dest) {
        Piece opp = g.getBoard().getSquare(dest).getPiece();
        if (opp == null) {
            return false;
        }
        if (pieceRule(dest) && player != opp.player) {
            return true;
        }
        return false;
    }
}
