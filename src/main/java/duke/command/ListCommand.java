package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

import java.io.IOException;

/**
 * Represents command to list all the tasks in the task list.
 *
 */
public class ListCommand extends Command{

    @Override
    public void execute(TaskList task, Ui ui, Storage storage) throws IOException {
        ui.printList(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}





















