package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasklist.TaskList;

import java.io.IOException;
import duke.DukeException;

public abstract class Command {

    public abstract void execute(Storage stg, Ui ui, TaskList tasks) throws DukeException, IOException;

    public abstract boolean isExit();

}
