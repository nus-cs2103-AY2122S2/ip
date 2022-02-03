package duke.controllers;

import duke.Main;
import duke.chi.Chi;
import duke.exception.ChiException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

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
    private Button sendButton;

    private Chi chi;

    /**
     * Constructor of the class.
     *
     * @param chi The new Chi instance.
     */
    public MainWindow(Chi chi) {
        try {
            this.chi = chi;
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/simp.jpeg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/chi.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getChiDialog("Hi welcome to ChiBot", dukeImage));
    }

    public void setDuke(Chi d) {
        chi = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();;
        String response;
        try {
            input = userInput.getText();
            if (input.equalsIgnoreCase("bye")) {
                Platform.exit();
            }
            response = chi.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getChiDialog(response, dukeImage)
            );
            userInput.clear();
        } catch (ChiException e){
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getChiDialog(e.getMessage(), dukeImage)
            );
            userInput.clear();
        } catch (IOException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getChiDialog("The IO is faulty nyan~!", dukeImage)
            );
            userInput.clear();
        }
    }
}
