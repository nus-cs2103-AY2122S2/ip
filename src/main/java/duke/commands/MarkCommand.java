package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Marks a task as completed.
 */
public class MarkCommand extends Command {
    private final int targetIndex;

    /**
     * Creates a new mark command.
     *
     * @param i the index of the task to be marked as done
     */
    public MarkCommand(int i) {
        targetIndex = i;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.markTask(targetIndex);
        storage.save(tasks);
        return "Great job! I've marked the task as completed:\n" + ui.tab(t.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
