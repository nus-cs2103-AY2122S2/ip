package baron.ui;

import baron.Baron;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Baron baron = new Baron();

    /**
     * Starts the JavaFX application.
     *
     * @param stage the primary stage of the JavaFX application.
     */
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new MainWindow(this.baron));
        stage.setScene(scene);
        stage.show();
    }
}
