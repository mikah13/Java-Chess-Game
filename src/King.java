
public class King extends Piece {

    /**
     * 
     */
    private static final long serialVersionUID = 327557750730044940L;

    public King(int player, int[] loc, int id) {
        super(player, loc, id);
    }

    // Unicode for the King
    public String getName() {
        return "\u265A";
    }

    // Piece rule for the King, basically move around the square 3x3.
    public boolean pieceRule(int[] dest) {
        int rowDif = dest[0] - loc[0];
        int colDif = dest[1] - loc[1];
        return Math.abs(rowDif) <= 1 && Math.abs(colDif) <= 1;
    }

    // Returns true if the move is valid

    public boolean moveable(Game g, int[] dest) {
        if (g.getBoard().getSquare(dest).getPiece() != null) {
            return false;
        }
        if (pieceRule(dest)) {
            if (checked(g, dest)) {
                return false;
            }
            return true;
        }
        return false;
    }

    // Castling Move.
    public boolean castleMove(Game g, int[] dest) {
        if (Math.abs(dest[1] - loc[1]) == 2 && dest[0] == loc[0] && !moved) {
            int row = 0;
            if (player == 0) {
                row = 7;
            }
            ChessPieces cp = g.getPlayer(player).getChessPieces();
            if (dest[1] == 2) {
                if (Path.checkPath(g.getBoard(), loc, new int[] { row, 0 }) && !cp.hasMoved(8)) {
                    if (checked(g, dest)) {
                        return false;
                    }
                    return true;
                }
            } else {
                if (Path.checkPath(g.getBoard(), loc, new int[] { row, 7 }) && !cp.hasMoved(15)) {
                    if (checked(g, dest)) {
                        return false;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    // Capture rule, returns true if the path is clear and follow the piece Rule
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
