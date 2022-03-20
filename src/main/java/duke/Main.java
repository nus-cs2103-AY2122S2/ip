package duke;

import java.io.IOException;

import duke.ui.Ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private final Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            fxmlLoader.getNamespace().put("primary", Ui.PRIMARY_COLOR);
            fxmlLoader.getNamespace().put("secondary", Ui.SECONDARY_COLOR);
            fxmlLoader.getNamespace().put("tertiary", Ui.TERTIARY_COLOR);
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
            String css = Main.class.getResource("/css/MainWindow.css").toExternalForm();
            scene.getStylesheets().add(css);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
