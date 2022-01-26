package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;
import duke.managers.Ui;
import duke.managers.Storage;

public abstract class Command {
    protected boolean exitProgram = false;
    public abstract void execute(TaskList tasks, Ui io, Storage storage) throws DukeException;
    public boolean isExit() {
        return exitProgram;
    }
}
