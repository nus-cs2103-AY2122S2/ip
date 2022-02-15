package commandset;

import exceptions.FaultyTaskNumberException;
import helper.TaskList;
import helper.Ui;
import tasks.Task;

/**
 * <h1>DeleteCommand</h1>
 * <p>
 * DeleteCommand deletes a task from the list of user tasks.
 * </p>
 *
 * @author Saravanan Anuja Harish
 */
public class DeleteCommand extends Command {

    // stores the value of space.
    private static final String SPACE = " ";

    // the index of the task number in the split array.
    private static final int TASK_NUM_IDX = 1;
  
    /**
     * Constructor for DeleteCommand.
     * returns an instance of DeleteCommand.
     */
    DeleteCommand() {
        super();
    }

    /**
     * deletes the task at taskNum.
     *
     * @param taskList the list of user tasks.
     * @param message the user command.
     * @throws FaultyTaskNumberException if the user inputs an invalid task number.
     */
    public static void delete(TaskList taskList, String message) {

        assert message.contains(SPACE) : "Must contain space to find the task number.";
        String[] arr = message.split(SPACE);
        int num = Integer.valueOf(arr[TASK_NUM_IDX]);

        if (num > 0 && num <= taskList.numOfTasks()) {
            Task task = taskList.remove(num - 1);  // to adjust with index starting with 0.
            Ui.printRemoved(task);
        } else {
            throw new FaultyTaskNumberException(num);
        }
    }
}
