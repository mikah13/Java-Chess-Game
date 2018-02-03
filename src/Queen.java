
public class Queen extends Piece {

    public Queen(int player, int[] loc, int id) {
        super(player, loc, id);
        // TODO Auto-generated constructor stub
    }

    public String getName() {
        return player == 0 ? "\u2655" : "\u265B";
    }
}
