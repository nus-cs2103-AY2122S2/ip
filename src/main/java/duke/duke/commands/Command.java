package duke.commands;

import duke.info.exception.InvalidInputException;
import duke.info.task.Calendar;
import duke.ui.Ui;
import duke.storage.Storage;

public abstract class Command {

    Command() {}

    public boolean isExit() {
        return (this instanceof ExitCommand);
    }

    public void execute(Calendar calendar, Ui ui, Storage storage) throws InvalidInputException {
        /* To be overridden in child classes; */
    }
}
