package duke.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        MainWindow mainWindow = new MainWindow();
        Scene scene = new Scene(mainWindow);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/Icon.png")));
        stage.setTitle("Charizard");
        stage.show();
    }
}

