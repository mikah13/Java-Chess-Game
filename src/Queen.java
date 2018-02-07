
public class Queen extends Piece {

    public Queen(int player, int[] loc, int id) {
        super(player, loc, id);
        // TODO Auto-generated constructor stub
    }

    public String getName() {
        return player == 0 ? "\u2655" : "\u265B";
    }

    public boolean getPath(Game g, int[] dest) {
        return Path.checkPath(g.getBoard(), loc, dest) || Path.checkDiag(g.getBoard(), loc, dest);
    }

    public boolean pieceRule(int[] dest) {
        int rowDif = dest[0] - loc[0];
        int colDif = dest[1] - loc[1];
        return (dest[0] - loc[0] == 0 || dest[1] - loc[1] == 0) || Math.abs(rowDif) == Math.abs(colDif);
    }

    public boolean moveable(Game g, int[] dest) {
        boolean res = false;
        if (pieceRule(dest)) {
            if (getPath(g, dest)) {
                res = true;
            }
        } else {
            res = false;
        }
        return res;
    }

    public boolean capture(Game g, int[] dest) {
        int opp = g.getBoard().getSquare(dest).getPiece().getPlayer();
        if (moveable(g, dest) && player != opp) {
            return true;
        }
        return false;

    }
}
