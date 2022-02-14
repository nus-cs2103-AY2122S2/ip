package apollo.ui.gui;

import java.io.IOException;

import apollo.Apollo;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Gui {

    //Todo: welcome message in gui
    private Apollo apollo = new Apollo();

    /**
     * Starts JavaFX stage.
     *
     * @param primaryStage Stage to start JavaFX.
     */
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            primaryStage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setApollo(apollo);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
