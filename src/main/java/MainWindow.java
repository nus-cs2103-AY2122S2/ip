import java.io.InputStream;

import javafx.fxml.FXML;
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

    private Duke duke;

    private final Image userImage;
    private final Image dukeImage;

    /**
     * Constructor method for MainWindow.
     */
    public MainWindow() {
        InputStream daUserPath = this.getClass().getResourceAsStream("/images/DaUser.png");
        InputStream daDukePath = this.getClass().getResourceAsStream("/images/DaDuke.png");

        //Checks if both userPath and dukePath is not null using assertions.
        assert daUserPath != null : "daUser path not suppose to be null!";
        assert daDukePath != null : "daDuke path not suppose to be null!";

        // Creates image based on the path provided.
        userImage = new Image(daUserPath);
        dukeImage = new Image(daDukePath);
    }

    /**
     * Initialize height for FXML window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Set the duke instance in the dialog container.
     * @param d duke instance.
     */
    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(d.initUi(), dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        //handle user input and places userImage and dukeImage in the scene.
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        //crude implementation of bye
        if (input.equals("bye")) {
            System.exit(0);
        }
    }
}
