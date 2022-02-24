package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.DukeUnsupportedOperationException;
import duke.tasks.TaskList;

/**
 * Parent class of all duke commands.
 */
public class Command {

    static TaskList taskList;

    /**
     * Defines the TaskList shared with all commands.
     *
     * @param taskList TaskList defined and used by all commands.
     */
    public static void defineTaskList(TaskList taskList) {
        Command.taskList = taskList;
    }

    /**
     * Carries out the command.
     *
     * @return Message for completing the command which is displayed to user.
     * @throws DukeException If arguments passed to the commands are invalid.
     */
    public String execute() throws DukeException{
        throw new DukeUnsupportedOperationException("This method is to be implemented by child classes");
    }
}
