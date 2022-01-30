package athena.commands;

import athena.tasks.TaskList;
import athena.ui.Ui;

import java.time.LocalDateTime;

/**
 * Represents an event command given to Athena by the user. When executed, creates a new
 * event task according to the given task name and datetime.
 */
public class EventCommand extends Command {
    private final String taskName;
    private final LocalDateTime eventDateTime;

    /**
     * Constructs a new EventCommand instance according to the given parameters.
     *
     * @param taskName Name of the event to be created.
     * @param eventDateTime Time and date of the event.
     */
    public EventCommand(String taskName, LocalDateTime eventDateTime) {
        this.taskName = taskName;
        this.eventDateTime = eventDateTime;
    }

    /**
     * Creates a new event task in the given taskList and displays the results through the ui.
     *
     * @param ui Ui instance to display outputs through.
     * @param taskList The TaskList instance to add the event to.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        int taskNumber = taskList.addEvent(taskName, eventDateTime);
        ui.sayTaskAddingLines(taskNumber);
    }
}
