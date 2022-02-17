package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Sets a task as done.
 */
public class MarkCommand extends Command {
    private static final String MESSAGE = "Nice! I've marked this task as done:";

    protected int index;
    /**
     * Constructs a mark command.
     *
     * @param index The index of task in the list.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        String task = tasks.markTask(index).toString();
        storage.saveTaskList(tasks);
        return MESSAGE + "\n  " + task;
    }
}
