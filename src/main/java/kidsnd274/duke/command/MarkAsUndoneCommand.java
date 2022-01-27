package kidsnd274.duke.command;

import kidsnd274.duke.TaskList;
import kidsnd274.duke.taskobjects.Task;

/**
 * Command to mark a task as undone
 */
public class MarkAsUndoneCommand extends TaskListCommand {
    public static final String COMMAND_WORD = "unmark";

    private final int taskNo;

    public MarkAsUndoneCommand(TaskList taskList, int taskNo) {
        super(taskList);
        this.taskNo = taskNo;
    }

    @Override
    public CommandResult runCommand() {
        Task currentTask = taskList.get(taskNo);
        currentTask.markAsUndone();
        String message = "Marked as undone:\n" + currentTask.getCurrentStatus();
        return new CommandResult(message, true);
    }
}
