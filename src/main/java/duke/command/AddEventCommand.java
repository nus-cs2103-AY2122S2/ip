package duke.command;

import duke.TaskList;
import duke.taskobjects.Event;

/**
 * Command to add an event task to the task list.
 */
public class AddEventCommand extends AddCommand {
    public static final String COMMAND_WORD = "event";

    public AddEventCommand(TaskList taskList, String description, String date) {
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
