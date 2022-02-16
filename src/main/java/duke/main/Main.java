package duke.main;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private static final String APP_NAME = "Charizard";

    @Override
    public void start(Stage stage) {
        MainWindow mainWindow = new MainWindow();
        Scene scene = new Scene(mainWindow);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/Icon.png")));
        stage.setTitle(APP_NAME);
        stage.show();
    }
}

