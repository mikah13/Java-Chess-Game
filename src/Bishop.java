
public class Bishop extends Piece {

    public Bishop(int player, int[] loc, int id) {
        super(player, loc, id);
        // TODO Auto-generated constructor stub
    }

    public String getName() {
        return player == 0 ? "\u2657" : "\u265D";
    }
}
