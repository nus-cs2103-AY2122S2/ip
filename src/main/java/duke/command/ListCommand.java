package duke.command;

import duke.ui.Messages;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print(Messages.LIST_MSG);
        for (int i = 0; i < tasks.getSize(); i++) {
            ui.print(i + 1 + "." + tasks.getTaskStatement(i));
        }
    }

}
