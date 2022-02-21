package li.zhongfu.cs2103.chatbot.ui.gui;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

    private List<String> inputHistory = new ArrayList<>();
    private int inputIndex = -1;
    private String inputLatest = null;

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
            // might want to make it bound to the padding width
            bubble.bindWidthToProperty(scrollPane.widthProperty().subtract(72));
        } else if (node instanceof Label) {
            Label label = (Label) node;
            /*
             * the -72 offset for MessageBubble was a guess
             * we pick -40 here because the bubble padding is 16px on each side
             */
            label.prefWidthProperty().bind(scrollPane.widthProperty().subtract(40));
            label.setWrapText(true);
        }
        dialogContainer.getChildren().add(node);
    }

    @FXML
    private void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.UP) {
            historyPrev();
        } else if (event.getCode() == KeyCode.DOWN) {
            historyNext();
        } // else do nothing i guess
    }

    private void userInputSetAndMoveToEnd(String input) {
        userInput.setText(input);
        userInput.positionCaret(input.length());
    }

    // look at next oldest previously-submitted cmd
    private void historyPrev() {
        if (inputIndex == 0) { // already showing oldest hist elem; do nothing
            return;
        }

        if (inputIndex < 0) { // if inputIndex is -1, then we haven't started seeking through history yet
            if (inputHistory.isEmpty()) { // if no history, then give up
                return;
            }
            // store current text & index, and display history
            inputLatest = userInput.getText();
            inputIndex = inputHistory.size() - 1; // last element
            userInputSetAndMoveToEnd(inputHistory.get(inputIndex));
        } else { // inputIndex > 0
            // decrement inputIndex then show history
            userInputSetAndMoveToEnd(inputHistory.get(--inputIndex));
        }
    }

    // look at next newest previously-submitted cmd
    private void historyNext() {
        if (inputIndex < 0) { // we weren't showing history; do nothing
            return;
        }

        int lastElem = inputHistory.size() - 1;
        if (inputIndex >= lastElem) { // already displaying latest history elem
            // display the latest input (that hasn't been submitted yet, but was previously temporarily stored)
            inputIndex = -1;
            // in case we accidentally a bug
            userInputSetAndMoveToEnd(inputLatest != null ? inputLatest : "");
            inputLatest = null;
        } else { // 0 <= inputIndex <= lastElem - 1
            // increment inputIndex then show history
            userInputSetAndMoveToEnd(inputHistory.get(++inputIndex));
        }
    }

    // when the user submits some new input, so we reset state and add the input to history
    private void historyAdd(String input) {
        inputIndex = -1;
        inputLatest = null;
        inputHistory.add(input);
    }

    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        userInput.clear();
        historyAdd(input);

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
