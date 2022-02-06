package chibot;

import chibot.chi.Chi;
import chibot.controllers.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Chi chi = new Chi("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        MainWindow app = new MainWindow(chi);
        Scene scene = new Scene(app);
        stage.setScene(scene);
        stage.setTitle("ChiBot");
        stage.show();
    }
}
