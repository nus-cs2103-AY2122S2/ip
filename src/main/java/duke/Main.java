package duke;

import java.io.IOException;
import java.util.Objects;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private static final String TITLE = "Duke";
    private static final String APPLICATION_ICON = "/images/Icon.png";
    private static final String BACKGROUND_IMAGE = "/images/Background.png";

    private static final double MIN_HEIGHT = 600.0;
    private static final double MIN_WIDTH = 400.0;

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();

            //modify window
            stage.setTitle(TITLE);
            stage.setResizable(false);
            stage.setMinHeight(MIN_HEIGHT);
            stage.setMinWidth(MIN_WIDTH);
            Image icon = new Image(
                    Objects.requireNonNull(this.getClass().getResourceAsStream(APPLICATION_ICON)));
            stage.getIcons().add(icon);

            //set background
            Image img = new Image(
                    Objects.requireNonNull(this.getClass().getResourceAsStream(BACKGROUND_IMAGE)));
            BackgroundImage bImg = new BackgroundImage(img,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    new BackgroundSize(1.0, 1, true, true, false, false));
            Background bGround = new Background(bImg);
            ap.setBackground(bGround);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
