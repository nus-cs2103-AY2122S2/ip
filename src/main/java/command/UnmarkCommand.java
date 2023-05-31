package command;

import exception.DukeException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Response;
import ui.Ui;

/**
 * Represents a type of Command - Unmark.
 * Marks a task as incomplete.
 */
public class UnmarkCommand extends Command {
    protected int index;

    /**
     * Class constructor.
     *
     * @param index Position of task in ArrayList tasks to update
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks a task as incomplete.
     * Displays the necessary bot response.
     *
     * @param tasks TaskList which stores the list of tasks
     * @param ui Ui to display necessary responses
     * @param storage Storage to perform caching features
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            if (this.index > 0 && this.index <= tasks.size()) {
                Task task = tasks.get(index - 1);
                task.unmark();
                storage.store(tasks);
                this.response = new Response(ui.getResponseMessage("unmark"),
                        ui.getTaskMessage(task));
            } else {
                this.response = new Response(ui.getInvalidRange());
            }
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
