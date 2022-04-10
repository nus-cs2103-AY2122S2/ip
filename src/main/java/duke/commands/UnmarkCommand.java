package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Sets a task as not done yet.
 */
public class UnmarkCommand extends Command {
    private static final String MESSAGE = "Nice! I've marked this task as not done yet:";

    protected int index;
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
        String task = tasks.unmarkTask(index).toString();
        storage.saveTaskList(tasks);
        return MESSAGE + "\n  " + task;
    }
}
