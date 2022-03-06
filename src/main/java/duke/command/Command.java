package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * An interface that represents a command.
 */
public interface Command {
    /**
     * Checks if the command is an Exit command.
     *
     * @return Flag to indicate if the command is an Exit command
     */
    boolean isExit();

    /**
     * Executes the command and then returns the response message.
     *
     * @param taskList Task list
     * @param ui An object to handle I/O operations
     * @param storage An object to handle file operations
     * @return The response message
     * @throws DukeException If the List of tasks in the task list is
     * empty or if there are any errors when retrieving the tasks
     * @throws IOException If the tasks cannot be saved to the data file
     */
    String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException;
}
