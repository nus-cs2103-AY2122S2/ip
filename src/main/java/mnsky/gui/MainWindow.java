package mnsky.gui;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import mnsky.core.Mnsky;

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

    private Mnsky mnsky;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image mnskyImage = new Image(this.getClass().getResourceAsStream("/images/mnsky.png"));
    private Image mnskyDownImage = new Image(this.getClass().getResourceAsStream("/images/mnskyDown.png"));
    private Image currentMnskyImage = mnskyImage;

    /**
     * Initializes the scroll pane to connect it to the height of the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        userInput.setFont(Font.font(20));
        sendButton.setFont(Font.font(20));
    }

    /**
     * Sets the Mnsky attribute to a Mnsky object.
     * @param m The Mnsky object.
     */
    public void setMnsky(Mnsky m) {
        mnsky = m;
    }

    /**
     * Gets user input from the TextField box and passes it to the displayResponse() function.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        displayResponse(input, true);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    public void displayResponse(String input, boolean showUserMessage) {
        ArrayList<String> responses = mnsky.getResponses(input);

        if (showUserMessage) {
            dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        }

        boolean isBye = false;
        StringBuilder final_response = new StringBuilder();
        for (String response : responses) {
            if (response.equals("bye")) {
                isBye = true;
                continue;
            }

            final_response.append(response);
            final_response.append('\n');
        }

        dialogContainer.getChildren().add(DialogBox.getDukeDialog(final_response.toString(), currentMnskyImage));

        if (isBye) {
            mnsky.shutDown();
            currentMnskyImage = mnskyDownImage;
            dialogContainer.getChildren().add(DialogBox.getDukeDialog("[MNSKY has shut itself down.]",
                    currentMnskyImage));
        }

        userInput.clear();
    }
}
