package command;

import exception.DukeException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Response;
import ui.Ui;

/**
 * Represents a type of Command - Mark.
 * Marks a task as completed.
 */
public class MarkCommand extends Command {
    protected int index;

    /**
     * Class constructor.
     *
     * @param index Position of task in ArrayList tasks to update
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks a a task as completed.
     * Displays the necessary bot response.
     *
     * @param tasks TaskList which stores the list of tasks
     * @param ui Ui to display necessary responses
     * @param storage Storage to perform caching features
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.index > 0 && this.index <= tasks.size()) {
            Task task = tasks.get(index - 1);
            task.mark();
            storage.store(tasks);
            this.response = new Response(ui.getResponseMessage("mark"),
                    ui.getTaskMessage(task));
        } else {
            ui.showInvalidRange();
        }
    }

}
