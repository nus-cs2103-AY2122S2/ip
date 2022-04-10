package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Adds a task to the task list.
 */
public class AddCommand extends Command {
    private static final String MESSAGE = "Got it. I've added this task:";

    protected Task task;

    /**
     * Constructs an add command.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.addTask(task);
        storage.saveTaskList(tasks);
        return MESSAGE + "\n  " + task.toString() + "\n" + tasks.printTaskCount();
    }
}
