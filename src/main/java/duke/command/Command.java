package duke.command;

import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents the command given by user.
 */
public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
    public abstract boolean isExit();
}
