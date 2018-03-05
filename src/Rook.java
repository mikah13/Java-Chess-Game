
public class Rook extends Piece {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public Rook(int player, int[] loc, int id) {
        super(player, loc, id);
    }

    public String getName() {
        return  "\u265C";
    }

    public boolean getPath(Game g, int[] dest) {
        return Path.checkPath(g.getBoard(), loc, dest);
    }

    public boolean pieceRule(int[] dest) {
        return dest[0] - loc[0] == 0 || dest[1] - loc[1] == 0;
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
