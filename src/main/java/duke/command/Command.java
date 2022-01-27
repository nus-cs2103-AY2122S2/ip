package duke.command;

import duke.List;
import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import java.io.IOException;

abstract public class Command {

    private boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public void execute(List taskList, Ui ui, Storage storage) throws IOException, DukeException {

    }

    public boolean getIsExit() {
        return this.isExit;
    }
}
