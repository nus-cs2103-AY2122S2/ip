package duke.gui;

import duke.Duke;
import duke.Response;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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
    private ImageView imageView;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    public void setDuke(Duke d) {
        duke = d;
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        dialogContainer.setOnScroll(event -> {
            double deltaY = event.getDeltaY() * 2; // * 2 to make the scrolling a bit faster
            double height = dialogContainer.getBoundsInLocal().getHeight();
            double vvalue = scrollPane.getVvalue();
            scrollPane.vvalueProperty().unbind();
            scrollPane.setVvalue(vvalue - deltaY / height);
            // deltaY / height to make the scrolling equally fast regardless of the total height
        });
        userInput.setPromptText("Type command");
        userInput.setFont(Font.font(14));
        userInput.setStyle("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);");
    }

    public void greet() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(duke.greet(), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String trimmedInput = input.trim();
        if (trimmedInput.equals("")) {
            return;
        }
        Response response = duke.getResponse(trimmedInput);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response.getResponse(), dukeImage)
        );
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        userInput.clear();
        if (response.isExit()) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
        userInput.setPromptText("Type command");
    }

}
