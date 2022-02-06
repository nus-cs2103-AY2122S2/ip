package duke.command;

import duke.TaskList;
import duke.taskobjects.Deadline;

/**
 * Command to add a deadline task to the task list.
 */
public class AddDeadlineCommand extends AddCommand {
    public static final String COMMAND_WORD = "deadline";

    public AddDeadlineCommand(TaskList taskList, String description, String date) {
        super(taskList, new Deadline(description, date));
    }

    @Override
    public CommandResult runCommand() {
        taskList.add(taskObj);
        String message = String.format("Added %s, as a %s\n%s\nYou currently have %d tasks",
                taskObj, taskObj.getType(), taskObj.getCurrentStatus(), taskList.size());
        return new CommandResult(message, true);
    }
}
