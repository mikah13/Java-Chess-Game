import java.io.Serializable;

public class Player implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
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

    public void undo() {
        cp.undo();
    }
    
    public void setPrevSet() {
        cp.setPrevSet();
    }
}
