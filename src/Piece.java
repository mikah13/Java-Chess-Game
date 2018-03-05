import java.io.Serializable;
import java.util.ArrayList;

public class Piece implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    protected String name;
    protected int player;
    protected int[] loc;
    protected int id;
    protected boolean moved;

    public Piece(int player, int[] loc, int id) {
        moved = false;
        this.player = player;
        this.loc = loc;
        this.id = id;
    }

    public int getID() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public int getPlayer() {
        return player;
    }

    public void setLoc(int[] loc) {
        this.loc = loc;
    }

    public int[] getLoc() {
        return loc;
    }

    public void dead() {
        this.player = -1;
        this.loc = null;
        this.id = -1;
        this.name = null;
    }

    public boolean promote() {
        return false;
    }

    public boolean moveable(Game g, int[] dest) {
        return false;
    }

    // Check all the possible moves and add them to an arraylist.
    public ArrayList<int[]> possibleMoves(Game g) {
        ArrayList<int[]> list = new ArrayList<int[]>();
        for (int i = 0; i < Game.SIZE; i++) {
            for (int j = 0; j < Game.SIZE; j++) {
                int[] moveLoc = new int[] { i, j };
                if (moveable(g, moveLoc) || capture(g, moveLoc) || castleMove(g, moveLoc)) {
                    list.add(moveLoc);
                }
            }
        }
        return list;
    }

    public boolean pieceRule(int[] dest, int row, int col) {
        return false;
    }

    public boolean capture(Game g, int[] dest) {
        return false;
    }

    public void moved() {
        this.moved = true;
    }

    public boolean castleMove(Game g, int[] dest) {
        return false;
    }

    public boolean checked(Game g, int[] kingLoc) {
        boolean checked = false;
        ChessPieces cp = g.getPlayer(1 - player).getChessPieces();
        for (int i = 0; i < cp.getPieces().length; i++) {
            if (cp.getPieces()[i].capture(g, kingLoc)) {
                checked = true;
            }
        }
        return checked;
    }

    //
    public boolean makeMove(Game g, int[] dest, Piece p) {
        setPieceLoc(g, dest);
        if (p != null) {
            int id = p.getID();
            int player = p.getPlayer();
            g.getPlayer(player).getChessPieces().removePiece(id);
        }
        if (g.checked() == player) {
            undo(g);
            return false;
        }

        return true;
    }

    public void setPieceLoc(Game g, int[] dest) {
        g.getPlayer(player).getChessPieces().setPieceLoc(id, dest);
        g.renderGUI();
    }

    public void undo(Game g) {
        g.getPlayer(player).undo();
        g.invalidMove();
    }

    public boolean noValidMove(Game g) {
        int count = 0;
        ArrayList<int[]> list = possibleMoves(g);
        for (int[] m : list) {
            Piece p = g.getBoard().getSquare(m).getPiece();
            if (p == null) {
                setPieceLoc(g, m);
                if (g.checked() != player) {
                    count++;
                }
                g.getPlayer(player).undo();
                g.renderGUI();
            } else {
                int pcId = p.id;
                if (pcId == id && id == 12) {
                    return true;
                } else {
                    g.getPlayer(1 - player).getChessPieces().removePiece(pcId);
                    setPieceLoc(g, m);
                    if (g.checked() != player) {
                        count++;
                    }
                    g.getPlayer(player).undo();
                    g.getPlayer(1 - player).setPrevSet();
                    g.renderGUI();
                }
            }
        }
        return count == 0;
    }
}
