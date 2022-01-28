package bob.command;

import bob.exception.BobException;
import bob.Storage;
import bob.Task.Task;
import bob.Task.Deadline;
import bob.TaskList;
import bob.Ui;

import java.time.LocalDateTime;
/**
 * {@inheritDoc}
 */
public class DeadlineCommand extends AddCommand {
    public LocalDateTime dateTime;
    public DeadlineCommand(String taskName, LocalDateTime dateTime) {
        super(taskName);
        this.dateTime = dateTime;
    }
    /**
     * {@inheritDoc}
     * Creates a new Deadline object and adds it to the task list and store.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws BobException {
        Task task = new Deadline(getTaskName(), this.dateTime);
        addAndUpdate(task, tasks, ui, store);
    }
}
