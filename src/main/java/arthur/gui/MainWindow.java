package arthur.gui;

import arthur.Arthur;
import arthur.Ui;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static final int TIME_TO_DELAY = 1;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Arthur arthur;

    private final Image userImage = new Image(
            this.getClass().getResourceAsStream("/images/User.png"));
    private final Image arthurImage = new Image(
            this.getClass().getResourceAsStream("/images/Arthur.png"));

    /**
     * Loads the scrollPane and welcome message upon initialisation.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getAurthurDialog(new Ui().showWelcome(), arthurImage));
    }

    public void setArthur(Arthur a) {
        arthur = a;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Arthur's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     * If bye command given, processes the input and closes the application.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = arthur.run(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAurthurDialog(response, arthurImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            // Creates a time delay to allow the bye command to process
            PauseTransition pause = new PauseTransition(Duration.seconds(TIME_TO_DELAY));
            pause.setOnFinished(i -> System.exit(0));
            pause.play();
        }
    }
}
