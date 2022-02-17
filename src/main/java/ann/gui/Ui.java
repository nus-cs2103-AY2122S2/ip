package ann.gui;

import ann.AnnBot;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Represents a manager for the GUI.
 */
public class Ui {

    public Ui() {
    }

    /**
     * Initializes the GUI.
     * @@author j-lum-reused
     * Source: from https://se-education.org/guides/tutorials/javaFxPart4.html.
     * @param stage the top level JavaFX container.
     * @param annBot represents the main logic of the application.
     */
    public void start(Stage stage, AnnBot annBot) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Ui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setAnn(annBot);
            stage.show();
            stage.setTitle("AnnBot");
        } catch (Throwable e) {
            showInitializationErrorAndShutDown(e);
        }
    }

    // Solution below adapted from
    // https://github.com/se-edu/addressbook-level3/blob/master/src/main/java/seedu/address/ui/UiManager.java.
    private void showInitializationErrorAndShutDown(Throwable e) {
        final Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Unable to initialize app... Shutting down...");
        alert.setContentText(e.toString());
        alert.showAndWait();
        Platform.exit();
        System.exit(1);
    }
}
