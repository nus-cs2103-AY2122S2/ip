package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.BobException;
import bob.task.Task;
/**
 * {@inheritDoc}
 */
public class DeleteCommand extends Command {
    private int index;
    public DeleteCommand(int index) {
        this.index = index;
    }
    /**
     * {@inheritDoc}
     * Removes the task from the task list and updates the store.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage store) throws BobException {
        Task toDelete = tasks.getTask(index);
        tasks.deleteTask(toDelete);
        store.updateStore(tasks);
        StringBuilder reply = new StringBuilder();
        reply.append(ui.deletedTask() + "\n");
        reply.append(ui.printTask(toDelete));
        return reply.toString();
    }
}
