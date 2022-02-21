package duke.command;

import java.time.LocalDate;

import duke.TaskList;
import duke.taskobjects.Event;

/**
 * Command to add an event task to the task list.
 */
public class AddEventCommand extends AddCommand {
    public static final String COMMAND_WORD = "event";

    /**
     * Creates an AddEventCommand object.
     *
     * @param taskList Task list where the Event Task will be added to.
     * @param description Description of the task.
     * @param date Date of the task.
     */
    public AddEventCommand(TaskList taskList, String description, LocalDate date) {
        super(taskList, new Event(description, date));
    }

    @Override
    public CommandResult runCommand() {
        taskList.add(taskObj);
        String message = String.format("Added %s, as a %s\n%s\nYou currently have %d tasks",
                taskObj, taskObj.getType(), taskObj.getCurrentStatus(), taskList.size());
        return new CommandResult(message, true);
    }
}
