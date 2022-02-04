package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;
import duke.util.TaskList;

/**
 * Represents a command that deletes the specified task from the task list.
 */
public class DeleteCommand extends Command {
    private static final String MESSAGE_TO_SHOW = "Meow! %s is deleted!\nNumber of tasks in list: %d";
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = tasks.deleteTask(index);
        return ui.showMessage(String.format(MESSAGE_TO_SHOW, deletedTask, tasks.getSize()));
    }
}
