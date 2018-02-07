import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/**
 * @author mikah
 *
 */
public class Square extends Button {
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
        btn.setStyle("-fx-font:42 arial ;-fx-background-color:" + this.color);
        btn.setPrefSize(100, 100);
        btn.setOnAction(this::clickEvent);
        // btn.setTextFill(Color.RED);

        return btn;
    }
    public void promote(Piece p) {
        if (p.promote()) {
            game.promote(game.getPlayer(p.player), p);
        }
    }
    public void movePiece(Piece p) {
        p.setLoc(loc);
        game.nextTurn();
        promote(p);
    }

    public void capturePiece(Piece p) {
        int id = piece.getID();
        int player = piece.getPlayer();
        game.getPlayer(player).getChessPieces().removePiece(id);
        p.setLoc(loc);
        game.nextTurn();
        game.checkWin();
        promote(p);
    }

    public void invalidMove(Piece p) {
        game.invalidMove();
        Square prevSquare = game.getBoard().getSquare(p.getLoc());
        int pieceLoc = p.getLoc()[0] + p.getLoc()[1];
        prevSquare.btn
                .setStyle("-fx-font:42 arial;-fx-background-color:" + (pieceLoc % 2 == 0 ? "#eff2d5" : "#5c8e83"));
    }

    public boolean clickEvent(ActionEvent event) {
        ArrayList<int[]> move = game.getMove();
        if (move.size() == 0 && piece != null && game.getTurn() == piece.getPlayer()) {
            btn.setStyle("-fx-font:42 arial;-fx-background-color:#827d78");
            game.addMove(loc);
            return true;
        }
        if (move.size() == 1) {
            Piece p = game.getBoard().getSquare(move.get(0)).piece;
            if (piece == null && p.moveable(game, loc)) {
                movePiece(p);
               
            } else if (piece != null && p.capture(game, loc)) {
                capturePiece(p);
                if (p.promote()) {
                    game.promote(game.getPlayer(p.player), p);
                }
            } else {
                invalidMove(p);
            }
            return true;
        }
        return true;
    }

}
