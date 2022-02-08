package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Task;

/**
 * Deletes a task for the task list.
 */
public class DeleteCommand extends Command {
    protected int index;
    private static final String MESSAGE = "Got it. I've removed this task:";

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
