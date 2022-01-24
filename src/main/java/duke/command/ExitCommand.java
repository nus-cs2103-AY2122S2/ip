package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {}

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printOutput("Bye! Hope to see you again soon!");
    }

    public boolean isExit() {
        return true;
    }
}
