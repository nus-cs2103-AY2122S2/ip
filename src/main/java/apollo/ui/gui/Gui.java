package apollo.ui.gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Gui {

    private Stage primaryStage;

    /**
     * Starts JavaFX stage.
     *
     * @param primaryStage Stage to start JavaFX.
     */
    public void start(Stage primaryStage, String greeting) {
        this.primaryStage = primaryStage;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            primaryStage.setScene(scene);
            fxmlLoader.<MainWindow>getController().initialize(greeting, this);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        primaryStage.close();
    }
}
