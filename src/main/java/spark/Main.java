package spark;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import spark.ui.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Spark spark = new Spark();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSpark(spark);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
