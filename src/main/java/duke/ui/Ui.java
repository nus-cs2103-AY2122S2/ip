package duke.ui;

import java.util.ArrayList;

import duke.tasklist.TaskList;
import duke.tasks.Task;

public class Ui {
    protected static final String DIVIDER = "--------------------------------------------";

    /** Print welcome message */
    public static void printWelcomeMessage() {
        System.out.println(DIVIDER);
        System.out.println("Hello I'm Duke! \nWhat can I do for you?");
        System.out.println(DIVIDER);
    }

    /** Print exit message */
    public static void printExitMessage() {
        System.out.println(DIVIDER);
        System.out.println("Bye! Have a nice day! :)");
        System.out.println(DIVIDER);
    }

    /** Print divider */
    public static void printDivider() {
        System.out.println(DIVIDER);
    }

    /** Print add success message after task added */
    public static void printAddSuccess(TaskList taskList) {
        int lastTaskIndex = taskList.getTasks().size() - 1;
        System.out.println("Got it. I've added this task:\n" + taskList.getTasks().get(lastTaskIndex));
        System.out.println("Now you have " + taskList.getTasks().size() + " task(s) in the list");
    }

    /** Prints message to inform user no matching task with given keyword */
    public static void printNoTaskFound() {
        System.out.println("No tasks found with given keyword. Please try again!");
    }

    public static void printInvalidCommand() {
        System.out.println("☹ OOPS!!! command is invalid, please try again!");
    }

    /** Print result header for find command */
    public static void printFindResultHeader(String keyword) {
        System.out.println("Here are the matching tasks in your list containing "
                + "\"" + keyword + "\"" + ":");
    }
    /**
     * Iterates and prints through the tasks
     *
     * @param tasks the ArrayList of tasks
     */
    public static void printTasks(ArrayList<Task> tasks) {
        int count = 1;
        for (Task task : tasks) {
            System.out.println(count + ". " + task.toString());
            count++;
        }
    }
}
