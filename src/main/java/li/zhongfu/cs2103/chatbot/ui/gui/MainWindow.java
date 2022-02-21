package li.zhongfu.cs2103.chatbot.ui.gui;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import li.zhongfu.cs2103.chatbot.Duke;
import li.zhongfu.cs2103.chatbot.types.message.ChatMessage;
import li.zhongfu.cs2103.chatbot.types.message.Message;
import li.zhongfu.cs2103.chatbot.types.message.QuitMessage;
import li.zhongfu.cs2103.chatbot.types.message.SystemMessage;

/**
 * Controller class for Duke main window.
 */
public class MainWindow extends BorderPane {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button enterButton;

    private Duke duke;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the current Duke instance to {@code duke} and initializes the instance, displaying the messages received
     * if any.
     *
     * @param duke the Duke instance to be used
     */
    public void setAndInitDuke(Duke duke) {
        this.duke = duke;
        handleMessages(duke.init());
    }

    private void addMessageToDialogContainer(Node node) {
        if (node instanceof MessageBubble) {
            MessageBubble bubble = (MessageBubble) node;
            bubble.bindWidthToProperty(scrollPane.widthProperty().subtract(72));
        }
        dialogContainer.getChildren().add(node);
    }

    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        userInput.clear();

        addMessageToDialogContainer(
                MessageBubble.create(input, getTimeString(), Color.LIGHTGREEN));
        handleMessages(duke.handleInput(input));
    }

    /**
     * Handle messages returned from Duke: displays messages as system messages or chat bubbles, or quits the
     * application as required.
     *
     * If a {@code QuitMessage} is present, then the application only quits 1.5s after processing the message.
     *
     * @param messages the messages to be handled
     */
    private void handleMessages(List<Message> messages) {
        for (Message msg : messages) {
            if (msg instanceof ChatMessage) {
                addMessageToDialogContainer(
                        MessageBubble.create(msg.getMessage(), getTimeString(), Color.LIGHTGRAY));
            } else if (msg instanceof SystemMessage) {
                addMessageToDialogContainer(new Label(msg.getMessage()));
            } else if (msg instanceof QuitMessage) {
                // sleep 1.5s then exit
                Task<Void> waitThenQuit = new Task<>() {
                    @Override
                    protected Void call() throws Exception {
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            // ignore
                        }
                        return null;
                    }
                };

                waitThenQuit.setOnSucceeded((WorkerStateEvent event) -> {
                    // this.getScene() returns null, apparently
                    Stage stage = (Stage) scrollPane.getScene().getWindow();
                    stage.close();
                });

                new Thread(waitThenQuit).start();
            } else {
                throw new RuntimeException("Unknown Message type");
            }
        }
    }

    private String getTimeString() {
        return LocalTime.now().format(TIME_FORMATTER);
    }
}
