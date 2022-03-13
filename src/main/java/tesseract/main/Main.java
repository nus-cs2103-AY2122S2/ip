package tesseract.main;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tesseract.GUI.MainWindow;

/**
 * A GUI for Tesseract using FXML.
 */
public class Main extends Application {
    private static final String MAIN_WINDOW_PATH = "/view/MainWindow.fxml";
    private static final String CSS_PATH = "/css/display.css";

    private Tesseract tesseract = new Tesseract();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(MAIN_WINDOW_PATH));
            AnchorPane ap = fxmlLoader.load();

            Scene scene = new Scene(ap);
            scene.getStylesheets().add(getClass().getResource(CSS_PATH).toExternalForm());

            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setTesseract(tesseract);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
