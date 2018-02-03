/**
 * 
 */

/**
 * @author mikah
 *
 */
public class Pawn extends Piece {

    public Pawn(int player, int[] loc, int id) {
        super(player, loc, id);
    }

    public String getName() {
        return player == 0 ? "\u2659" : "\u265F";
    }

}
