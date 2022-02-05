package alfred.ui.controller;

import alfred.Alfred;
import javafx.application.Platform;
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

    private Alfred alfred;

    private final Image userImage =
            new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image alfredImage =
            new Image(this.getClass().getResourceAsStream("/images/alfred.png"));

    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
    }

    public void greetUser() {
        String greetingMessage = this.alfred.getGreetingMessage();
        DialogBox startupDialog = DialogBox.getAlfredDialog(greetingMessage, this.alfredImage);
        this.dialogContainer.getChildren().addAll(startupDialog);
    }

    public void setAlfred(Alfred alfred) {
        this.alfred = alfred;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        String response = this.alfred.getResponse(input);
        this.dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, this.userImage),
                DialogBox.getAlfredDialog(response, this.alfredImage));
        if (this.alfred.isExit()) {
            Platform.exit();
        }
        this.userInput.clear();
    }

}
