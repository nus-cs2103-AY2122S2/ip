package main.duke.gui;

import javafx.fxml.FXML;
import main.duke.Duke;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import main.duke.DukeException;
import main.duke.commands.Command;

import java.util.concurrent.CompletableFuture;

public class MainWindow extends AnchorPane{

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.jpg"));

    private Duke duke = new Duke("data", "duke.txt");

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws DukeException {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        CompletableFuture.runAsync(() -> {
        try {
            if (Command.getIsExit()) {
                Thread.sleep(200);
                System.exit(0);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }});
        assert (userInput.equals(null));
    }
}
