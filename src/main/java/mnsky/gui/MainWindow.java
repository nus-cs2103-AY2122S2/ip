package mnsky.gui;

import java.util.ArrayList;

import javafx.application.Platform;
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
    private static final int NUM_LINES_IN_BOX = 3;

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

        for (int i = 0; i < responses.size(); i += NUM_LINES_IN_BOX) {
            StringBuilder response = new StringBuilder();
            for (int j = i; j < responses.size() && j < i + NUM_LINES_IN_BOX; j++) {
                if (responses.get(j).equals("bye")) {
                    Platform.exit();
                }
                response.append(responses.get(j));
                response.append('\n');
            }

            dialogContainer.getChildren().add(DialogBox.getDukeDialog(response.toString(), mnskyImage));
        }

        userInput.clear();
    }
}
