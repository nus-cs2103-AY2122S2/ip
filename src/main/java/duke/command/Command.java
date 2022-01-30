package duke.command;

import duke.logic.DukeException;
import duke.logic.Storage;
import duke.logic.TaskList;
import duke.logic.Ui;

public abstract class Command {
    public abstract boolean execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
