package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class InvalidCommand extends Command {
    public InvalidCommand() {
        super();
    }

    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) {
        System.out.println("    Invalid duke.command.Command!");
    }
}
