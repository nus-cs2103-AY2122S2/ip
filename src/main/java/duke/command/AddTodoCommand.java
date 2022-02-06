package duke.command;

import duke.TaskList;
import duke.taskobjects.Todo;

/**
 * Command to add a Todo Task to the task list.
 */
public class AddTodoCommand extends AddCommand {
    public static final String COMMAND_WORD = "todo";

    public AddTodoCommand(TaskList taskList, String description) {
        super(taskList, new Todo(description));
    }

    @Override
    public CommandResult runCommand() {
        taskList.add(taskObj);
        String message = String.format("Added %s, as a %s\n%s\nYou currently have %d tasks",
                taskObj, taskObj.getType(), taskObj.getCurrentStatus(), taskList.size());
        return new CommandResult(message, true);
    }
}
