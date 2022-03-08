package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Storage;

/**
 * Abstract class for commands.
 *
 * <p>All future commands are to inherit from this class.</p>
 */
public abstract class Command {
    private String key;

    /**
     * Constructor for command to init values.
     *
     * @param key Keyword to call this command.
     */
    Command(String key) {
        this.key = key;
    }

    /**
     * Execution of a command behavior.
     *
     * @param input User input
     * @param taskList User tasklist.
     * @param storage Storage to store the updated tasklist.
     * @return Command response.
     * @throws DukeException If certain conditions in function are met.
     */
    public abstract String execute(String input, TaskList taskList, Storage storage) throws DukeException;

    /**
     * Gets description of the task after the command keyword.
     *
     * @param input The full user input.
     * @param emptyDescErrDesc No description print msg.
     * @return User input without the command's keyword.
     * @throws DukeException If task description is empty.
     */
    protected String getTaskDescription(String input, String emptyDescErrDesc) throws DukeException {
        String taskDescription = "";
        //check if input have command key
        assert input.length() > 0 : "There's not input, command should not exist";
        assert input.contains(key) : "Command doesn't have key. Command should not exist.";

        try {
            taskDescription = input.substring(input.indexOf(key) + key.length() + 1);
        } catch (IndexOutOfBoundsException exception) {
            throw new DukeException(emptyDescErrDesc);
        }

        if (taskDescription.length() == 0) {
            throw new DukeException(emptyDescErrDesc);
        }

        return taskDescription;
    }

    public String getKey() {
        return key;
    }
}
