
public class Rook extends Piece {
    public Rook(int player, int[] loc, int id) {
        super(player, loc, id);
        // TODO Auto-generated constructor stub
    }

    public String getName() {
        return player == 0 ? "\u2656" : "\u265C";
    }
}
