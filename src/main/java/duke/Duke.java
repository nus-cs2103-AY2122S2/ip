package duke;

import duke.commands.Command;
import duke.commands.ExitCommand;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.*;
import duke.ui.TextUI;
import duke.ui.gui.DialogBox;
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



/**
 * Entry point of DUke.
 * Initializes the application and initiates contact with user.
 */
public class Duke {

    private static TaskList taskList;
    private static Storage storage;
    private static TextUI textUI;
    private static Parser parser;

    public Duke() {
        initialize();
    }

    private static void initialize() {
        textUI = new TextUI();
        storage = new Storage();
        parser = new Parser();
        try {
            taskList = Storage.loadTasklist();
        } catch (DukeException e) {
            taskList = new TaskList();
            System.out.println(e.getMessage());
        }
        Command.defineTaskList(taskList);
    }

    private static void run() {
        assert taskList != null : "TaskList failed to initialize";
        textUI.printWelcomeMessage();
        do {
            String userInputCommand = textUI.getUserCommand();
            try {
                Command currCommand = parser.parseCommands(userInputCommand);
                textUI.printMessage(currCommand.execute());
                storage.saveTasklist(taskList);
            } catch (DukeException e) {
                textUI.printMessage(e.getMessage());
            }
        } while (ExitCommand.isRunning());
    }
    
    public static void main(String[] args) {
        initialize();
        run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        assert taskList != null : "TaskList failed to initialize";
        try {
            Command currCommand = parser.parseCommands(input);
            String outputToUser = currCommand.execute();
            storage.saveTasklist(taskList);
            return outputToUser;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
