package duke.ui;

import java.io.IOException;
import java.util.Objects;

import duke.Duke;
import duke.ui.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GraphicsUi extends Application {
    private static final String WINDOW_TITLE = "Task List Assistant";
    private final Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GraphicsUi.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass()
                            .getResourceAsStream("/images/icon.png"))));
            fxmlLoader.<MainWindow>getController().setDuke(duke);

            // Formatting Stage
            stage.setTitle(WINDOW_TITLE);
            stage.setResizable(false);
            stage.setMinHeight(600.0);
            stage.setMinWidth(400.0);

            stage.show();
            // Print welcome message
            fxmlLoader.<MainWindow>getController().printWelcomeMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
