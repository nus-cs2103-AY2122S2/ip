package bob.command;

import bob.Storage;
import bob.Task.Task;
import bob.TaskList;
import bob.Ui;

public abstract class AddCommand extends Command {
    private String taskName;

    public AddCommand(String taskName) {
        this.taskName = taskName;
    }
    public String getTaskName() {
        return this.taskName;
    }
    public void addAndUpdate(Task task, TaskList tasks, Ui ui, Storage store) {
        tasks.addTask(task);
        store.updateStore(tasks);
        ui.newTask(task, tasks);
    }
}
