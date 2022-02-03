package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * This Command class is the parent class for all commands to be executed.
 */
public abstract class Command {

    /**
     * Executes command by printing exit message.
     * @param taskList  List of tasks
     * @param ui        Ui provided
     * @param storage   Saved history
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

}

