package ann.gui;

import ann.AnnBot;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 * Solution below adapted from https://se-education.org/guides/tutorials/javaFxPart4.html.
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
    private AnnBot annBot;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image annImage = new Image(this.getClass().getResourceAsStream("/images/ann.png"));
    private final String welcomeMessage = "Greetings from AnnBot!\nHow can I help you? :)";
    private final Image greetingImage = new Image(this.getClass().getResourceAsStream("/images/greeting.png"));

    /**
     * Initializes the MainWindow when application is launched.
     * Shows welcome message to users.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getAnnDialog(welcomeMessage, greetingImage));
    }

    public void setAnn(AnnBot a) {
        annBot = a;
    }

    /**
     * Creates two dialog boxes, one repeating user input and the other containing AnnBot's response, and
     * then adds them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = annBot.getResponse(input);
        boolean isExitCommand = annBot.isExitCommand(input);
        userInput.clear();
        if (isExitCommand) {
            dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
            handleExitCommand(response);
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getAnnDialog(response, annImage)
            );
        }
    }

    private void handleExitCommand(String response) {
        if (showConfirmationAlert("Are you sure you want to exit?")) {
            showInformationAlert(response);
            Platform.exit();
            System.exit(0);
        }
    }

    private void showInformationAlert(String response) {
        final Alert alert = new Alert(Alert.AlertType.INFORMATION, response);
        alert.showAndWait();
    }

    private boolean showConfirmationAlert(String content) {
        final Alert alert = new Alert(Alert.AlertType.CONFIRMATION, content, ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        return alert.getResult() == ButtonType.YES;
    }
}
