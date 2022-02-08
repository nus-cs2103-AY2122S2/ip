package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Sets a task as done.
 */
public class MarkCommand extends Command {
    protected int index;
    private static final String MESSAGE = "Nice! I've marked this task as done:";

    /**
     * Constructs a mark command.
     *
     * @param index The index of task in the list.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markTask(index);
        storage.saveTaskList(tasks);
        return ui.showMessage(MESSAGE + "\n  " + tasks.getTaskString(index));
    }
}
