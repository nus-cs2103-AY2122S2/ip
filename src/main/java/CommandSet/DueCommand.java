package commandset;

import helper.DateHandler;
import helper.TaskList;
import helper.Ui;

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

    // the size of task list if no tasks are present.
    private static final int EMPTY = 0;

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
     *
     * @param date the due date.
     * @param taskList the list of user tasks.
     */
    public static void getTasksDueOn(String date, TaskList taskList) {
        date = date.substring(DUE_ON.length()).trim();
        TaskList dueTasks = taskList.getTasksDueOn(date);
        date = new DateHandler(date).toString();

        if (dueTasks.numOfTasks() != EMPTY) {
            Ui.printMessage("The following Tasks are due on " + date + ":\n" + dueTasks.toString());
        } else {
            Ui.printMessage("Seems like you are free on " + date);
        }
    }


    /**
     * checks the tasks that are due before date.
     *
     * @param date the due date.
     * @param taskList the list of user tasks.
     */
    public static void getTasksDueBefore(String date, TaskList taskList) {
        date = date.substring(DUE_BEFORE.length()).trim();
        TaskList dueTasks = taskList.getTasksDueBefore(date);
        date = new DateHandler(date).toString();

        if (dueTasks.numOfTasks() != EMPTY) {
            Ui.printMessage("The following Tasks are due before " + date + ":\n" +dueTasks.toString());
        } else {
            Ui.printMessage("Seems like you are free before " + date);
        }

    }
}
