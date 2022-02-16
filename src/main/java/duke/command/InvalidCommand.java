package duke.command;

import duke.exception.DukeException;
import duke.function.Storage;
import duke.function.TaskList;
import duke.function.Ui;

public class InvalidCommand extends Command {
    public InvalidCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.printReturn("That is not a valid command\nPlease type 'help' to see a list of valid commands");
    }
}
