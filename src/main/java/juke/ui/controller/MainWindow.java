package juke.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import juke.Juke;
import juke.command.Command;
import juke.command.CommandHandler;
import juke.exception.JukeInvalidCommandException;

/**
 * Main window controller for JavaFX.
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

    /**
     * Reference to the Juke instance.
     */
    private Juke juke;

    private Image userImage = new Image(getClass().getResourceAsStream("/images/User.png"));
    private Image jukeImage = new Image(getClass().getResourceAsStream("/images/Juke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Juke instance.
     *
     * @param instance GUI Instance.
     */
    public void setJuke(Juke instance) {
        this.juke = instance;
    }

    @FXML
    private void handleUserInput() {
        assert juke != null;
        String input = userInput.getText();
        userInput.clear();
        Command cmd = null;
        try {
            cmd = CommandHandler.fetchCommand(input);
        } catch (JukeInvalidCommandException e) {
            addUserDialog(input);
            addErrorDialog(e);
        }
        CommandHandler.execute(cmd);
        if (CommandHandler.isCommandNull(cmd)) {
            return;
        }
        addUserDialog(input);
        try {
            addJukeDialog(juke.getGui().getResultMessageOrThrow(CommandHandler.fetchResult(cmd)));
        } catch (Exception e) {
            addErrorDialog(e);
        }
    }

    /**
     * Sends a message on the user's behalf
     *
     * @param text Message to send.
     */
    public void addUserDialog(String text) {
        dialogContainer.getChildren().add(DialogBox.getUserDialog(text, userImage));
    }

    /**
     * Sends a message on Juke's behalf
     *
     * @param text Message to send.
     */
    public void addJukeDialog(String text) {
        dialogContainer.getChildren().add(DialogBox.getJukeDialog(text, jukeImage));
    }

    /**
     * Sends an error message on Juke's behalf
     *
     * @param exception Exception.
     */
    public void addErrorDialog(Exception exception) {
        dialogContainer.getChildren().add(DialogBox.getErrorDialog(exception.getMessage(), jukeImage));
    }
}
