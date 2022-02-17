package taskie.command;

import taskie.storage.Storage;
import taskie.task.Task;
import taskie.tasklist.TaskList;
import taskie.ui.Ui;


/**
 * A class that specifies the behavior of a command that deletes a task from  a task list.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand.
     *
     * @param index Integer index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        super("delete");
        this.index = index;
    }

    /**
     * Executes the actions of the command.
     *
     * @param tasks TaskList to act on.
     * @param ui Ui to use when printing messages.
     * @param storage Storage to call for loading and saving tasks.
     * @param response StringBuilder object to append results to.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, StringBuilder response) {
        if (isOutOfBounds(tasks)) {
            response.append("Invalid index, please try again.");
            return;
        }
            Task task = tasks.remove(index);
            response.append(ui.taskDeleteMessage(task, tasks.size()));
            assert response.length() > 0; // response should not be empty
            storage.save(tasks.list());
    }

    /**
     * Checks if the internal index is out of bounds given a list.
     *
     * @param tasks TaskList to check bounds with.
     * @return True if internal index is out of bounds. False if index is within bounds.
     */
    private boolean isOutOfBounds(TaskList tasks) {
        return index < 0 || index >= tasks.size();
    }
}

