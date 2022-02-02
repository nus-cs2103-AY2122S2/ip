package seedu.duke.chatbot;

import seedu.duke.command.Command;
import seedu.duke.exceptions.DukeException;
import seedu.duke.task.TaskList;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Functions as the chatbot by taking in inputs.
 * Also helps in giving out specified outputs.
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;


    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Creates the chatbot.
     * @param filePath which provides the path to the database, a txt file storing old tasks
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath, ui);
        try {
            this.taskList = storage.getOldTaskList();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Starts the chatbot by taking in commands and executing them.
     */
    public void run() {
        String name  = ui.showWelcome();
        Parser parser = new Parser(name);
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = parser.parse(fullCommand); //read the full command and return the command
                this.taskList = c.execute(this.taskList, this.ui, this.storage);
                isExit = c.isExit();
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
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

        // more code to be added here later
    }

    /**
     * Runs the chatbot Duke with a specified path to the database file.
     */
    public static void main(String[] args) {
        String filePath = System.getProperty("user.dir")  + "/data/OldTasks.txt";
        new Duke(filePath).run();
    }
}