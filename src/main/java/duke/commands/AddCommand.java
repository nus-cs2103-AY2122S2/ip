package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Adds a task to the TaskList.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Creates a new add command.
     * @param task the task to be added
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(task);
        storage.append(task.getAppendData());
        return "Task added:\n" + ui.tab(task.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
