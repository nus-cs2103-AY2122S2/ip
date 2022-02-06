package duke.command;

import duke.TaskList;

/**
 * Command to list out all the tasks and its current status.
 */
public class ListAllTasksCommand extends TaskListCommand {
    public static final String COMMAND_WORD = "list";

    public ListAllTasksCommand(TaskList taskList) {
        super(taskList);
    }

    @Override
    public CommandResult runCommand() {
        return new CommandResult(taskList.listAll());
    }
}
