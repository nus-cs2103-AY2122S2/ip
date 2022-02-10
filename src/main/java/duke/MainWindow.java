package duke;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * This is a MainWindow class that acts as the logic behind the
 * main GUI screen for the Duke application.
 *
 * @author  Hsiao Jiet
 * @version 1.0
 * @since   2022-2-1
 */

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private MenuBar menuBar;

    private Duke duke;

    private Image userImage = new Image(MainWindow.class.getResourceAsStream("/duke/images/jackie.jpg"));
    private Image dukeImage = new Image(MainWindow.class.getResourceAsStream("/duke/images/dictator.jpg"));


    /**
     * Sets up the Duke instance to start up the GUI of the Duke application
     * @param d is the Duke instance of this program
     */
    @FXML
    public void setDuke(Duke d) throws DukeException, IOException {
        duke = d;
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(duke.run(), dukeImage)
        );
    }

    /**
     * Logic behind how user input is received and a response
     * by the Duke is provided on the GUI
     */
    @FXML
    private void handleUserInput() throws DukeException, IOException {
        String input = userInput.getText();
        assert input != ""; //Assumes all text by user WILL have something
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        userInput.clear();
    }

    @FXML
    private void handleStatisticsAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/duke/view/Statistic.fxml"));
            Parent root1 = fxmlLoader.load();
            Statistic statisticController = fxmlLoader.getController();
            statisticController.initDuke(duke);
            statisticController.initialize();
            Stage stage = new Stage();
            stage.setTitle("Statistic window");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}