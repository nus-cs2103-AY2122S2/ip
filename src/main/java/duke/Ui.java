package duke;

import java.util.ArrayList;

public class Ui {

    public Ui() {

    }

    /**
     * Returns a styled message with front and back styling
     *
     * @param msg the message to be styled
     * @return    the styled message
     */
    public String formatMsg(String msg) {
        String startFormat = "####################\n";
        String endFormat = "####################";
        return startFormat + msg + endFormat;
    }

    /**
     * Returns a styled welcome message upon launch
     */
    public void displayWelcomeMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String welcomeMsg = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(formatMsg(welcomeMsg));
    }

    /**
     * Returns a styled exit message on exit
     */
    public void displayExitMsg() {
        System.out.println(formatMsg("Bye. Hope to see you again soon!\n"));
    }

    /**
     * Returns string of stored data as an indexed list
     *
     * @param data arrayList of Task data
     * @return     data as a list in a single string
     */
    public void displayTaskList(ArrayList<Task> data) {
        String renderStr = "";
        for (int i = 0; i < data.size(); i++) {
            String dataStr = String.format("%d. ", i+1)
                    + data.get(i)
                    + " \n";
            renderStr += dataStr;
        }
        System.out.println(formatMsg(renderStr));
    }

    /**
     * Display message after marking task as complete
     *
     * @param task Task to be marked as complete
     */
    public void displayMarkMsg(String task) {
        String markMsg = "Nice! I've marked this task as done:\n"
                + task + "\n";
        System.out.println(formatMsg(markMsg));
    }

    /**
     * Display message after marking task as incomplete
     *
     * @param task Task to be marked as incomplete
     */
    public void displayUnmarkMsg(String task) {
        String unmarkMsg = "Nice! I've marked this task as NOT done:\n"
                + task + "\n";
        System.out.println(formatMsg(unmarkMsg));
    }

    public void displayListedText(Task task, int size) {
        String output = " Got it. I've added this task:\n   "
                + task.toString()
                + "\n Now you have "
                + size
                + " tasks in the list.\n";
        System.out.println(formatMsg(output));
    }

    /**
     * Display deletion message after deleting task
     *
     * @param deletedTask Task that was deleted
     * @param size Number of tasks remaining in list
     */
    public void displayDeletedMessage(Task deletedTask, int size) {
        String output = " Noted. I've removed this task:\n"
                + "  "
                + deletedTask
                + "\n Now you have "
                + size
                + " tasks in the list.\n";
        System.out.println(formatMsg(output));
    }

    public void displayLoadError() {

    }
}