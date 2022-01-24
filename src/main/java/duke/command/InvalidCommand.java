package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class InvalidCommand extends Command {

    public InvalidCommand() {}

    public void execute(TaskList taskList, Ui ui, Storage storage) {}

    public boolean isExit() {
        return false;
    }
}
