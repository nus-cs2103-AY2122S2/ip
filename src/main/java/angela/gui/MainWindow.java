package angela.gui;

import angela.Angela;
import angela.util.Ui;
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

/**
 * Controller for angela.gui.MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static final String GOODBYE = "bye";
    private static final String USER_IMAGE_URL = "/images/DaUser.png";
    private static final String ANGELA_IMAGE_URL = "/images/DaAngela.png";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Angela angela;
    private final Ui ui = new Ui();

    private final Image userImage = new Image(this.getClass().getResourceAsStream(USER_IMAGE_URL));
    private final Image angelaImage = new Image(this.getClass().getResourceAsStream(ANGELA_IMAGE_URL));

    /**
     * Initialize the FXML
     */
    @FXML
    public void initialize() {
        setScrollPane();
        setUiAtBegin();
    }

    /**
     * Sets up properties for the scroll pane
     */
    private void setScrollPane() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets up the displayed text for GUI in the beginning
     */
    private void setUiAtBegin() {
        String helloText = ui.startChat();
        DialogBox helloBox = DialogBox.getAngelaDialog(helloText, angelaImage);
        dialogContainer.getChildren().addAll(helloBox);
    }

    /**
     * Display upcoming deadlines in GUI
     */
    public void displayUpcomingDeadline() {
        String nearestDeadlineText = ui.showNearestDeadlines(angela.getDateTable());
        String upcomingDeadlineText = ui.showUpcomingDeadlines(angela.getDateTable());
        DialogBox upcomingDeadlineBox = DialogBox.getAngelaDialog(upcomingDeadlineText, angelaImage);
        DialogBox nearestDeadlineBox = DialogBox.getAngelaDialog(nearestDeadlineText, angelaImage);
        dialogContainer.getChildren().addAll(upcomingDeadlineBox, nearestDeadlineBox);
    }

    public void setAngela(Angela d) {
        angela = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Angela's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = angela.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAngelaDialog(response, angelaImage)
        );
        userInput.clear();
        if (input.equals(GOODBYE)) {
            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(1));
            pauseTransition.setOnFinished(f -> Platform.exit());
            pauseTransition.play();
        }
    }
}
