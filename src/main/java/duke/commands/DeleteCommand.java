package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates command to delete a task from task list
 */
public class DeleteCommand implements Command {
    /**
     * The index of task to be deleted
     */
    private final int index;

    /**
     * Instantiates a new Delete command.
     *
     * @param index index of task to be deleted
     */
    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList<Task> tasks, Ui ui, Storage storage) {
        String output = "";
        try {
            Task task = tasks.remove(this.index);
            output += "I've removed the task:\n" + task;
            storage.updateStorage(tasks);
        } catch (IndexOutOfBoundsException e) {
            output = "Could not delete task as it was not found.";
        }

        return output;
    }
}
