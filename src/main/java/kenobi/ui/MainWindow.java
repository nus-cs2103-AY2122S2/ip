package kenobi.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    private Kenobi kenobi;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Penguin_pp.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Kenobi_pp.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setKenobi(Kenobi k) {
        kenobi = k;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        dialogContainer.getChildren().add(DialogBox.getUserDialog(userText, new ImageView(userImage)));

        kenobi.giveCommand(userInput.getText());
        userInput.clear();

        Label dukeText = new Label(kenobi.getResponse());
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(dukeText, new ImageView(dukeImage))
        );
    }
}
