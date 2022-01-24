package duke.command;

import duke.exception.DukeException;
import duke.function.TaskList;
import duke.function.Ui;
import duke.function.Storage;

public abstract class Command {
    String fullCommand;

    Command(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public abstract boolean isExit();

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
