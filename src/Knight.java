
public class Knight extends Piece {

    public Knight(int player, int[] loc, int id) {
        super(player, loc, id);
        // TODO Auto-generated constructor stub
    }

    public String getName() {
        return player == 0 ? "\u2658" : "\u265E";
    }

    public boolean pieceRule(int[] dest) {
        int rowDif = dest[0] - loc[0];
        int colDif = dest[1] - loc[1];
        return (Math.abs(rowDif) == 2 && Math.abs(colDif) == 1) || (Math.abs(rowDif) == 1 && Math.abs(colDif) == 2);
    }

    public boolean moveable(Game g, int[] dest) {
        boolean res = false;
        if (pieceRule(dest)) {
            res = true;
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
