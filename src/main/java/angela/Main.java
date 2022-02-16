package angela;

import java.io.IOException;
import java.net.URL;

import angela.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private static final String DIRECTORY = "data";
    private static final String FILE_NAME = "Duke.txt";
    private static final String PATH = DIRECTORY + "/" + FILE_NAME;
    private static final String RESOURCE_PATH = "/view/MainWindow.fxml";
    private static final String BOT_NAME = "Angela";
    private static final double MIN_HEIGHT = 600.0;
    private static final double MIN_WIDTH = 400.0;

    private final Angela angela = new Angela(PATH, DIRECTORY);

    public Main() throws IOException {
    }

    @Override
    public void start(Stage stage) {
        try {
            setStage(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set stage for the GUI
     *
     * @param stage Stage needed to be setup
     * @throws IOException If an I/O exception occurs
     */
    private void setStage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = createFxmlLoader();
        Scene scene = createScene(fxmlLoader);
        customizeStage(stage, scene, fxmlLoader);
    }

    private FXMLLoader createFxmlLoader() {
        URL urlLocation = Main.class.getResource(RESOURCE_PATH);
        return new FXMLLoader(urlLocation);
    }

    private Scene createScene(FXMLLoader fxmlLoader) throws IOException {
        AnchorPane ap = fxmlLoader.load();
        // Assert the input value is not null
        assert(!(ap == null));
        return new Scene(ap, MIN_WIDTH, MIN_HEIGHT, Color.rgb(160, 244, 255, 0.73));
    }

    private void customizeStage(Stage stage, Scene scene, FXMLLoader fxmlLoader) {
        stage.setTitle(BOT_NAME);
        // Allow the open window to be resized
        stage.setResizable(false);
        stage.setMinHeight(MIN_HEIGHT);
        stage.setMinWidth(MIN_WIDTH);
        stage.setScene(scene);
        fxmlLoader.<MainWindow>getController().setAngela(angela);
        stage.show();
        fxmlLoader.<MainWindow>getController().displayUpcomingDeadline();
    }
}
