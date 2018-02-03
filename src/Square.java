import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/**
 * @author mikah
 *
 */
public class Square {
    private String color;
    private Piece piece;
    private Button btn;
    private int[] loc;
    private Game game;

    public Square(Game game, Piece piece, String color, int[] loc) {
        this.game = game;
        this.loc = loc;
        this.piece = piece;
        this.color = color;
        setName();
    }

    public Piece getPiece() {
        return this.piece;
    }

    public void setPiece(Piece p) {
        this.piece = p;
        setName();
    }

    public void setName() {
        if (this.piece != null) {
            btn = new Button(this.piece.getName());
        } else {
            btn = new Button("");
        }
    }

    public Button draw() throws Exception {
        btn.setStyle("-fx-font:34 arial;-fx-background-color:" + this.color);
        btn.setPrefSize(80, 80);
        btn.setOnAction(this::clickEvent);
        return btn;
    }

    public void clickEvent(ActionEvent event) {
        ArrayList<int[]> move = game.getMove();
        if (move.size() == 0 && this.piece != null) {
            game.addMove(loc);
        }
        if (move.size() == 1 && this.piece == null) {
            game.addMove(loc);
            Piece p = game.getBoard().getSquare(move.get(0)).getPiece();
            int id = p.getID();
            game.move(p.getPlayer(), id, loc);

        }

    }

}
