package bob.command;

import bob.Task.Todo;
import bob.exception.BobException;
import bob.TaskList;
import bob.Ui;
import bob.Storage;
import bob.Task.Task;
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
    public void execute(TaskList tasks, Ui ui, Storage store) throws BobException {
        Task task = new Todo(getTaskName());
        addAndUpdate(task, tasks, ui, store);
    }
}
