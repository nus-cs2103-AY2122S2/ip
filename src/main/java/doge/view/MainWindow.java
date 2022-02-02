package doge.view;

import doge.Doge;

import doge.Parser;
import doge.command.ByeCommand;
import doge.command.Command;
import doge.exception.DogeException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.File;

/**
 * Controller for doge.view.MainWindow. Provides the layout for the other controls.
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

    private Doge doge;
    private Image userImg = new Image(new File("./src/main/resource/images/user.png").toURI().toString());
    private Image dogeImg = new Image(new File("./src/main/resource/images/doge.jpg").toURI().toString());

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }


    public void setDoge(Doge d) {
        doge = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String errorMsg;
        Command c;
        String dogeText;

        try {
            c = Parser.parse(userText);
            if (c instanceof ByeCommand) {
                Platform.exit();
            }
            c.execute(doge.getTasks(), doge.getUi(), doge.getStorage());
            doge.getStorage().save(doge.getTasks().getTaskList());
            dogeText = doge.getUi().respond(c);
            userInput.clear();
        } catch (DogeException e) {
            errorMsg = doge.getUi().showError(e.getMessage());
            dogeText = errorMsg;
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImg),
                DialogBox.getDogeDialog(dogeText, dogeImg)
        );
        userInput.clear();
    }
}
