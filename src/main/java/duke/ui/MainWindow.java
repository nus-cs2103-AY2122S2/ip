package duke.ui;

import java.util.Objects;

import duke.Duke;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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

    private Duke duke;

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/User.png")));
    private final Image cortanaImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/Cortana.png")));

    /**
     * Initialize the main window.
     */
    @FXML
    public void initialize() {
        Label welcome = new Label("");
        Image image = new Image("images/Cortana.gif");
        welcome.setGraphic(new ImageView(image));
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcome, cortanaImage));
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event ->
                dialogContainer.getChildren().add(
                        DialogBox.getDukeDialog(new Label("Hi, my name is Cortana, what can I do for you?"),
                                cortanaImage)));
        delay.play();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets duke.
     *
     * @param d the d
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one contains user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(new Label(input), userImage),
                DialogBox.getDukeDialog(new Label(response), cortanaImage)
        );
        userInput.clear();
        if (response.equals("Bye. Hope to see you again soon!")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}
