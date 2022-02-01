package duke.command;

import duke.Ui;
import duke.Storage;
import duke.DukeDateTime;
import duke.task.Task;
import duke.task.Event;
import duke.task.TaskList;

public class EventCommand extends Command {

    private final String description;
    private final DukeDateTime at;

    /**
     * Constructs a {@code EventCommand} object.
     * @param description the description of the event
     * @param at a {@code DukeDateTime object specifying the time of the event}
     */
    public EventCommand(String description, DukeDateTime at) {
        this.description = description;
        this.at = at;
    }

    /**
     * Adds an event into the list of tasks.
     * @param tasks current list of tasks
     * @param ui the UI used
     * @param storage the storage used
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = new Event(description, at);
        tasks.add(t);
        return "Got it. I've added this task:\n" + t
                + "\nNow you have " + tasks.size() + " tasks in your list.";
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
