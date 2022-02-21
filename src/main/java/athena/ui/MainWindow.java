package athena.ui;

import java.io.IOException;

import athena.Athena;
import athena.exceptions.InputException;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends Stage {
    @FXML
    private Scene scene;
    @FXML
    private AnchorPane ap;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private final Athena athena;
    private final Image userImage;
    private final Image athenaImage;

    public MainWindow(Athena athena) {
        this.athena = athena;
        userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
        athenaImage = new Image(this.getClass().getResourceAsStream("/images/athena.jpg"));

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        displayAthenaDialog(Messages.WELCOME_MESSAGE);
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Athena's reply and then appends them to
     * the dialog container. Clears the user input after processing if no input errors were encountered.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        displayUserDialog(input);
        try {
            String response = athena.getResponse(input);
            displayAthenaDialog(response);
            userInput.clear();
        } catch (InputException e) {
            displayAthenaError(e.getMessage());
        }
        if (!athena.getIsActive()) {
            shutdown();
        }
    }

    private void shutdown() {
        userInput.setDisable(true);
        sendButton.setDisable(true);
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> close());
        delay.play();
    }

    private void displayAthenaError(String errorMessage) {
        dialogContainer.getChildren().add(DialogBox.getAthenaError(errorMessage, athenaImage));
    }

    private void displayAthenaDialog(String dialog) {
        dialogContainer.getChildren().add(DialogBox.getAthenaDialog(dialog, athenaImage));
    }

    private void displayUserDialog(String dialog) {
        dialogContainer.getChildren().add(DialogBox.getUserDialog(dialog, userImage));
    }
}
