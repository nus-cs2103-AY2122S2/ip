package duke;

import java.util.ArrayList;

/**
 * Class containing all UI methods
 */
public class Ui {

    /**
     * UI Constructor
     */
    public Ui() {

    }

    /**
     * Returns a styled message with front and back styling
     *
     * @param msg the message to be styled
     * @return    the styled message
     */
    public static String formatMsg(String msg) {
        assert msg != "" : "cannot format an empty msg!";
        String startFormat = "####################\n";
        String endFormat = "####################";
        return startFormat + msg + endFormat;
    }

    /**
     * Returns a styled welcome message upon launch
     */
    public static String displayWelcomeMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String welcomeMsg = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        return formatMsg(welcomeMsg);
    }

    /**
     * Returns a styled exit message on exit
     */
    public String displayExitMsg() {
        return formatMsg("Bye. Hope to see you again soon!\n");
    }

    /**
     * Returns list of tasks provided in arraylist as a single string
     *
     * @param tasks ArrayList of tasks
     * @return List of tasks as a single string
     */
    public String renderTaskList(ArrayList<Task> tasks) {
        assert tasks != null || tasks.size() <= 0 : "Cannot render an empty task list!";
        String renderStr = "";
        for (int i = 0; i < tasks.size(); i++) {
            String dataStr = String.format("%d. ", i + 1)
                    + tasks.get(i)
                    + " \n";
            renderStr += dataStr;
        }
        return renderStr;
    }

    /**
     * Prints string of stored data as an indexed list
     *
     * @param tasks arrayList of Task data
     */
    public String displayTaskList(ArrayList<Task> tasks) {
        assert tasks != null || tasks.size() <= 0 : "Cannot render an empty task list!";
        return formatMsg(renderTaskList(tasks));
    }

    /**
     * Prints list of tasks found via FIND command
     *
     * @param foundTasks ArrayList of tasks found via FIND
     */
    public String displayFoundTaskList(TaskList foundTasks) {
        assert foundTasks != null || foundTasks.getSize() <= 0 : "Cannot render an empty task list!";
        String message = "Here are the matching tasks in your list:\n";
        return formatMsg(message + renderTaskList(foundTasks.getTasks()));
    }

    /**
     * Display message after marking task as complete
     *
     * @param task Task to be marked as complete
     */
    public String displayMarkMsg(String task) {
        assert task != "" : "Cannot display an empty task as marked!";
        String markMsg = "Nice! I've marked this task as done:\n"
                + task + "\n";
        return formatMsg(markMsg);
    }

    /**
     * Display message after marking task as incomplete
     *
     * @param task Task to be marked as incomplete
     */
    public String displayUnmarkMsg(String task) {
        assert task != "" : "Cannot display an empty task as unmarked!";
        String unmarkMsg = "Nice! I've marked this task as NOT done:\n"
                + task + "\n";
        return formatMsg(unmarkMsg);
    }

    /**
     * Display listed message after listing task
     *
     * @param task Task that was listed
     * @param size Number of tasks in list
     */
    public String displayListedText(Task task, int size) {
        String output = " Got it. I've added this task:\n   "
                + task.toString()
                + "\n Now you have "
                + size
                + " tasks in the list.\n";
        return formatMsg(output);
    }

    /**
     * Display deletion message after deleting task
     *
     * @param deletedTask Task that was deleted
     * @param size Number of tasks remaining in list
     */
    public String displayDeletedMessage(Task deletedTask, int size) {
        assert deletedTask != null : "Cannot display an empty task as deleted!";
        assert size >= 0 : "Number of tasks in list cannot be negative!";
        String output = " Noted. I've removed this task:\n"
                + "  "
                + deletedTask
                + "\n Now you have "
                + size
                + " tasks in the list.\n";
        return formatMsg(output);
    }

    public void displayLoadError() {

    }
}
