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

    private Nexus nexus;

    private final Image userImage;
    private final Image nexusImage;

    /**
     * Constructs MainWindow.
     */
    public MainWindow() {
        InputStream daUserPath = this.getClass().getResourceAsStream("/images/DaUser.png");
        InputStream daNexusPath = this.getClass().getResourceAsStream("/images/DaNexus.png");

        //Checks if both userPath and nexusPath is not null using assertions.
        assert daUserPath != null : "daUser path not suppose to be null!";
        assert daNexusPath != null : "daNexus path not suppose to be null!";

        // Creates image based on the path provided.
        userImage = new Image(daUserPath);
        nexusImage = new Image(daNexusPath);
    }

    /**
     * Initializes height for FXML window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Nexus instance in the dialog container.
     * @param d Nexus instance.
     */
    public void setNexus(Nexus d) {
        nexus = d;
        dialogContainer.getChildren().addAll(
                DialogBox.getNexusDialog(d.initUi(), nexusImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Nexus's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        //handle user input and places userImage and nexusImage in the scene.
        String input = userInput.getText();
        String response = nexus.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getNexusDialog(response, nexusImage)
        );
        userInput.clear();

        //crude implementation of bye
        if (input.equals("bye")) {
            System.exit(0);
        }
    }
}
