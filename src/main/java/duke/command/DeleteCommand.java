package duke.command;

import duke.TaskList;
import duke.taskobjects.Task;

/**
 * A class representing a command to delete an item from the task list.
 */
public class DeleteCommand extends TaskListCommand {
    public static final String COMMAND_WORD = "delete";

    private final int taskNo;

    /**
     * Default constructor for DeleteCommand.
     *
     * @param taskList Provided task list where a task will be removed from.
     * @param taskNo The task with the same ID will be removed from the task list.
     */
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
