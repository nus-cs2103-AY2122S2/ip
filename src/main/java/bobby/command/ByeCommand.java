package bobby.command;

import bobby.Storage;
import bobby.task.TaskList;
import bobby.Ui;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.goodbyeMessage();
        storage.saveTasks(tasks.getTaskList());
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ByeCommand;
    }
}
