/**
 * 
 */

/**
 * @author mikah
 *
 */
abstract class Board {
    protected Square[][] board;
    protected int size;
    protected Game game;

    public Board(final Game game,final int size) {
        this.game = game;
        this.size = size;
        this.board = new Square[size][size];
    }

}
