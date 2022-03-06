package duke;

import java.io.IOException;

import duke.command.Command;
import duke.command.CommandType;
import duke.command.FindCommand;
import duke.command.PrintCommand;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

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
            System.err.println(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Returns the task list.
     *
     * @return The task list
     */
    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Returns the command after parsing the input.
     *
     * @param input The input from the user
     * @return The command
     * @throws DukeException If there are errors when parsing the input
     */
    public Command getCommand(String input) throws DukeException {
        return parser.parse(input);
    }

    /**
     * Returns the response message after executing the command.
     *
     * @param command The command to execute
     * @return The response message
     * @throws DukeException If the List of tasks in the task list is
     * empty or if there are any errors when retrieving the tasks
     * @throws IOException If the tasks cannot be saved to the data file
     */
    public String getResponse(Command command) throws DukeException, IOException {
        return command.execute(taskList, ui, storage);
    }

    /**
     * Returns the filtered tasks reminder message after parsing the
     * input and executing the command.
     *
     * @param command The command
     * @return The filtered tasks reminder message
     */
    public String getFilteredTasksReminderMessage(Command command) {
        if (!taskList.hasFilter()) {
            return "";
        }

        if (taskList.getFilterCommandType() == CommandType.PRINT && !(command instanceof PrintCommand)) {
            return ui.filteredTasksReminderMessage(ui.tasksOnDateMessage(taskList, taskList.getFilterInfo()));
        } else if (taskList.getFilterCommandType() == CommandType.FIND && !(command instanceof FindCommand)) {
            return ui.filteredTasksReminderMessage(ui.tasksWithKeywordMessage(taskList, taskList.getFilterInfo()));
        } else {
            return "";
        }
    }
}
