package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class HelpCommand extends Command {

    public HelpCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printHelp();
    }
}
