package chibot.controllers;

import chibot.Main;
import chibot.chi.Chi;
import chibot.exception.ChiException;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserImg.png"));
    private Image chiImage = new Image(this.getClass().getResourceAsStream("/images/chisan.png"));

    /**
     * Places introduction message when window opens.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getChiDialog(chi.getWelcomeMessage(), chiImage));
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
                dialogContainer.getChildren().add(DialogBox.getChiDialog(chi.getGoodbyeMessage(), chiImage));
                PauseTransition pt = new PauseTransition(Duration.seconds(3));
                pt.setOnFinished(e -> Platform.exit());
                pt.play();
            } else if (input.equalsIgnoreCase("help")) {
               response = chi.getResponse("help");
                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(input, userImage),
                        DialogBox.getChiDialog(response, chiImage),
                        HelpDialogBox.getChiHelpDialog(chiImage)
                );
            } else {
                response = chi.getResponse(input);
                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(input, userImage),
                        DialogBox.getChiDialog(response, chiImage)
                );
            }
            userInput.clear();
        } catch (ChiException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getChiErrorDialog(e.getMessage(), chiImage)
            );
            userInput.clear();
        } catch (IOException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getChiDialog("The IO is faulty nyan~!", chiImage)
            );
            userInput.clear();
        }
    }
}
