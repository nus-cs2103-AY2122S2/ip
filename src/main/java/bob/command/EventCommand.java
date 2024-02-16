package bob.command;

import java.time.LocalDate;
import java.time.LocalTime;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.BobException;
import bob.task.Event;
import bob.task.Task;

/**
 * {@inheritDoc}
 */
public class EventCommand extends AddCommand {
    private LocalDate date;
    private LocalTime start;
    private LocalTime end;
    public EventCommand(String taskName, LocalDate date, LocalTime start, LocalTime end) {
        super(taskName);
    }
    /**
     * {@inheritDoc}
     * Creates a new Event object and adds it to the task list and store.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage store) throws BobException {
        Task task = new Event(getTaskName(), this.date, this.start, this.end);
        return addAndUpdate(task, tasks, ui, store);
    }
}
