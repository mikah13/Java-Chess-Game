
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.application.Platform;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Screens {
    public static void sideMenu(Game g, GridPane gp) {
        gp.setStyle("-fx-background-color:#558de8");
        Button saveGame = new Button("Save");
        Button menu = new Button("Menu");
        saveGame.setPrefSize(100, 50);
        saveGame.setStyle("-fx-font:25 arial");
        GridPane.setMargin(saveGame, new Insets(50, 0, 0, 10));
        menu.setPrefSize(100, 50);
        menu.setStyle("-fx-font:25 arial");
        GridPane.setMargin(menu, new Insets(50, 0, 0, 10));
        gp.add(menu, Game.SIZE + 1, 0);
        gp.add(saveGame, Game.SIZE + 1, 1);
        menu.setOnMouseClicked(e -> {
            Screens.startMenu(g, gp);
        });
        saveGame.setOnMouseClicked(e -> {
            try {
                File f = new File("chess.x");
                FileOutputStream fileOut = new FileOutputStream(f);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(g);
                out.close();
                fileOut.close();
            } catch (IOException i) {
                i.printStackTrace();
            }
        });
    }

    public static void startMenu(Game g, GridPane gp) {
        gp.getChildren().clear();

        Button newGame = new Button("New Game");
        Button loadGame = new Button("Load Game");
        Button exit = new Button("Exit");
        Label title = new Label("Chess Game");
        title.setStyle("-fx-font:70 arial");

        styleBtn(newGame);
        styleBtn(loadGame);
        styleBtn(exit);
        gp.add(title, 0, 0);
        gp.add(newGame, 0, 1);
        gp.add(loadGame, 0, 2);
        gp.add(exit, 0, 3);
        gp.setVgap(20);
        gp.setStyle("-fx-padding:200 20 0 250;-fx-background-color:#558de8");
        newGame.setOnMouseClicked((event) -> {
            gp.setVgap(0);
            gp.setStyle("-fx-padding:0 0 0 0");
            g.newGame();
        });
        loadGame.setOnMouseClicked((event) -> {
            loadGameEvent(g, gp);
        });
        exit.setOnMouseClicked((event) -> {
            Platform.exit();
        });
    }

    public static void loadGameEvent(Game g, GridPane gp) {
        gp.setVgap(0);
        gp.setStyle("-fx-padding:0 0 0 0");
        Game X = null;
        try {
            FileInputStream fileIn = new FileInputStream("chess.x");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            X = (Game) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            Label msg = new Label("File Not Found");
            gp.add(msg, 0, 4);
            System.out.println(i);
            return;
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
            return;
        }
        g.setPlayer(X.getPlayer(1), 1);
        g.setPlayer(X.getPlayer(0), 0);
        g.setMove(X.getMove());
        g.setTurn(X.getTurn());
        g.renderGUI();
    }

    public static void styleBtn(Button btn) {
        btn.setStyle("-fx-font:40 arial;-fx-text-fill:blue");
        btn.setPrefSize(400, 100);

    }

    // Render the screen for the game end.
    public static void endGameScreen(Game g, GridPane gp, int won) {
        gp.getChildren().clear();
        String player = won == 0 ? "Player 1" : "Player 2";
        Image img = new Image("endgame.jpg");
        gp.getChildren().add(new ImageView(img));
        Label msg = new Label("\t" + player + " won!\n CLICK HERE to play again");
        msg.setStyle("-fx-font:50 arial;-fx-text-fill:blue");
        gp.add(msg, 0, 0);
        GridPane.setHalignment(msg, HPos.CENTER);
        File a = new File("sound/game_over.wav");
        AudioClip x = new AudioClip(a.toURI().toString());
        x.play();
        msg.setOnMouseClicked((event) -> {
            x.stop();
            gp.setVgap(0);
            g.newGame();
        });

    }

    // Render the screen for the promotion move
    public static void promoteScreen(Game g, GridPane gp, Player p, Piece pc) {
        gp.getChildren().clear();
        Rook r = new Rook(pc.getPlayer(), pc.loc, pc.id);
        Knight k = new Knight(pc.getPlayer(), pc.loc, pc.id);
        Bishop b = new Bishop(pc.getPlayer(), pc.loc, pc.id);
        Queen q = new Queen(pc.getPlayer(), pc.loc, pc.id);
        Text title = new Text("Pick a new piece:");
        title.setStyle("-fx-font:40 arial");
        title.setTextAlignment(TextAlignment.CENTER);
        gp.add(title, 0, 0, 4, 1);
        gp.setVgap(30);
        gp.setHgap(20);
        gp.setStyle("-fx-padding:200 150 80 80");
        createNewPiece(g, gp, p, pc, r, 0);
        createNewPiece(g, gp, p, pc, k, 1);
        createNewPiece(g, gp, p, pc, b, 2);
        createNewPiece(g, gp, p, pc, q, 3);
    }

    // Render the button for piece to be promoted
    public static void createNewPiece(Game g, GridPane gp, Player p, Piece pc, Piece newPiece, int pos) {
        Button btn = new Button(newPiece.getName());
        btn.setPrefSize(150, 150);
        btn.setStyle("-fx-font:50 arial;-fx-text-fill:" + (p.getPlayer() == 0 ? "b0baab" : "black"));
        gp.add(btn, pos, 1);
        btn.setOnAction((event) -> {
            gp.setStyle("-fx-padding:0 0 0 0");
            gp.setVgap(0);
            gp.setHgap(0);
            p.getChessPieces().promotePiece(pc.id, newPiece);
            g.renderGUI();
        });
    }
}
