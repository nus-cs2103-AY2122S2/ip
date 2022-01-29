package CommandSet;

/**
 * This file contains the implementation of Marking class.
 * @author Saravanan Anuja Harish
 */
import Exceptions.FaultyTaskNumberException;
import Helper.TaskList;
import Helper.Ui;


public class Marking extends Command{

    /**
     * Constructor for Marking.
     * returns an instance of Marking.
     */
    Marking() {
        super();
    }

    /**
     * marks a task as done.
     * @param message the user command.
     * @param taskList the list of user tasks.
     * @throws FaultyTaskNumberException if the user inputs an invalid task number.
     */
    public static void mark(String message, TaskList taskList) {
        String[] arr = message.split(" ");
        int num = Integer.valueOf(arr[1]);
        if (num <= taskList.numOfTasks() && num > 0) {
            taskList.get(num - 1).markDone();
            Ui.printTaskCompleted(taskList.get(num - 1));
        } else {
            throw new FaultyTaskNumberException(num);
        }
    }

    /**
     * marks a task as not done.
     * @param message the user command.
     * @param taskList the list of user tasks.
     * @throws FaultyTaskNumberException if the user inputs an invalid task number.
     */
    public static void unmark(String message, TaskList taskList) {
        String[] arr = message.split(" ");
        int num = Integer.valueOf(arr[1]);
        if(num > 0 && num <= taskList.numOfTasks()) {
            taskList.get(num - 1).unMarkDone();
            Ui.printUnmarked(taskList.get(num - 1));
        } else {
            throw new FaultyTaskNumberException(num);
        }
    }

}
