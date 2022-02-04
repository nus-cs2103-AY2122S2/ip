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
    private static final String MESSAGE_TO_SHOW = "Meow! %s is added!\nNumber of tasks in list: %d";
    private Task taskToAdd;

    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(taskToAdd);
        return ui.showMessage(String.format(MESSAGE_TO_SHOW, taskToAdd, tasks.getSize()));
    }
}
