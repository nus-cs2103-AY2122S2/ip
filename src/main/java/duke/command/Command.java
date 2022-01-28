package duke.command;

import duke.exception.DukeException;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public abstract class Command {
    public abstract boolean exec(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
