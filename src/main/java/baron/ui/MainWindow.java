package baron.ui;

import java.io.IOException;
import java.util.Objects;

import baron.Baron;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private final Baron baron;

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/DaUser.png")));
    private final Image baronImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/DaDuke.png")));

    /**
     * Constructs a {@code MainWindow} object for the GUI with the specified {@code Baron} object.
     *
     * @param baron the {@code Baron} object that acts as the main logic for {@code MainWindow} object.
     * @see Baron
     */
    public MainWindow(Baron baron) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/views/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.baron = baron;
        this.dialogContainer.getChildren().add(DialogBox.getBaronDialog(this.baron.getWelcomeMessage(),
                this.baronImage));
    }

    /**
     * Initialise MainWindow GUI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Handles user input and creates two dialog boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to the dialog container. Clears the user input after processing.
     * If the user input is blank, do nothing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.isBlank()) {
            return;
        }

        String response = this.baron.getResponse(input);
        this.dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, this.userImage),
                DialogBox.getBaronDialog(response, this.baronImage)
        );
        this.userInput.clear();
        exitIfBye(input);
    }

    /**
     * Exits the application if the specified input is "bye".
     *
     * @param input user input.
     */
    private void exitIfBye(String input) {
        if (input.strip().equals("bye")) {
            this.sendButton.setDisable(true);
            this.userInput.setDisable(true);
            this.dialogContainer.getChildren().add(
                    DialogBox.getBaronDialog("This app will close in 2s...", this.baronImage)
            );
            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(2));
            pauseTransition.setOnFinished(e -> Platform.exit());
            pauseTransition.play();
        }
    }
}
