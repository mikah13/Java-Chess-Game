
/**
 * @author mikah
 *
 */
public class Piece {
    protected String name;
    protected int player;
    protected int[] loc;
    protected int id;

    public Piece(int player, int[] loc, int id) {
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

    public boolean pieceRule(int[] dest, int row, int col) {
        return false;
    }

    public boolean capture(Game g, int[] dest) {
        return false;
    }

}
