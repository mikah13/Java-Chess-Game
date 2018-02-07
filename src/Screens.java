import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Screens {
    public static void endGameScreen(Game g, GridPane gp, int won) {
        gp.getChildren().clear();
        String player = won == 0 ? "Player 1" : "Player 2";
        Label msg = new Label(player + " won!");
        Button restart = new Button("Play Again");
        gp.setStyle(
                "-fx-padding:300 230 100;-fx-background-image: url('https://raw.githubusercontent.com/mikah13/Java-Chess-Game/master/endgame.jpg?token=AYsO-LmO49dXyMvDqKBWFzRZ_cH30GyZks5ahBDNwA%3D%3D')");
        msg.setStyle("-fx-font:55 arial");
        msg.setTextFill(Color.web("white"));
        restart.setPrefSize(200, 80);
        restart.setFont(Font.font(27));
        gp.add(msg, 0, 0);
        gp.add(restart, 0, 1);
        gp.setVgap(30);
        GridPane.setHalignment(msg, HPos.CENTER);
        GridPane.setHalignment(restart, HPos.CENTER);
        restart.setOnAction((event) -> {
            gp.setVgap(0);
            gp.setStyle("-fx-padding:0 0 0 0 ");
            g.newGame();
        });
        restart.setDefaultButton(true);
    }

    public static void promoteScreen(Game g, GridPane gp, Player p, Piece pc) {
        gp.getChildren().clear();
        Rook r = new Rook(pc.getPlayer(), pc.loc, pc.id);
        Knight k = new Knight(pc.getPlayer(), pc.loc, pc.id);
        Bishop b = new Bishop(pc.getPlayer(), pc.loc, pc.id);
        Queen q = new Queen(pc.getPlayer(), pc.loc, pc.id);
        createNewPiece(g, gp, p, pc, r, 0);
        createNewPiece(g, gp, p, pc, k, 1);
        createNewPiece(g, gp, p, pc, b, 2);
        createNewPiece(g, gp, p, pc, q, 3);
    }

    public static void createNewPiece(Game g, GridPane gp, Player p, Piece pc, Piece newPiece, int pos) {
        Button btn = new Button(newPiece.getName());
        gp.add(btn, pos, 0);
        btn.setOnAction((event) -> {
            p.getChessPieces().promotePiece(pc.id, newPiece);
            g.renderGUI();
        });
    }
}
