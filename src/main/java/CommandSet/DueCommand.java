package CommandSet;

/**
 * File contains the implementation of DueCommand class.
 * @author Saravanan Anuja Harish
 */

import Helper.TaskList;
import Helper.Ui;

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
     * @throw WrongDateArgumentException if the user inputs an invalid date.
     * @throw WrongTimeArgumentException if the user inputs an invalid time.
     */
    public static void dueOn(String date, TaskList taskList) {
        date = date.substring(DUE_ON.length()).trim();
        Ui.printMessage(taskList.tasksDueOn(date).toString());
    }


    /**
     * checks the tasks that are due before date.
     * @param date the due date.
     * @param taskList the list of user tasks.
     * @throw WrongDateArgumentException if the user inputs an invalid date.
     * @throw WrongTimeArgumentException if the user inputs an invalid time.
     */
    public static void dueBefore(String date, TaskList taskList) {
        date = date.substring(DUE_BEFORE.length()).trim();
        Ui.printMessage(taskList.tasksDueBefore(date).toString());
    }
}
