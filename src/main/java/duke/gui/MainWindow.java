package duke.gui;

import duke.Duke;
import duke.exception.BingChillingException;
import duke.ui.AlertUi;
import duke.ui.MessageUi;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


/**
 * Controller for duke.Gui.MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Duke duke;
    private MessageUi messageUi = new MessageUi();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/Ekud.png"));

    public MainWindow() {
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(messageUi.showWelcomeMessage(), botImage),
                DialogBox.getDukeDialog(duke.getFileLoadingMessage(), botImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        try {
            String response = duke.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, botImage)
            );
            return;
        } catch (BingChillingException e) {
            AlertUi.makeErrorAlert("BingChillingException", e.getMessage());
        } finally {
            userInput.clear();
            return;
        }
    }
}



