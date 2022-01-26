package duke;

import duke.command.Parser;
import duke.exception.DukeException;
import duke.exception.DukeIoException;
import duke.task.TaskList;

/**
 * Represents an instance of the Duke application.
 * Serves as the entry point for the entire application.
 */
public class Duke {
    /** Global task list for all operations. */
    private static TaskList taskList;

    public static void main(String[] args) {
        new Duke().init().run();
    }

    private Duke() {

    }

    /**
     * Performs pre-execution initialization of resources required throughout the application lifecycle.
     * Loads any existing database and attaches observers for saving the database to disk.
     * @return The current instance of the application being initialized.
     */
    private Duke init() {
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

    /**
     * Drives the main application Read-Evaluate-Print Loop.
     */
    private void run() {
        Ui ui = Ui.getInstance().greet();

        boolean isRunning = true;
        while (isRunning) {
            String command = ui.readInput();
            isRunning = ui.printCommand((linePrinter) -> {
                try {
                    return Parser.parse(command).execute(linePrinter, taskList);
                } catch (DukeException ex) {
                    ui.printError(linePrinter, ex);
                }
                return true;
            });
            System.out.println();
        }
    }
}
