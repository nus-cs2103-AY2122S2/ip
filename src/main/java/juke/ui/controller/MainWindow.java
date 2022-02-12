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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image jukeImage = new Image(this.getClass().getResourceAsStream("/images/Juke.png"));

    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
    }

    public void setGui(Gui gui) {
        this.gui = gui;
    }

    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        this.dialogContainer.getChildren().add(
            DialogBox.getUserDialog(input, this.userImage));
        try {
            Command cmd = CommandHandler.fetchCommand(input);
            CommandHandler.execute(cmd);
            this.dialogContainer.getChildren().add(
                DialogBox.getJukeDialog(this.gui.displayResult(CommandHandler.fetchResult(cmd)), this.jukeImage));
        } catch (JukeInvalidCommandException e) {
            this.dialogContainer.getChildren().add(
                DialogBox.getJukeDialog(e.getMessage(), this.jukeImage));
        }
        this.userInput.clear();
    }

    /**
     * Sends a message on Juke's behalf
     *
     * @param text Message to send.
     */
    public void addJukeDialog(String text) {
        this.dialogContainer.getChildren().add(
            DialogBox.getJukeDialog(gui.getResponse(text), this.jukeImage));
    }
}
