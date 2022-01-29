package saitama.commands;

import saitama.Storage;
import saitama.TaskList;
import saitama.Ui;
import saitama.tasks.Task;

/**
 * A Command object that adds a task to the task list.
 */
public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println("OK...");
        taskList.add(task);
        System.out.println("The following task has been added to the list: ");
        System.out.println(task);
        System.out.println("Now you have " + taskList.numOfTasks() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

