package duke.command;

import java.time.LocalDate;

import duke.TaskList;
import duke.taskobjects.Task;
import duke.taskobjects.TaskWithDate;

/**
 * Command to edit the date for Deadline and Event tasks
 */
public class PostponeTaskCommand extends TaskListCommand {
    public static final String COMMAND_WORD = "postpone";

    private final int taskNo;
    private final LocalDate newDate;

    /**
     * Creates a PostponeTaskCommand object.
     *
     * @param taskList Task list where a task's date will be postponed.
     * @param taskNo The task with the same ID will be marked as done.
     * @param newDate New date for event or postpone tasks
     */
    public PostponeTaskCommand(TaskList taskList, int taskNo, LocalDate newDate) {
        super(taskList);
        this.taskNo = taskNo;
        this.newDate = newDate;
    }

    @Override
    public CommandResult runCommand() {
        Task temporaryTask = taskList.get(taskNo);
        if (!(temporaryTask instanceof TaskWithDate)) {
            return new CommandResult("Error occured, task not a date", false);
        }
        TaskWithDate currentTask = (TaskWithDate) temporaryTask;
        currentTask.changeDate(newDate);
        String message = "Date changed to " + currentTask.getDate() + " for task:\n"
                + currentTask.getCurrentStatus();
        return new CommandResult(message, true);
    }
}
