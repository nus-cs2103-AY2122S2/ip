package duke.commands;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;
import duke.exceptions.DukeException;


public class InvalidMarkCommand extends MarkCommand {

    public InvalidMarkCommand() {
        super("");
    }

    @Override
    public String execute(Storage storage, Ui ui, TaskManager taskManager) throws DukeException {
        throw new DukeException("Invalid mark/unmark command!");
    }
}
