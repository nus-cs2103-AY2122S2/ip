package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;
import duke.util.TaskList;

/**
 * Deletes the specified task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.getTask(index);
        tasks.deleteTask(index);
        ui.printMessage("Meow! Task is removed!\n" + t + "\n" + "Number of tasks in list: " + tasks.getSize());
    }
}
