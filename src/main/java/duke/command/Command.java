package duke.command;
import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Abstraction of all the commands that can be given by the user.
 */
public abstract class Command {

    /**
     * Executes the command.
     * @param taskList the task list
     * @param ui the io that is being used.
     * @param storage the storage that is being used.
     * @return false if command to exit application, true otherwise.
     * @throws DukeException superclass of all exceptions that can be thrown. InvalidIndexException for invalid index,
     *              InvalidDateException if the date from user is of the wrong format and InvalidCommandFormatException
     *              if the command given by the user is of wrong syntax.
     */
    public abstract boolean exec(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
