package duke;

/**
 * Command to add event
 */
public class EventCommand extends Command {
    private final Event event;

    /**
     * Constructor for event command
     * @param description event description
     * @param at date/time of event
     */
    public EventCommand(String description, String at) {
        this.event = new Event(description, at);
    }

    /**
     * Executes command to add event
     * @param storage storage
     * @param tasks list of tasks
     * @param ui User interface
     */
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.add(event);
       return ui.showEvent(this.event, tasks);
    }

    /**
     * Boolean indicative of whether program should end
     * @return boolean indicative of whether program should end
     */
    public boolean isExit() {
        return false;
    }
}
