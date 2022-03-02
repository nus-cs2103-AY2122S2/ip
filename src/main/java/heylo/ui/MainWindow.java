// Adapted from https://se-education.org/guides/tutorials/javaFxPart4.html
package heylo.ui;

import heylo.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Main main;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image heyloImage = new Image(this.getClass().getResourceAsStream("/images/Heylo.png"));

    /**
     * Greets the user.
     */
    static String greet() {
        return "Heylo! What can I do for you today? :)\n";
    }

    /**
     * Creates window and initializes with greeting message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getHeyloDialog(greet(), heyloImage)
        );
    }

    public void setMain(Main m) {
        main = m;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Heylo's reply.
     * Appends dialog boxes to the scrolling window.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = main.getResponse(input);
        if (!input.trim().equals("")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getHeyloDialog(response, heyloImage)
            );
        }
        userInput.clear();
    }
}