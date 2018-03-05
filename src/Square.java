import java.io.File;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.media.AudioClip;

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

    public Button getButton() {
        return btn;
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

    // Set style and action for each button.
    public Button draw() throws Exception {
        int p = 0;
        if (piece != null) {
            p = piece.player;
        }
        btn.setStyle("-fx-font:42 arial;-fx-text-fill:" + (p == 0 ? "#b0baab" : "black") + ";-fx-background-color:"
                + this.color);
        btn.setPrefSize(100, 100);
        btn.setOnAction(this::clickEvent);
        return btn;
    }

    // Event handler when promotion occurs
    public void promote(Piece p) {
        if (p.promote() && !game.winCondition()) {
            game.promote(game.getPlayer(p.player), p);
        }
    }

    public void playSuccess() {
        File success = new File("sound/move.wav");
        new AudioClip(success.toURI().toString()).play();
    }

    public void playInvalid() {
        File invalid = new File("sound/invalid.mp3");
        new AudioClip(invalid.toURI().toString()).play();
    }

    public void playCapture() {
        File success = new File("sound/capture.wav");
        new AudioClip(success.toURI().toString()).play();
    }

    // Move the piece if all the condition has been checked
    public void movePiece(Piece p) {
        if (p.makeMove(game, loc, null)) {
            playSuccess();
            p.moved();
            game.nextTurn(loc);
        } else {
            playInvalid();
        }
        game.renderGUI();
        promote(p);
        // }
    }

    // Move then capture the opp's piece.
    public void capturePiece(Piece p) {
        if (!p.makeMove(game, loc, piece)) {
            game.getPlayer(1 - p.player).setPrevSet();
            playInvalid();
        } else {
            playCapture();
            p.moved();
            game.nextTurn(loc);
        }
        game.renderGUI();
        promote(p);
        // }

    }

    // Highlight all the possible moves.
    public void showPossibleMove() {
        ArrayList<int[]> moves = piece.possibleMoves(game);
        for (int[] loca : moves) {
            Square sq = game.getBoard().getSquare(loca);
            Piece p = sq.piece;
            String clr = "#b1bbbc";
            String bgcl = "#7bbded";
            if (p != null) {
                clr = p.getPlayer() == 0 ? "#b0baab" : "black";
                bgcl = "#dd9311";
            }
            sq.btn.setStyle("-fx-font:42 arial;-fx-text-fill:" + clr + ";-fx-background-color:" + bgcl
                    + ";-fx-border-color:black");
        }
    }

    // Highlight the first click on the selected piece
    public void firstClick() {
        int p = 0;
        if (piece != null) {
            p = piece.player;
        }
        showPossibleMove();
        btn.setStyle(
                "-fx-font:42 arial;-fx-text-fill:" + (p == 0 ? "#b0baab" : "black") + ";-fx-background-color:#4aa1e0");
        game.addMove(loc);
    }

    public void castleMove(Piece p) {
        int row = 0;
        if (p.player == 0) {
            row = 7;
        }
        if (p.makeMove(game, loc, null)) {
            ChessPieces cp = game.getPlayer(p.player).getChessPieces();
            if (loc[1] == 2) {
                cp.setPieceLoc(8, new int[] { row, 3 });
            } else {
                cp.setPieceLoc(15, new int[] { row, 5 });
            }
            playSuccess();
            p.moved();
            game.nextTurn(loc);
        }
        game.renderGUI();
        // game.getPlayer(p.player).getChessPieces().setPieceLoc(p.id, loc);

    }

    // Event Handler for the Game
    public boolean clickEvent(ActionEvent event) {
        ArrayList<int[]> move = game.getMove();
        if (move.size() == 0 && piece != null && game.getTurn() == piece.getPlayer()) {
            firstClick();
            return true;
        }
        if (move.size() == 1) {
            Piece p = game.getBoard().getSquare(move.get(0)).piece;
            if (p.moveable(game, loc)) {
                movePiece(p);
                game.checkWin();
            } else if (p.castleMove(game, loc)) {
                castleMove(p);
            } else if (p.capture(game, loc)) {
                capturePiece(p);
                game.checkWin();
            } else {
                game.invalidMove();
                playInvalid();
            }
            return true;
        }
        return true;
    }
}
