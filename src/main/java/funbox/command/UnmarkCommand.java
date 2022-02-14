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
    String index;

    /**
     * The constructor for UnmarkCommand.
     *
     * @param index The index of the task which should be marked as done.
     */
    public UnmarkCommand(String index) {
        super(false);
        this.index = index;
    }

    //@@Jonas K-reused
    //Reused from https://stackoverflow.com/questions/237159/
    // whats-the-best-way-to-check-if-a-string-represents-an-integer-in-java
    /**
     * Check if String can be parsed to integer.
     *
     * @param str The string used to check if its parsable.
     * @return Returns true if string can be parsed to integer, else false.
     */
    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
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

        if (index == "" || !(isInteger(index))) {
            return ui.showError("`unmark` command should follow this format: unmark <index>");
        }

        int currIndex = Integer.parseInt(index) - 1;

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


        taskList.setTaskUndone(currIndex);
        result = ui.printMarkUndone() + "\n" + taskList.getTask(ui, currIndex);
        storage.unmarkTask(currIndex);
        return result;
    }
}
