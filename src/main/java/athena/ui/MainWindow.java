package athena.ui;

import java.io.IOException;

import athena.Athena;
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

    private Athena athena;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image athenaImage = new Image(this.getClass().getResourceAsStream("/images/athena.jpg"));

    public MainWindow(Athena athena) {
        this.athena = athena;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        displayAthenaDialog("Greetings! My name is Athena. What can I help you with?");
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Athena's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = athena.getResponse(input);
        displayUserDialog(input);
        displayAthenaDialog(response);
        userInput.clear();
        if (!athena.isActive()) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> close());
            delay.play();
        }
    }

    private void displayAthenaDialog(String dialog) {
        dialogContainer.getChildren().add(DialogBox.getAthenaDialog(dialog, athenaImage));
    }

    private void displayUserDialog(String dialog) {
        dialogContainer.getChildren().add(DialogBox.getUserDialog(dialog, userImage));
    }
}
