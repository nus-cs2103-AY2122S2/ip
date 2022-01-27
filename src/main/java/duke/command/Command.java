package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

public abstract class Command {

    /**
     * Constructs a {@code Command} object.
     */
    public Command() {}

    /**
     * Executes the command.
     * @param tasks current list of tasks
     * @param ui the UI used
     * @param storage the storage used
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

}
