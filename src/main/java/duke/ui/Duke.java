package duke.ui;

import duke.ui.components.DialogBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import duke.data.Storage;
import duke.data.TaskList;
import duke.chatbot.ChatBot;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Duke class representing a JavaFX application
 * to serve as the main user interface.
 */
public class Duke extends Application {
    /** Default path for data file: data/duke.txt */
    private static final Path DEFAULT_FILE_PATH = Paths.get("data", "duke.txt");

    /** ChatBot instance for running of user commands */
    private ChatBot chatBot;

    /** UI nodes/components for mainframe of application */
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private void initialiseChatBot() throws IOException {
        Storage store = Storage.initStorage(DEFAULT_FILE_PATH);
        TaskList taskList = TaskList.initTaskList(store);

        this.chatBot = new ChatBot(taskList);
    }

    /**
     * Initialises required components involved in
     * the main layout and sets their sizes and positions.
     *
     * @return Root node for the layout
     */
    private AnchorPane initialiseLayout() {
        // Reused with modifications from JavaFX tutorial at
        // https://se-education.org/guides/tutorials/javaFx.html
        // by Jeffrey Lum

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        dialogContainer.setSpacing(15);
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        return mainLayout;
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Reused with modifications from JavaFX tutorial at
        // https://se-education.org/guides/tutorials/javaFx.html
        // by Jeffrey Lum

        // Initialise logic for chatbot and main layout
        initialiseChatBot();
        AnchorPane mainLayout = this.initialiseLayout();

        // Set fixed height and width for application window
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        scene = new Scene(mainLayout);
        // Workaround for buggy text display on Mac OS
        scene.getRoot().setStyle("-fx-font-family: 'serif'");

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Creates two dialog boxes, one echoing user input and
     * the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        // Reused from JavaFX tutorial at
        // https://se-education.org/guides/tutorials/javaFx.html
        // by Jeffrey Lum
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * Runs the user command based on the input string and gets
     * the feedback response from ChatBot.
     *
     * @param input User input command to run.
     * @return String feedback for command ran.
     */
    private String getResponse(String input) {
        ArrayList<String> response = chatBot.runCommand(input);
        // Show each item in response in a new line
        return response.stream().reduce("", (first, second) -> first + "\n" + second);
    }
}
