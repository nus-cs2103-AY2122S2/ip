package command;

import exception.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Response;
import ui.Ui;

/**
 * Represents a type of Command - Delete.
 * Processes and removes a task from the TaskList.
 */
public class DeleteCommand extends Command {

    protected int index;

    /**
     * Class constructor.
     *
     * @param index Position of ArrayList to delete from
     * @throws DukeException If by param fails to parse
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Removes a task from the TaskList instance and saves the updated list of tasks.
     * Displays the necessary bot response.
     *
     * @param tasks TaskList which stores the list of tasks
     * @param ui Ui to display necessary responses
     * @param storage Storage to perform caching features
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.index > 0 && this.index <= tasks.size()) {
            String taskMessage = ui.getTaskMessage(tasks.get(index - 1));
            tasks.removeTask(this.index - 1);
            try {
                storage.store(tasks);
                this.response = new Response(ui.getResponseMessage("delete"),
                        taskMessage,
                        ui.getTasksCountMessage(tasks));
            } catch (DukeException e) {
                throw new DukeException(e.getMessage());
            }
        } else {
            this.response = new Response(ui.showInvalidRange());
        }
    }

}
