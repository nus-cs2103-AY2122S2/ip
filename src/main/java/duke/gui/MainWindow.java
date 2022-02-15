package duke.gui;

import duke.Duke;
import duke.exception.DukeException;
import duke.util.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

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
    private Stage stage;
    private static final Ui ui = new Ui();

    private final Image USER_IMAGE = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/elizabeth_swan.png")));
    private final Image DUKE_IMAGE = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/jack_sparrow.png")));
    private final Image WELCOME_GIF = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/jack_sparrow_welcome.gif")));
    private final Image EXIT_GIF = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/jack_sparrow_adios.gif")));
    private final Image ERROR_PHOTO = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/jack_sparrow_disgusted.jpg")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d, Stage stage) {
        duke = d;
        this.stage = stage;

        // Send welcome message
        ImageView iv = new ImageView(WELCOME_GIF);
        iv.setFitHeight(229);
        iv.setFitWidth(576);
        iv.setCache(true);
        dialogContainer.getChildren().add(iv);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(ui.showWelcome(), DUKE_IMAGE));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (isEmptyInput(input)) {
            return;
        }

        try {
            String response = duke.getResponse(input);
            if (response.equals("exit")) {
                handleExit(input);
                userInput.clear();
                return;
            }
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog("Adventurer said: " + input, USER_IMAGE),
                    DialogBox.getDukeDialog(response, DUKE_IMAGE)
            );
        } catch (DukeException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog("Adventurer reply: " + input, USER_IMAGE),
                    DialogBox.getDukeDialogCustom(e.getMessage(), DUKE_IMAGE, "error")
            );
        }

        userInput.clear();
    }

    /**
     * Handles the termination of the chat-bot
     *
     * @param input user input
     */
    private void handleExit(String input) {
        String response = ui.showExitMessage();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog("Adventurer said: " + input, USER_IMAGE),
                DialogBox.getDukeDialog(response, DUKE_IMAGE)
        );
        // Show exit gif
        ImageView iv = new ImageView(EXIT_GIF);
        iv.setFitHeight(208);
        iv.setFitWidth(500);
        dialogContainer.getChildren().add(iv);

        userInput.setDisable(true);
        sendButton.setText("Exit");
        sendButton.setOnAction(event -> stage.close());
    }

    /**
     * Checks if user input is empty
     *
     * @param input user input
     * @return true if user has not typed in any input
     */
    private boolean isEmptyInput(String input) {
        return input.equalsIgnoreCase("");
    }
}

