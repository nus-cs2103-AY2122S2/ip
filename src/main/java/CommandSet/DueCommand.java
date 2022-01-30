package CommandSet;

import Helper.TaskList;
import Helper.Ui;

/**
 * File contains the implementation of DueCommand class.
 * <h1>DueCommand</h1>
 * <p>
 * DueCommand implements the command that the would returns the list of tasks
 * that are due on a certain date or due before a certain date.
 * </p>
 *
 * @author Saravanan Anuja Harish
 */
public class DueCommand extends Command {

    // stores the command for due before.
    private static final String DUE_BEFORE = "due-before";

    // stores the command for due on.
    private static final String DUE_ON = "due-on";

    /**
     * Constructor for DueCommand.
     * returns an instance of DueCommand.
     */
    DueCommand() {
        super();
    }

    /**
     * checks the tasks that are due on date.
     * @param date the due date.
     * @param taskList the list of user tasks.
     */
    public static void tasksDueOn(String date, TaskList taskList) {
        date = date.substring(DUE_ON.length()).trim();
        Ui.printMessage(taskList.tasksDueOn(date).toString());
    }


    /**
     * checks the tasks that are due before date.
     * @param date the due date.
     * @param taskList the list of user tasks.
     */
    public static void tasksDueBefore(String date, TaskList taskList) {
        date = date.substring(DUE_BEFORE.length()).trim();
        Ui.printMessage(taskList.tasksDueBefore(date).toString());
    }
}
