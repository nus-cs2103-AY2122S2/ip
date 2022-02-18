package commandset;

import exceptions.FaultyTaskNumberException;
import helper.TaskList;
import helper.Ui;

/**
 * <h1>Marking</h1>
 * <p>
 * Marking class implements the methods are mark tasks as done or
 * unmark them.
 * </p>
 * @author Saravanan Anuja Harish
 */
public class Marking extends Command {

    // stores the value of space.
    private static final String SPACE = " ";

    // the index of the task number in the split array.
    private static final int TASK_NUM_IDX = 1;

    // smallest task number
    private static final int TASK_START_NUM = 1;

    /**
     * Constructor for Marking.
     * returns an instance of Marking.
     */
    Marking() {
        super();
    }

    /**
     * marks a task as done.
     *
     * @param message the user command.
     * @param taskList the list of user tasks.
     * @throws FaultyTaskNumberException if the user inputs an invalid task number.
     */
    public static void markTask(String message, TaskList taskList) {

        String[] arr = message.split(SPACE);
        int num = Integer.valueOf(arr[TASK_NUM_IDX]);

        if (num <= taskList.numOfTasks() && num >= TASK_START_NUM) {
            taskList.get(num - 1).markDone();                // to adjust with index starting with 0.
            Ui.printTaskCompleted(taskList.get(num - 1));    // to adjust with index starting with 0.
        } else {
            throw new FaultyTaskNumberException(num);
        }
    }

    /**
     * marks a task as not done.
     *
     * @param message the user command.
     * @param taskList the list of user tasks.
     * @throws FaultyTaskNumberException if the user inputs an invalid task number.
     */
    public static void unmarkTask(String message, TaskList taskList) {

        String[] arr = message.split(SPACE);
        int num = Integer.valueOf(arr[TASK_NUM_IDX]);

        if (num > 0 && num <= taskList.numOfTasks()) {
            taskList.get(num - 1).unMarkDone();         // to adjust with index starting with 0.
            Ui.printUnmarked(taskList.get(num - 1));    // to adjust with index starting with 0.
        } else {
            throw new FaultyTaskNumberException(num);
        }
    }

}
