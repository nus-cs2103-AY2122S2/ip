package lucifer.gui;

import java.util.Objects;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import lucifer.Lucifer;
import lucifer.ui.Ui;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    /** The scroll pane of the GUI **/
    @FXML
    private ScrollPane scrollPane;
    /** The dialog container of the GUI **/
    @FXML
    private VBox dialogContainer;
    /** The text box of the GUI **/
    @FXML
    private TextField userInput;
    /** The main class of the GUI **/
    private Lucifer lucifer;
    private final Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
            "/images/User.png")));
    private final Image luciferImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
            "/images/Lucifer.jpg")));
    /**
     * Initializes the GUI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greeting = Ui.greeting();
        dialogContainer.getChildren().addAll(
                DialogBox.getLuciferDialog(greeting, luciferImage)
        );
    }
    /**
     * Sets the Lucifer class.
     **/
    public void setLucifer(Lucifer l) {
        lucifer = l;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = lucifer.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getLuciferDialog(response, luciferImage)
        );
        if (input.startsWith("bye")) {
            Ui.farewell();
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished( event -> Platform.exit());
            delay.play();
        }
        userInput.clear();
    }
}
