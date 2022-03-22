package duke.commands;

import duke.data.TaskList;
import duke.data.TasksEditor;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.DukeException;

import java.io.IOException;

/**
 * Represents an executable command.
 */
public class Command {
    protected Command() {
    }

    /**
     * Executes the command.
     *
     * @param tasksEditor a task list editor to handle task operations.
     * @param ui an object to help generate response message.
     * @param storage an object ot help store the data.
     * @return The response message.
     * @throws DukeException If the command is not specified.
     * @throws IOException If facing issue when loading the data file.
     */
    public String execute(TasksEditor tasksEditor, Ui ui, Storage storage) throws DukeException, IOException {
        throw new DukeException("This method is to be implemented by child classes");
    };

    /**
     * Decides whether it is exit command.
     *
     * @return true if it is an instance of ExitCommand; otherwise false
     */
    public boolean isExit() {
        return false;
    }
}
