package kenobi.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import kenobi.Kenobi;

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

    private Image kenobiImage = new Image(getClass().getResourceAsStream("/images/kenobi.png"));
    private Image userImage = new Image(getClass().getResourceAsStream("/images/user.png"));

    private Kenobi kenobi;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setKenobi(Kenobi k) {
        kenobi = k;

        assert kenobi != null : "Kenobi instance is null";

        kenobi.init();

        dialogContainer.getChildren().add(
                DialogBox.getKenobiDialog(kenobi.greet(), kenobiImage)
        );
    }

    /**
     * Adds a dialog box to the scroll pane.
     */
    private void addDialog(DialogBox db) {
        dialogContainer.getChildren().add(db);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String inputString = userInput.getText();
        userInput.clear();

        addDialog(DialogBox.getUserDialog(inputString, userImage));

        kenobi.executeCommand(inputString);
        addDialog(DialogBox.getKenobiDialog(kenobi.getResponse(), kenobiImage));

        if (kenobi.isTerminated()) {
            Platform.exit();
        }
    }
}
