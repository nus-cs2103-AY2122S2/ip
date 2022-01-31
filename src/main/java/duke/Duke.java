package duke;

import java.io.IOException;

import duke.command.Parser;
import duke.exception.DukeException;
import duke.exception.DukeIoException;
import duke.task.TaskList;
import duke.ui.MainWindow;
import duke.ui.Ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * Represents an instance of the Duke application.
 * Serves as the entry point for the entire application.
 */
public class Duke extends Application {
    /** Global task list for all operations. */
    private TaskList taskList;
    private MainWindow mainWindowController;

    public Duke() {

    }

    @Override
    public void init() throws Exception {
        super.init();
        this.initializeTaskList();
    }

    /**
     * Performs pre-execution initialization of resources required throughout the application lifecycle.
     * Loads any existing database and attaches observers for saving the database to disk.
     * @return The current instance of the application being initialized.
     */
    Duke initializeTaskList() {
        try {
            taskList = Storage.load();
            taskList.registerListener(store -> {
                try {
                    Storage.save(store);
                } catch (DukeIoException ex) {
                    System.out.println("Warning: An error occurred while saving Task list");
                }
            });
        } catch (DukeIoException ex) {
            System.out.println("Encountered an error during initialization:\n"
                    + "\t " + ex.getMessage() + " \n"
                    + "Please check that you have read / write permissions in the current folder.\n"
                    + "If the saved data file is corrupted, consider deleting the data folder.\n"
                    + "Will not save any changes!");
        }
        return this;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Hand off to UI to build stage
        try {
            Ui.getInstance().buildStage(primaryStage, this::processCommand);
        } catch (DukeIoException ex) {
            System.out.println(ex.getMessage());
            Platform.exit();
        }
        primaryStage.show();
        Ui.getInstance().greet();
    }

    /**
     * Drives the main application Read-Evaluate-Print Loop.
     */
    public void processCommand(String input) {
        boolean isRunning = Ui.getInstance().printCommand((linePrinter) -> {
            try {
                return Parser.parse(input).execute(linePrinter, taskList);
            } catch (DukeException ex) {
                Ui.getInstance().printError(linePrinter, ex);
            }
            return true;
        });

        if (!isRunning) {
            Platform.exit();
        }
    }
}
