package juke.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import juke.command.Command;
import juke.command.CommandHandler;
import juke.exception.JukeInvalidCommandException;
import juke.ui.Gui;

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

    private Gui gui;

    private Image userImage = new Image(getClass().getResourceAsStream("/images/User.png"));
    private Image jukeImage = new Image(getClass().getResourceAsStream("/images/Juke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the GUI instance.
     *
     * @param gui GUI Instance.
     */
    public void setGui(Gui gui) {
        this.gui = gui;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Command cmd = null;
        try {
            cmd = CommandHandler.fetchCommand(input);
        } catch (JukeInvalidCommandException e) {
            dialogContainer.getChildren().add(
                DialogBox.getJukeDialog(e.getMessage(), jukeImage));
        }
        CommandHandler.execute(cmd);
        if (CommandHandler.isCommandNull(cmd)) {
            return;
        }
        dialogContainer.getChildren()
                .add(DialogBox.getUserDialog(input, userImage));
        dialogContainer.getChildren()
                .add(DialogBox.getJukeDialog(gui.getResultMessage(CommandHandler.fetchResult(cmd)), jukeImage));
        this.userInput.clear();
    }

    /**
     * Sends a message on Juke's behalf
     *
     * @param text Message to send.
     */
    public void addJukeDialog(String text) {
        dialogContainer.getChildren().add(
            DialogBox.getJukeDialog(gui.getResponse(text), jukeImage));
    }
}
