package duke.gui;

import duke.Duke;
import duke.Ui;
import duke.exception.DukeException;
import duke.gui.DialogBox;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/russell.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/edna.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(Ui.WELCOME_MSG, dukeImage));
    }

    @FXML
    private void handleUserInput() throws DukeException {
        String userText = userInput.getText();
        String dukeText = duke.getResponse(userText);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getDukeDialog(dukeText, dukeImage)
        );
        userInput.clear();
    }
}