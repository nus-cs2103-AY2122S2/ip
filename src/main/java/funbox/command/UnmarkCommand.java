package funbox.command;

import funbox.exception.FunBoxExceptions;
import funbox.util.Ui;
import funbox.util.TaskList;
import funbox.util.Storage;

import java.io.IOException;

/**
 * Deal with handling command for UnmarkCommand.
 */
public class UnmarkCommand extends Command {
    int index;

    /**
     * The constructor for UnmarkCommand.
     *
     * @param index The index of the task which should be marked as done.
     */
    public UnmarkCommand(int index) {
        super(false);
        this.index = index;
    }

    /**
     * Execute the command to mark the tasks in the list as not done.
     *
     * @param taskList List of tasks.
     * @param ui Interface which interact with users.
     * @param storage Stores user tasks locally.
     * @return Returns a string to be displayed to the user.
     * @throws FunBoxExceptions If (index - 1) > taskList.getSize() or (index - 1) < 0.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws FunBoxExceptions, IOException {
        String result;
        boolean isGreaterThanList = (index - 1) > taskList.getSize();
        boolean isNegative = (index - 1) < 0;

        if (isGreaterThanList) {
            throw new FunBoxExceptions("The index entered is larger than the list!");
        }

        if (isNegative) {
            throw new FunBoxExceptions("The index cannot be negative!");
        }

        int currIndex = index - 1;
        taskList.setTaskUndone(currIndex);
        result = ui.printMarkUndone() + "\n" + taskList.getTask(ui, currIndex);
        storage.unmarkTask(currIndex);
        return result;
    }
}
