package duke;

import duke.command.Command;
import duke.command.CommandType;
import duke.command.PrintCommand;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * The driver class that runs the program.
 */
public class Duke {
    private final Ui ui;
    private final Parser parser;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructor to initialize an instance of Duke class with folder
     * name and file name.
     *
     * @param folderName Folder name of the data file
     * @param fileName File name of the data file
     */
    public Duke(String folderName, String fileName) {
        ui = new Ui();
        parser = new Parser();

        try {
            storage = new Storage(folderName, fileName);
            taskList = storage.loadTasksFromFile();
        } catch (DukeException | IOException e) {
            ui.displayError(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Executes the program.
     */
    public void run() {
        ui.displayWelcome();
        boolean isExit = false;

        Command command = null;

        while (!isExit) {
            try {
                String commandLine = ui.readCommand();
                command = parser.parse(commandLine);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (DukeException | IOException e) {
                ui.displayError(e.getMessage());
            } finally {
                checkAndDisplayFilteredTasks(command);
                ui.displayLine();
            }
        }
    }

    /**
     * Checks if the task list has filtered tasks.
     * Displays the filtered tasks message if there are filtered tasks.
     *
     * @param command Executed command
     */
    public void checkAndDisplayFilteredTasks(Command command) {
        if (taskList.hasFilter()) {
            String filteredTasksMessage;

            if (taskList.getFilterCommandType() == CommandType.PRINT && !(command instanceof PrintCommand)) {
                filteredTasksMessage = ui.tasksOnDateMessage(taskList, taskList.getFilterInfo());
            } else {
                filteredTasksMessage = "";
            }

            if (!filteredTasksMessage.isEmpty()) {
                ui.displayFilteredTasks(filteredTasksMessage);
            }
        }
    }

    /**
     * Starts the execution of the program.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        new Duke("data", "tasks.txt").run();
    }
}
