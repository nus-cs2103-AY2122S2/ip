package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

/**
 * Represents a command that creates a new Event task.
 */
public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";
    private String description;
    private String at;

    /**
     * Creates a new EventCommand.
     *
     * @param description Description of deadline.
     * @param at String representing the time that the event occurs.
     */
    public EventCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }

    /**
     * Creates a new event task, adds it to the list of tasks, stores the tasks into the user's storage and notifies the
     * user upon successful completion.
     *
     * @param tasks List of user's tasks.
     * @param storage Handles storage of tasks in the user's memory.
     */

    @Override
    public String execute(TaskList tasks, Storage storage) {
        Event event = new Event(description, at);
        tasks.addTask(event);
        storage.save(tasks);
        return Ui.showAddTask(event, tasks.count());
    }
}
