
public class Player {
    private ChessPieces cp;
    private int player;

    public Player(int player) {
        this.player = player;
        cp = new ChessPieces(player);

    }

    public Piece[] getPieces() {
        return cp.getPieces();
    }

    public ChessPieces getChessPieces() {
        return this.cp;
    }

    public int getPlayer() {
        return this.player;
    }
}
