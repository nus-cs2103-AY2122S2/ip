package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Sets a task as not done yet.
 */
public class UnmarkCommand extends Command {
    protected int index;
    private static final String MESSAGE = "Nice! I've marked this task as not done yet:";

    /**
     * Constructs an unmark command.
     *
     * @param index The index of task in the list.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.unmarkTask(index);
        storage.saveTaskList(tasks);
        return MESSAGE + "\n  " + tasks.getTaskString(index);
    }
}
