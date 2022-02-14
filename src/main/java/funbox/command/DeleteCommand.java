package funbox.command;

import java.io.IOException;
import funbox.exception.FunBoxExceptions;
import funbox.util.Ui;
import funbox.util.TaskList;
import funbox.util.Storage;

public class DeleteCommand extends Command {
    int index;

    /**
     * Constructor for DeleteCommand.
     *
     * @param index The position of the tasks on the list to be deleted.
     */
    public DeleteCommand(int index) {
        super(false);
        this.index = index;
    }

    /**
     * Execute the command to delete a task from the list.
     *
     * @param taskList List of tasks.
     * @param ui Interface which interact with users.
     * @param storage Stores user tasks locally.
     * @return Returns a string to be displayed to the user.
     * @throws FunBoxExceptions If (index - 1) > taskList.getSize() or (index - 1) < 0.
     * @throws IOException Throws if file do not exist.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws FunBoxExceptions, IOException {
        assert ui != null : "ui should not be null";

        String result = "";
        int currIndex = index - 1;

        boolean isGreaterThanList = currIndex > taskList.getSize();
        boolean isNegative = currIndex < 0;
        boolean isEmptyList = taskList.getSize() == 0;

        if (isEmptyList) {
            return "Task list is empty";
        }

        if (isGreaterThanList) {
            return ui.showError("The index entered is larger than the list!");
        }

        if (isNegative) {
            return ui.showError("The index cannot be negative!");
        }


        result = taskList.delete(currIndex, ui);
        storage.deleteTask(currIndex);

        return result;
    }
}
