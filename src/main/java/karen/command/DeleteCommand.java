package karen.command;

import karen.KarenException;
import karen.Storage;
import karen.Ui;
import karen.task.Task;

/**
 * To delete Task object from Storage.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(Ui ui, Storage storage) throws KarenException {
        try {
            Task item = storage.getTask(this.taskIndex);
            storage.deleteTask(this.taskIndex);
            storage.saveTasks();

            return ui.displayUserInput(ui.formatCount("removed", item, storage.getTaskCount()));
        } catch (IndexOutOfBoundsException err) {
            throw new KarenException(
                    String.format("Are you sure that [%d] is even in the 'list' command?", this.taskIndex + 1));
        }

    }
}
