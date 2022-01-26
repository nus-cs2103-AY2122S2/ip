package duke.command;

import duke.manager.Ui;
import duke.manager.TaskList;
import duke.manager.Storage;
import duke.exception.DukeException;

public abstract class Command {

    public Command() {

    }

    public abstract void execute(TaskList tasks,Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
