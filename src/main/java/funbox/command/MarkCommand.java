package funbox.command;

import funbox.exception.FunBoxExceptions;
import funbox.util.Ui;
import funbox.util.TaskList;
import funbox.util.Storage;
import java.io.IOException;

/**
 * Deal with handling command for Mark.
 */
public class MarkCommand extends Command {
    int index;

    /**
     * The constructor for MarkCommand.
     *
     * @param index The index of the task which should be marked as done.
     */
    public MarkCommand(int index) {
        super(false);
        this.index = index;
    }

    /**
     * Execute the command to mark the tasks in the list.
     *
     * @param taskList List of tasks.
     * @param ui Interface which interact with users.
     * @param storage Stores user tasks locally.
     * @return Returns a string to be displayed to the user.
     * @throws FunBoxExceptions If (index - 1) > taskList.getSize() or (index - 1) < 0.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws FunBoxExceptions, IOException {
        String result = "";
        int currIndex = index - 1;

        boolean isGreaterThanList = currIndex > taskList.getSize();
        boolean isNegative = currIndex < 0;
        boolean isEmptyList = taskList.getSize() == 0;

        if (isEmptyList) {
            return "Task list is empty";
        }

        if (isGreaterThanList) {
            throw new FunBoxExceptions("The index entered is larger than the list!");
        }

        if (isNegative) {
            throw new FunBoxExceptions("The index cannot be negative!");
        }

        taskList.setTaskDone(currIndex);
        result = ui.printMarkDone().concat(taskList.getTask(ui, currIndex));
        storage.markTask(currIndex);
        return result;
    }
}
