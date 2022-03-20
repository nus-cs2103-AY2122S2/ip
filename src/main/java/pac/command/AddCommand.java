package pac.command;

import pac.task.Task;
import pac.task.TaskList;
import pac.ui.Ui;
import pac.storage.Storage;

import java.io.IOException;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.add(task);
        storage.writeTasks(tasks);
        return ui.showAddTask(task, tasks);
    }
}
