
public class King extends Piece {

    public King(int player, int[] loc, int id) {
        super(player, loc, id);
        // TODO Auto-generated constructor stub
    }

    public String getName() {
        return player == 0 ? "\u2654" : "\u265A";
    }
}
