import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;

/**
 * @author mikah
 *
 */
public class GUI extends Application {
    private GridPane gp;
    private Game game = new Game();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chess Game");
        primaryStage.setScene(game.getScene());
        primaryStage.show();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        launch(args);

    }
}
