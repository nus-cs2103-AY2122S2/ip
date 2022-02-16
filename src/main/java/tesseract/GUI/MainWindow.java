package tesseract.GUI;

//import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
//import javafx.scene.control.Dialog;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import tesseract.main.Tesseract;

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

    private Tesseract tesseract;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaLoki.jpeg"));
    private final Image tessImage = new Image(this.getClass().getResourceAsStream("/images/DaTesseract.jpeg"));

    /**
     * Initializes the user interface window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets Tesseract.
     *
     * @param tess Tesseract instance that manages all inputs.
     */
    public void setTesseract(Tesseract tess) {
        tesseract = tess;
        String welcomeMsg = tesseract.getWelcomeMsg();
        dialogContainer.getChildren().add(DialogBox.getTesseractDialog(welcomeMsg, tessImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = tesseract.getResponse(input);
        if (input.equals("bye")) {
            response = tesseract.getExitMsg();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTesseractDialog(response, tessImage)
        );
        userInput.clear();
    }
}
