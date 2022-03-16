package duke.ui;

import duke.Duke;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

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

    private Duke duke;

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/DaUser.png")));
    private final Image dukeImage = new Image(Objects.requireNonNull(this
            .getClass().getResourceAsStream("/images/DaDuke.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    public VBox getDialogContainer() {
        return this.dialogContainer;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {

        if (Ui.isExit) {
            exitApplication();
        }

        String input = userInput.getText();
        String response = duke.getResponse(input);
        assert !response.isEmpty() : "Assertion failed on MainWindow.handleUserInput(): response is empty";
        if (isInvalid(response)) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDialog(Ui.MSG_COMMAND +  input, userImage, DialogBoxStyle.UserError),
                    DialogBox.getDialog(response.replace(Ui.PREFIX_INVALID, ""), dukeImage, DialogBoxStyle.BotError)
            );
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDialog(Ui.MSG_COMMAND + input, userImage, DialogBoxStyle.UserNormal),
                    DialogBox.getDialog(response, dukeImage, DialogBoxStyle.BotNormal)
            );
        }
        userInput.clear();

        if (response.equals(Ui.MSG_EXIT)) {
            Ui.isExit = true;
        }
    }

    @FXML
    public void exitApplication() {
        Platform.exit();
    }

    private boolean isInvalid(String response) {
        return response.substring(0,9).equals(Ui.PREFIX_INVALID);
    }
}
