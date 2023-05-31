package command;

import exception.DukeException;
import parser.Priorities;
import storage.Storage;
import task.Event;
import task.Task;
import task.TaskList;
import ui.Response;
import ui.Ui;

/**
 * Represents a type of Command - Event.
 * Processes, stores and saves the event task.
 */
public class EventCommand extends Command {
    protected Task event;

    /**
     * Class constructor.
     * Instantiates a new instance of event.
     *
     * @param description Description of event
     * @param at location of the event
     */
    public EventCommand(String description, String at, Priorities priority) {
        this.event = new Event(description, at, priority);
    }

    /**
     * Adds the event task to the TaskList instance and saves the new list of tasks.
     * Displays the necessary bot response.
     *
     * @param tasks TaskList which stores the list of tasks
     * @param ui Ui to display necessary responses
     * @param storage Storage to perform caching features
     * @throws DukeException Throws exception related commands
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.add(event);
            storage.store(tasks);
            this.response = new Response(ui.getResponseMessage("event"),
                    ui.getTaskMessage(event),
                    ui.getTasksCountMessage(tasks));
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

}
