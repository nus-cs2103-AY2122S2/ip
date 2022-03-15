package ui;

import cleese.Cleese;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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

    private Cleese cleese;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Bruce.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Alfred.png"));

    /**
     *
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcomeMessage = String.format("Good day Sir, I'm Cleese, your virtual butler!\n"
                                                + "And just how may I help you today?\n\n"
                                                + "Type 'help' to view all available commands ");
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(welcomeMessage, dukeImage));
    }

    public void setDuke(Cleese d) {
        cleese = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = cleese.getResponse(input);
        if (input.equals("bye")) {
            Stage stage = (Stage) dialogContainer.getScene().getWindow();
            stage.close();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
