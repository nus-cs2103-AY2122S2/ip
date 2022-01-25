package kidsnd274.duke.command;

import kidsnd274.duke.TaskList;

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
