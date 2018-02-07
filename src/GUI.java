
import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author mikah
 *
 */
public class GUI extends Application {
    private Game game = new Game();

    @Override
    public void start(Stage primaryStage) {
        final int appWidth = 800;
        final int appHeight = 800;
        Scene scene = new Scene(game.getGrid(), appWidth, appHeight);
        Image image = new Image(
                "https://raw.githubusercontent.com/mikah13/Java-Chess-Game/master/cursor.png?token=AYsO-Dst3TpYDLcHgTuRP8P8v8sZXNMUks5ahBKmwA%3D%3D"); // pass
                                                                                                                                                       // in
                                                                                                                                                       // the
                                                                                                                                                       // image
                                                                                                                                                       // path
        scene.setCursor(new ImageCursor(image));
        primaryStage.setTitle("Chess Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
