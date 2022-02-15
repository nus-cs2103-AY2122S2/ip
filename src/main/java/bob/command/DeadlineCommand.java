package bob.command;

import java.time.LocalDateTime;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.BobException;
import bob.task.Deadline;
import bob.task.Task;

/**
 * {@inheritDoc}
 */
public class DeadlineCommand extends AddCommand {
    private LocalDateTime dateTime;

    /**
     * Constructor for the deadline command
     * @param taskName name of the deadline to be created
     * @param dateTime date and time of the deadline
     */
    public DeadlineCommand(String taskName, LocalDateTime dateTime) {
        super(taskName);
        this.dateTime = dateTime;
    }
    /**
     * {@inheritDoc}
     * Creates a new Deadline object and adds it to the task list and store.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage store) throws BobException {
        Task task = new Deadline(getTaskName(), this.dateTime);
        return addAndUpdate(task, tasks, ui, store);
    }
}
