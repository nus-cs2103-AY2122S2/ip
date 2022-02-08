package duke.commands;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;
import duke.exceptions.DukeException;

public class InvalidUpdateCommand extends UpdateCommand {

    public InvalidUpdateCommand() {
        super("");
    }

    @Override
    public String execute(Storage storage, Ui ui, TaskManager taskManager) throws DukeException {
        throw new DukeException("Invalid Update Command!");
    }
}
