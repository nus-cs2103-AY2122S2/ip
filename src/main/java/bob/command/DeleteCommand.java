package bob.command;

import bob.exception.BobException;
import bob.Storage;
import bob.Task.Task;
import bob.TaskList;
import bob.Ui;

public class DeleteCommand extends Command {
    public int index;
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws BobException {
        Task toDelete = tasks.getTask(index);
        tasks.deleteTask(toDelete);
        store.updateStore(tasks);
        ui.deletedTask();
        ui.printTask(toDelete);
    }
}
