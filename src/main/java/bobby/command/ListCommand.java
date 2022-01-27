package bobby.command;

import bobby.Storage;
import bobby.task.TaskList;
import bobby.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printLongLine();
        tasks.sortTaskList();
        ui.printTaskList(tasks);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ListCommand;
    }
}
