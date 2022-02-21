package duke.command;

import duke.TaskList;
import duke.taskobjects.Task;

/**
 * Command to mark a task as undone.
 */
public class MarkAsUndoneCommand extends TaskListCommand {
    public static final String COMMAND_WORD = "unmark";

    private final int taskNo;

    /**
     * Creates a MarkAsUndone object.
     *
     * @param taskList Task list where a task inside will be marked as undone.
     * @param taskNo The task with the same ID will be marked as undone.
     */
    public MarkAsUndoneCommand(TaskList taskList, int taskNo) {
        super(taskList);
        this.taskNo = taskNo;
    }

    @Override
    public CommandResult runCommand() {
        Task currentTask = taskList.get(taskNo);
        currentTask.markAsUndone();
        assert !currentTask.isDone() : "Task didn't get marked as undone";
        String message = "Marked as undone:\n" + currentTask.getCurrentStatus();
        return new CommandResult(message, true);
    }
}
