package spark.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import spark.Spark;

import java.util.concurrent.TimeUnit;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML // @FXML annotation marks a private or protected member and makes it accessible to FXML despite its modifier
    private ScrollPane dialogScrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button runButton;

    private Spark spark;

    private Image sparkImage = new Image(this.getClass().getResourceAsStream("/images/blue-circle.png"));

    @FXML
    public void initialize() {
        dialogScrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setSpark(Spark spark) {
        this.spark = spark;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = spark.executeCommand(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, sparkImage),
                DialogBox.getSparkDialog(response, sparkImage)
        );
        userInput.clear();

        // TODO: create a way to exit Spark while printing a message!
        if (response.contains("Cool, see you around!")) {
            System.exit(0);
        }
    }
}
