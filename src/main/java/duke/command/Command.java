package duke.command;

import java.io.IOException;

import duke.List;
import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Represents a Command.
 */
public abstract class Command {

    private boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public abstract String execute(List taskList, Ui ui, Storage storage) throws IOException, DukeException;
}
