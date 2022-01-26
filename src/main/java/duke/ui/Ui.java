package duke.ui;

import duke.task.Task;

import java.util.Arrays;
import java.util.List;

/**
 * Encapsulates the user interaction logic.
 */
public class Ui {
    private static final String TEXT_LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    private static final String TEXT_GREETING = "Hello! I'm Duke\n" + "What can I do for you?";
    private static final String TEXT_GOODBYE = "Bye. Hope to see you again soon!";
    private static final String TEXT_DIVIDER = "____________________________________________________________";
    private static final String TEXT_ACKNOWLEDGE_LIST = "Here are the tasks in your list:";
    private static final String TEXT_ACKNOWLEDGE_MARK = "Nice! I've marked this task as done:";
    private static final String TEXT_ACKNOWLEDGE_UNMARK = "OK, I've marked this task as not done yet:";
    private static final String TEXT_ACKNOWLEDGE_DELETE = "Noted. I've removed this task:";
    private static final String TEXT_ACKNOWLEDGE_TASK = "Got it. I've added this task:";

    /**
     * Displays the Duke logo and the greeting message.
     */
    public void greet() {
        printDivider();
        printTabbed(TEXT_LOGO, 1);
        System.out.println();
        printTabbed(TEXT_GREETING, 1);
        printDivider();
        System.out.println();
    }

    /**
     * Displays the goodbye message.
     */
    public void sayGoodbye() {
        printDivider();
        printTabbed(TEXT_GOODBYE, 1);
        printDivider();
        System.out.println();
    }

    /**
     * Displays the information about the Tasks in a list.
     *
     * @param tasks The list of Tasks to be displayed.
     */
    public void listTasks(List<Task> tasks) {
        printDivider();
        printTabbed(TEXT_ACKNOWLEDGE_LIST, 1);
        for (int i = 0; i < tasks.size(); i++) {
            String entry = (i + 1) + "." + tasks.get(i).toString();
            printTabbed(entry, 1);
        }

        printDivider();
        System.out.println();
    }

    /**
     * Displays the acknowledgement message that a Task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public void acknowledgeMark(Task task) {
        printDivider();
        printTabbed(TEXT_ACKNOWLEDGE_MARK, 1);
        printTabbed(task.toString(), 3);
        printDivider();
        System.out.println();
    }

    /**
     * Displays the acknowledgement message that a Task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     */
    public void acknowledgeUnmark(Task task) {
        printDivider();
        printTabbed(TEXT_ACKNOWLEDGE_UNMARK, 1);
        printTabbed(task.toString(), 3);
        printDivider();
        System.out.println();
    }

    /**
     * Displays the acknowledgement message that a Task has been added.
     *
     * @param task     The task that has been added.
     * @param numTasks The new number of Tasks remaining.
     */
    public void acknowledgeAdd(Task task, int numTasks) {
        printDivider();
        printTabbed(TEXT_ACKNOWLEDGE_TASK, 1);
        printTabbed(task.toString(), 3);
        printTabbed("Now you have " + numTasks + " tasks in the list.", 1);
        printDivider();
        System.out.println();
    }

    /**
     * Displays the acknowledgement message that a Task has been deleted.
     *
     * @param task              The task that has been deleted.
     * @param numTasksRemaining The new number of Tasks remaining.
     */
    public void acknowledgeDelete(Task task, int numTasksRemaining) {
        printDivider();
        printTabbed(TEXT_ACKNOWLEDGE_DELETE, 1);
        printTabbed(task.toString(), 3);
        printTabbed("Now you have " + numTasksRemaining + " tasks in the list.", 1);
        printDivider();
        System.out.println();
    }

    /**
     * Displays an error message.
     *
     * @param message The error message.
     */
    public void showError(String message) {
        printDivider();
        printTabbed(message, 1);
        printDivider();
        System.out.println();
    }

    /**
     * Displays a line divider.
     */
    private void printDivider() {
        printTabbed(TEXT_DIVIDER, 0);
    }

    /**
     * Prints an indented String. The indentation consists of a single tab and an additional number of whitespaces.
     *
     * @param s       The String to be printed.
     * @param padding The amount of additional whitespace padding.
     */
    private void printTabbed(String s, int padding) {
        String[] lines = s.split("\n");
        char[] whiteSpace = new char[padding];
        Arrays.fill(whiteSpace, ' ');

        for (String line : lines) {
            System.out.println('\t' + String.valueOf(whiteSpace) + line);
        }
    }
}
