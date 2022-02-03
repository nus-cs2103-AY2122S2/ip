package athena.commands;

import java.time.LocalDateTime;

import athena.tasks.TaskList;
import athena.ui.Ui;

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
     * Creates a new deadline task in the given TaskList and outputs the results to the
     * given Ui.
     *
     * @param ui ui instance to display outputs through.
     * @param taskList taskList instance to create the deadline task in.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        int taskNumber = taskList.addDeadline(taskName, dueDateTime);
        ui.sayTaskAddingLines(taskNumber);
    }
}
