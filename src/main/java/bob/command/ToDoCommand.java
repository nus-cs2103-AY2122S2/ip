package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.BobException;
import bob.task.Task;
import bob.task.Todo;
/**
 * {@inheritDoc}
 */
public class ToDoCommand extends AddCommand {
    public ToDoCommand(String taskName) {
        super(taskName);
    }
    /**
     * {@inheritDoc}
     * Creates a new ToDo object and adds it to the task list and store.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage store) throws BobException {
        Task task = new Todo(getTaskName());
        return addAndUpdate(task, tasks, ui, store);
    }
}
