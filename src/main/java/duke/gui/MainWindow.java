package duke.gui;

import duke.Duke;
import duke.Storage;
import duke.TextUi;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class MainWindow extends AnchorPane {
    private final TextUi ui = new TextUi();
    private final Storage storage = new Storage();
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Controller for duke.gui.MainWindow. Provides the layout for the other controls.
     */
    public void setDuke(Duke d) {
        String previousTasks = storage.getStorageTasks();
        String welcome = ui.greeting();
        duke = d;
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(welcome, dukeImage),
                DialogBox.getDukeDialog(previousTasks, dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing duke.
     * Duke's reply and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        if (response.equals("bye")) {
            String byeMessage = ui.sayBye();
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(byeMessage, dukeImage)
            );
            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
            pause.setOnFinished(event -> {
                Platform.exit();
                System.exit(0);
            });
            pause.play();
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            userInput.clear();
        }
    }

}
