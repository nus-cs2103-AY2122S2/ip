package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Abstraction of all the commands that can be given by the user.
 */
public abstract class Command {

    /**
     * Executes the command.
     * @param taskList the task list
     * @param storage the storage that is being used.
     * @return a String consisting of Duke's response message.
     * @throws DukeException superclass of all exceptions that can be thrown. InvalidIndexException for invalid index,
     *              InvalidDateException if the date from user is of the wrong format and InvalidCommandFormatException
     *              if the command given by the user is of wrong syntax.
     */
    public abstract String exec(TaskList taskList, Storage storage) throws DukeException;

    /**
     * Method that checks if Duke should exit.
     * @return false for all Commands other than Exit Command.
     */
    public boolean shouldAbort() {
        return false;
    }
}
