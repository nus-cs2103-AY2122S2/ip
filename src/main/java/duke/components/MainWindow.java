package duke.components;

import java.io.InputStream;

import duke.Duke;
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
    // Images path.
    private static final String PC_PRINCIPAL_IMAGE_FILE = "/image/pcPrincipal.png";
    private static final String CARTMAN_IMAGE_FILE = "/image/cartman.png";
    private static final String WELCOME_MESSAGE = "Hello bruh! I'm PC Principal! How can i be of service!";
    // Error messages.
    private static final String DUKE_NOT_FOUND = "Duke image file not found!";
    private static final String DUKE_NULL = "Duke is null";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;
    private Image pcPrincipalImage;


    /**
     * Initialize the main window fxml.
     */
    @FXML
    public void initialize() {
        InputStream pcPrincipalInputStream = this.getClass().getResourceAsStream(PC_PRINCIPAL_IMAGE_FILE);
        InputStream cartmanInputStream = this.getClass().getResourceAsStream(CARTMAN_IMAGE_FILE);
        assert pcPrincipalInputStream != null : DUKE_NOT_FOUND;
        pcPrincipalImage = new Image(pcPrincipalInputStream);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // Create a welcome dialogue when duke starts.
        dialogContainer.getChildren().add(
                ResponseDialogBox.getDukeDialog(WELCOME_MESSAGE, pcPrincipalImage)
        );
    }

    public void setDuke(Duke d) {
        assert d != null : DUKE_NULL;
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.runCommand(input);
        dialogContainer.getChildren().addAll(
                UserDialogBox.getUserDialog(input),
                ResponseDialogBox.getDukeDialog(response, pcPrincipalImage)
        );
        userInput.clear();
    }
}
