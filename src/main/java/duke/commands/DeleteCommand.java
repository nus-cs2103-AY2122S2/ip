package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Deletes a task for the task list.
 */
public class DeleteCommand extends Command {
    private static final String MESSAGE = "Got it. I've removed this task:";

    protected int index;
    /**
     * Constructs a delete command.
     *
     * @param index The index of task in the list.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task t = tasks.deleteTask(index);
        storage.saveTaskList(tasks);
        return MESSAGE + "\n  " + t.toString() + "\n" + tasks.printTaskCount();
    }
}
