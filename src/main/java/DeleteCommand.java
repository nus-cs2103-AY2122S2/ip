/**
 * This file contains the implementation of DeleteCommand class.
 * @author Saravanan Anuja Harish
 */

public class DeleteCommand extends Command {

    /**
     * Constructor for DeleteCommand.
     * returns an instance of DeleteCommand.
     */
    DeleteCommand() {
        super();
    }

    /**
     * deletes the task at taskNum.
     * @param taskList the list of user tasks.
     * @param message the user command.
     * @throws FaultyTaskNumberException if the user inputs an invalid task number.
     */
    static void delete(TaskList taskList, String message) {

        String[] arr = message.split(" ");
        int num = Integer.valueOf(arr[1]);
        if (num > 0 && num <= taskList.numOfTasks()) {
            Task task = taskList.remove(num - 1);
            Ui.printRemoved(task);
        } else {
            throw new FaultyTaskNumberException(num);
        }
    }
}
