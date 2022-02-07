package command;

import java.time.format.DateTimeParseException;

import exception.DukeException;
import storage.Storage;
import task.Deadline;
import task.Task;
import task.TaskList;
import ui.Response;
import ui.Ui;

/**
 * Represents a type of Command - Deadline.
 * Processes, stores and saves the deadline task.
 */
public class DeadlineCommand extends Command {
    protected Task deadline;

    /**
     * Class constructor.
     * Instantiates a new instance of deadline.
     *
     * @param description Description of deadline
     * @param by Date/Time of the deadline
     * @throws DukeException If by param fails to parse
     */
    public DeadlineCommand(String description, String by) throws DukeException {
        try {
            this.deadline = new Deadline(description, by);
        } catch (DateTimeParseException e) {
            throw new DukeException("date or time was not formatted correctly. Make sure it is yyyy-MM-dd");
        }
    }

    /**
     * Adds the deadline task to the TaskList instance and saves the new list of tasks.
     * Displays the necessary bot response.
     *
     * @param tasks TaskList which stores the list of tasks
     * @param ui Ui to display necessary responses
     * @param storage Storage to perform caching features
     * @throws DukeException Throws exception related commands
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(deadline);
        try {
            storage.store(tasks);
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
        this.response = new Response(ui.getResponseMessage("deadline"),
                ui.getTaskMessage(deadline),
                ui.getTasksCountMessage(tasks));
    }
}
