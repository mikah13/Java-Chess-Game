
import java.io.Serializable;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author mikah
 *
 */
public class GUI extends Application implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Game game = new Game();

    @Override
    public void start(Stage primaryStage) {
        final int appWidth = 920;
        final int appHeight = 800;
        Scene scene = new Scene(game.getGrid(), appWidth, appHeight);
        primaryStage.setTitle("Chess Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
