package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.task.TaskList;
import duke.util.Ui;

/**
 * Abstract class for commands.
 *
 * <p>All future commands are to inherit from this class.</>
 */
public abstract class Command {
    protected String key;

    /**
     * Constructor for command to init values.
     *
     * @param key Keyword to call this command.
     */
    Command(String key) {
        this.key = key;
    }

    /**
     * Abstract function for execution of a command behavior.
     *
     * @param input User input
     * @param taskList User tasklist.
     * @param storage Storage to store the updated tasklist.
     * @param ui Duke UI to print what the command wants.
     * @throws DukeException If certain conditions in function are met.
     */
    public abstract void execute(String input, TaskList taskList, Storage storage, Ui ui) throws DukeException;

    /**
     * Get description of the task after the command keyword.
     *
     * @param input The full user input.
     * @param emptyDescErrDesc No description print msg.
     * @return User input without the command's keyword.
     * @throws DukeException If task description is empty.
     */
    protected String getTaskDescription(String input, String emptyDescErrDesc) throws DukeException {
        String taskDescription = input.substring(input.indexOf(key) + key.length() + 1);

        if (taskDescription.length() == 0) {
            throw new DukeException(emptyDescErrDesc);
        }

        return taskDescription;
    }

    public String getKey() {
        return key;
    }
}
