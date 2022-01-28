package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;
import duke.util.TaskList;

/**
 * Represents a command that adds the specified task to the task list.
 */
public class AddCommand extends Command {
    private Task taskToAdd;

    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(taskToAdd);
        return ui.showMessage("Meow! Task is added!\n" + taskToAdd + "\n"
                + "Number of tasks in list: " + tasks.getSize());
    }
}
