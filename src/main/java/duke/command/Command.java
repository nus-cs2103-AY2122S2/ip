package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    public abstract void execute(Storage storage, TaskList tasks, Ui ui) throws DukeException;
    public abstract boolean isExit();
}
