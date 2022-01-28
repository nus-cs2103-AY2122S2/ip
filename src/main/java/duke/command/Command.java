package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

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
     * Executes the command.
     *
     * @param taskList Task list
     * @param ui An object to handle I/O operations
     * @param storage An object to handle file operations
     * @throws DukeException If the task list is empty and if there are
     * any errors when retrieving tasks
     * @throws IOException If the tasks cannot be saved to the data file
     */
    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException;
}
