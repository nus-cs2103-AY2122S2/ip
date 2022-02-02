package duke.command;

import duke.TaskList;
import duke.taskobjects.Task;

/**
 * Command to mark a task as done
 */
public class MarkAsDoneCommand extends TaskListCommand {
    public static final String COMMAND_WORD = "mark";

    private final int taskNo;

    /**
     * Default constructor for MarkAsDoneCommand
     * @param taskList Provided task list where a task inside will be marked as done
     * @param taskNo The task with the same ID will be marked as done
     */
    public MarkAsDoneCommand(TaskList taskList, int taskNo) {
        super(taskList);
        this.taskNo = taskNo;
    }

    @Override
    public CommandResult runCommand() {
        Task currentTask = taskList.get(taskNo);
        currentTask.markAsDone();
        String message = "Marked as done:\n" + currentTask.getCurrentStatus();
        return new CommandResult(message, true);
    }
}
