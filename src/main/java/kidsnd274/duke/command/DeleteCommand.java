package kidsnd274.duke.command;

import kidsnd274.duke.TaskList;
import kidsnd274.duke.taskobjects.Task;

public class DeleteCommand extends TaskListCommand {
    public static final String COMMAND_WORD = "delete";

    private int taskNo;

    public DeleteCommand(TaskList taskList, int taskNo) {
        super(taskList);
        this.taskNo = taskNo;
    }

    @Override
    public CommandResult runCommand() {
        Task currentTask = taskList.get(taskNo);
        String message = String.format("Removed this task:\n%s\nYou currently have %d tasks",
                currentTask.getCurrentStatus(), taskList.size());
        taskList.remove(taskNo);
        return new CommandResult(message, true);
    }
}
