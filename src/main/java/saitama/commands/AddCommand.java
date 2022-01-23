package saitama.commands;

import saitama.Storage;
import saitama.TaskList;
import saitama.Ui;
import saitama.tasks.Task;

/**
 * A Command object that adds a task to the task list.
 */
public class AddCommand extends Command{
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
