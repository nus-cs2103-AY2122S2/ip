package ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Ui interface that the user interacts with.
 * Involves a scanner to read user inputs.
 */
public class UiFormatter {
    /**
     * Formats the Java GUI.
     * @param stage Stage to format.
     */
    public void formatGui(Stage stage, AnchorPane mainLayout, ScrollPane scrollPane,
                          VBox dialogContainer, TextField userInput, Button sendButton, Scene scene) {
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    /**
     * Formats the DukeException message according to a particular format.
     * @param message Text that must be formatted.
     * @return String of message with format applied
     */
    public static String formatExceptionMessage(String message) {
        return "OOPS!!! " + message;
    }

    /** Returns the hello message when the bot starts.
     * @return Hello message.
     * */
    public String hello() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    /**
     * Returns the goodbye message when the bot session ends.
     * @return Goodbye message.
     */
    public String exitMessage() {
        return "Bye. Hope to see you again soon!";
    }
}
