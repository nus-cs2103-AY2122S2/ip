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

    public void setAndInitDuke(Duke d) {
        duke = d;
        List<Node> nodes = dialogContainer.getChildren();
        handleMessages(nodes, duke.init());
    }

    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        userInput.clear();

        List<Node> nodes = dialogContainer.getChildren();
        nodes.add(new MessageBubble(input, getTimeString(), Color.LIGHTGREEN));
        handleMessages(nodes, duke.handleInput(input));
    }

    private void handleMessages(List<Node> dialogContainerChildren, List<Message> messages) {
        for (Message msg : messages) {
            if (msg instanceof ChatMessage) {
                dialogContainerChildren.add(new MessageBubble(msg.getMessage(), getTimeString(), Color.LIGHTGRAY));
            } else if (msg instanceof SystemMessage) {
                dialogContainerChildren.add(new Label(msg.getMessage()));
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
