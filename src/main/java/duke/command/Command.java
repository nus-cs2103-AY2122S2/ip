package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Encapsulates a command to be executed in Duke.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param taskList the task list to execute this command on.
     * @param ui the user interface of Duke.
     * @param storage the storage of Duke.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Checks whether the Duke application should exit after this command.
     *
     * @return true iff this Command is a ByeCommand instance.
     */
    public abstract boolean isExit();
}
