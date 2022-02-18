package mcbot.gui;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import mcbot.McBotGui;

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

    private McBotGui mcBotGui;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image mcBotImage = new Image(this.getClass().getResourceAsStream("/images/McBot.png"));

    /**
     * To initialise the Main Window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Method for chatbot to say hello at the start.
     */
    public void sayHello() {
        String welcome = mcBotGui.getResponse("hi");
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcome, mcBotImage));
    }

    /**
     * Method to set the chatbot.
     * @param mb is the instance of McBotGui.
     */
    public void setMcBotGui(McBotGui mb) {
        mcBotGui = mb;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        boolean isEnd = false;
        String input = userInput.getText();
        String response = mcBotGui.getResponse(input);
        if (response.equals("bye")) {
            isEnd = true;
            response = "Arghh! This ain't the last time ye see me lad";
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, mcBotImage)
        );
        System.out.println(dialogContainer.getAlignment());
        userInput.clear();
        checkToEnd(isEnd);
        
    }

    /**
     * Helper method to check if the user ended the program.
     * @param isEnd is true if the user ended the program.
     */
    private void checkToEnd(boolean isEnd) {
        //code snippet from https://stackoverflow.com/questions/26454149/make-javafx-wait-and-continue-with-code
        if (isEnd) {
            Task<Void> sleeper = new Task<>() {
                @Override
                protected Void call() {
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                    return null;
                }
            };
            sleeper.setOnSucceeded(event -> Platform.exit());
            new Thread(sleeper).start();
        }
    }
}
