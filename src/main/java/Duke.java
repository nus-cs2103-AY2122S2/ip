import java.io.File;
import java.io.IOException;

import exception.DukeException;
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
import notes.NoteList;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Main class from which the bot is run.
 */
public class Duke extends Application {

    private static final String TASK_FILE_PATH = "tasklistdata.txt";
    private static final String NOTE_FILE_PATH = "notelistdata.txt";
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;
    private NoteList notes;
    private final Parser parser;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpeg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpeg"));

    /**
     * Duke constructor which creates new files for data to be stored.
     */
    public Duke() {
        File taskFile = new File(TASK_FILE_PATH);
        File noteFile = new File(NOTE_FILE_PATH);
        try {
            taskFile.createNewFile();
            noteFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Could not create file.");
        }
        storage = new Storage(TASK_FILE_PATH, NOTE_FILE_PATH);
        try {
            tasks = new TaskList(storage.setUpTaskData());
            notes = new NoteList(storage.setUpNoteData());
        } catch (DukeException e) {
            System.out.println("Could not set up data.");
        }
        ui = new Ui(tasks, notes);
        parser = new Parser(tasks, notes);
    }

    /**
     * Starts the bot by combining Parser, Storage, TaskList and Ui and handling Duke exceptions.
     */
    public String run(String userInput) {
        try {
            return ui.startConversation(this.parser, this.storage, userInput);
        } catch (DukeException e) {
            return ui.showIllegalTextError(e);
        }
    }

    public static void main(String[] args) {}

    /**
     * Creates the container for the content of the chat to scroll.
     */
    public void setContainer(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
        formatWindow(stage, mainLayout);
    }

    /**
     * Formatting the window to look as expected.
     */
    public void formatWindow(Stage stage, AnchorPane mainLayout) {
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

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    /**
     * Starts the conversation between the user and the bot.
     */
    @Override
    public void start(Stage stage) {
        //Setting up required components
        setContainer(stage);

        //Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(new Label(userText), new ImageView(user)),
                DialogBox.getDukeDialog(new Label(dukeText), new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * Generates Duke response for user inputs.
     * @param userInput String entered by the user
     * @returns Duke's response by running the bot.
     */
    public String getResponse(String userInput) {
        return new Duke().run(userInput);
    }
}
