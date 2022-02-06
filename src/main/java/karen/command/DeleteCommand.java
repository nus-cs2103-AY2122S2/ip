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

    public DeleteCommand(int inputIndex) {
        taskIndex = inputIndex;
    }

    /**
     * Deletes Task at index taskIndex from Storage. Saves the current state of taskList to local file directory.
     *
     * @param ui To control outputs related to execution
     * @param storage To access and modify Tasks stored in Storage
     * @return String result of output from successful execution of Command
     * @throws KarenException if Task is not within the index of the Storage
     */
    @Override
    public String execute(Ui ui, Storage storage) throws KarenException {
        try {
            Task item = storage.getTask(taskIndex);
            storage.deleteTask(taskIndex);
            storage.saveTasks();

            return ui.displayUserInput(ui.formatCount("removed", item, storage.getTaskCount()));
        } catch (IndexOutOfBoundsException err) {
            throw new KarenException(
                    String.format("Are you sure that [%d] is even in the 'list' command?", taskIndex + 1));
        }

    }
}
