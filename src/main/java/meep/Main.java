package meep;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import meep.Ui.Gui;
import meep.Ui.MainWindow;

/**
 * A GUI for Gui using FXML.
 */
public class Main extends Application {

    private Gui gui = new Gui();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setResizable(false);
            fxmlLoader.<MainWindow>getController().setDuke(gui);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
