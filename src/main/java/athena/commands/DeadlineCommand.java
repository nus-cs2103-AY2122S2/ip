package athena.commands;

import java.time.LocalDateTime;

import athena.tasks.TaskList;
import athena.ui.Messages;

/**
 * Represents a deadline command given to Athena by the user. When executed, sets a new
 * deadline according to the given task name and due date.
 */
public class DeadlineCommand extends Command {
    private final String taskName;
    private final LocalDateTime dueDateTime;

    /**
     * Constructs a new DeadlineCommand instance according to the given parameters.
     *
     * @param taskName The name of the deadline task.
     * @param dueDateTime The due date and time of the deadline task.
     */
    public DeadlineCommand(String taskName, LocalDateTime dueDateTime) {
        this.taskName = taskName;
        this.dueDateTime = dueDateTime;
    }

    /**
     * Creates a new deadline task in the given TaskList and returns output.
     *
     * @param taskList taskList instance to create the deadline task in.
     * @return Command output.
     */
    @Override
    public String execute(TaskList taskList) {
        int taskNumber = taskList.addDeadline(taskName, dueDateTime);
        return Messages.getTaskAddingDialog(taskList, taskNumber);
    }
}
