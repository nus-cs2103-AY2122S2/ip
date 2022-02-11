package athena.commands;

import java.time.LocalDateTime;

import athena.tasks.TaskList;
import athena.ui.Messages;

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
     * Creates a new event task in the given taskList and returns the output.
     *
     * @param taskList The TaskList instance to add the event to.
     * @return Command output.
     */
    @Override
    public String execute(TaskList taskList) {
        int taskNumber = taskList.addEvent(taskName, eventDateTime);
        return Messages.getTaskAddingDialog(taskList, taskNumber);
    }
}
