package aeromon;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

//@@author cashewnade-reused
//Reused from https://se-education.org/guides/tutorials/javaFx.html

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

    private Aeromon aeromon;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User-modified.png"));
    private final Image aeromonImage = new Image(this.getClass().getResourceAsStream("/images/Aeromon-modified.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Aeromon object.
     *
     * @param aeromon the Aeromon object.
     */
    public void setAeromon(Aeromon aeromon) {
        this.aeromon = aeromon;
        dialogContainer.setBackground(new Background(new BackgroundFill(Color.rgb(255, 245, 230), CornerRadii.EMPTY, Insets.EMPTY)));
        dialogContainer.getChildren().add(DialogBox.getAeromonDialog(aeromon.start(), aeromonImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = aeromon.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAeromonDialog(response, aeromonImage)
        );
        userInput.clear();
    }
}
