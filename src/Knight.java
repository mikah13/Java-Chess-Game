
public class Knight extends Piece {

    public Knight(int player, int[] loc, int id) {
        super(player, loc, id);
        // TODO Auto-generated constructor stub
    }

    public String getName() {
        return player == 0 ? "\u2658" : "\u265E";
    }
}
