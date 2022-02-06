package duke.util;

import java.util.List;

import duke.task.Task;

public final class Ui {

    private static final String BAR = "-".repeat(60);

    private Ui() {
    }

    /**
     * Main printing method that formats the output with BAR pre and post output
     * Most pre-defined printing methods in this class ultimately calls this method
     * for the actual printing
     * 
     * @param message string to be printed
     */
    public static void printMessage(String message) {
        System.out.println(BAR + "\n" + message + "\n" + BAR);
    }

    public static void printGreeting(String botName) {
        String greeting = "Hello! I'm " + botName + "\nWhat can I do for you?";
        printMessage(greeting);
    }

    public static void printBye() {
        printMessage("Bye. Hope to see you again soon!");
    }

    public static void printDontKnowCommand() {
        String message = "OOPS!!! I'm sorry, but I don't know what that means :-(\nPlease try again:";
        printMessage(message);
    }

    public static void printFeedbackFooter(String message, Task targetTask, List<Task> tasks) {
        System.out.println(BAR);
        System.out.println(message);
        System.out.println(targetTask.getTaskIcon() + " [" + targetTask.isDone() + "]" + targetTask);
        printNoOfTasks(tasks);
        System.out.println(BAR);
    }

    /**
     * Prints details of an Duke Exception, along with a hint to resolve the error
     * 
     * @param e    Exception object
     * @param hint string message containing hint to resolve the error
     *             e.g. "Please try again" or "Please input in format XXXX"
     */
    public static void printDukeException(Exception e, String hint) {
        System.out.println(BAR);
        System.err.println(e.getMessage());
        System.out.println(hint);
        System.out.println(BAR);
    }

    public static void printList(List<Task> tasks) {
        System.out.println(BAR);
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            Task curr = tasks.get(i - 1);
            System.out.print(i + ".");
            printTask(curr);
        }
        System.out.println(BAR);
    }

    /**
     * @param curr Task object to be printed
     */
    private static void printTask(Task curr) {
        System.out.println(curr.getTaskIcon() + " [" + curr.isDone() + "]" + curr);
    }

    /**
     * Prints tasks in custom format without bars
     * Should be called directly outside of this class as this is intended as an
     * internal helper class
     * with no extra BAR formatting pre and post output
     * 
     * @param tasks
     */
    private static void printNoOfTasks(List<Task> tasks) {
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }
}
