package duke.ui;

import java.util.Scanner;

import duke.info.task.Calendar;
import duke.info.task.Task;
import duke.utils.Text;

public class Ui {

    private final Scanner sc;

    /**
     * Constructs an Ui object with a new Scanner
     * that takes input from System.in
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Displays the welcome message specified in
     * Text.java in the duke.utils package
     */

    public void showWelcome() {
        System.out.println(Text.TEXT_WELCOME);
    }

    /**
     * Returns the next line of input from
     * the Scanner object
     * @return the String command from I/O
     */

    public String readCommand() {
        return this.sc.nextLine();
    }

    /**
     * Prints the text divider as specified in
     * Text.java in the duke.utils package
     */
    public void showLine() {
        System.out.println(Text.TEXT_DIVIDER);
    }

    /**
     * Prints the loading error as specified in
     * Text.java in the duke.utils package
     */
    public void showLoadingError() {
        showLine();
        System.out.println(Text.TEXT_LOADING_ERROR);
        showLine();
    }

    /**
     * Prints the error message specified by errorMessage
     * @param errorMessage - the error message to print
     */
    public String showError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Prints the goodbye message as specified in Text.java
     * in the duke.utils.package
     */
    public String showGoodbye() {
        return Text.TEXT_GOODBYE_MESSAGE;
    }

    /**
     * Prints the toString() format of the specified calendar
     * object
     * @param calendar - the calendar to be printed
     */
    public String showCalendar(Calendar calendar) {
        return calendar.toString();
    }

    /**
     * Prints the success message indicating the task was
     * successfully added to the calendar. Also displays
     * the total number of tasks in the calendar after the
     * addition.
     * @param addedTask - the task that was added
     * @param numOfTasks - the total number of tasks
     */
    public String showTaskAdded(Task addedTask, int numOfTasks) {
        return String.format(Text.TEXT_TASK_ADDED, addedTask, numOfTasks);
    }

    /**
     * Prints the success message indicating the specified
     * task was marked as complete in the calendar.
     * @param taskString - toString() of the task marked complete
     */
    public String showTaskComplete(String taskString) {
        return String.format(Text.TEXT_MARKED, taskString);
    }

    /**
     * Prints the success message indicating the specified task
     * was marked as yet to be completed in the calendar.
     * @param taskString - toString() of the task marked incomplete
     */
    public String showTaskIncomplete(String taskString) {
        return String.format(Text.TEXT_UNMARKED, taskString);
    }

    /**
     * Prints the success message indicating the specified task has
     * been deleted from the calendar.
     * @param taskString - toString() of the task that was deleted
     */
    public String showTaskDeleted(String taskString) {
        return String.format(Text.TEXT_DELETED, taskString);
    }
}
