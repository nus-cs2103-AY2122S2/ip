package duke.util;

import static duke.commons.core.CliComponents.TEXT_BAR_LINE;
import static duke.commons.core.Messages.MESSAGE_EMPTY_TASK_LIST;

import java.util.List;

import duke.task.Task;

/**
 * Represents the User Interface
 */
public final class ResponseFormatter {

    private ResponseFormatter() {
    }

    /**
     * Main printing method that formats the output with TEXT_BAR_LINE pre and post output
     * Most pre-defined printing methods in this class ultimately calls this method
     * for the actual printing
     *
     * @param message string to be printed
     * @return formated response
     */
    public static String printMessage(String message) {
        return TEXT_BAR_LINE + "\n" + message + "\n" + TEXT_BAR_LINE;
    }

    /**
     * Prints a greeting with a custom bot name
     * @param botName custom bot name
     * @return greeting String
     */
    public static String printGreeting(String botName) {
        String greeting = "Hello! I'm " + botName + "\nWhat can I do for you?";
        return printMessage(greeting);
    }

    public static String printBye() {
        return printMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a message that describes handling an unknown command
     * @return String representing an "I don't know what that means" message
     */
    public static String printDontKnowCommand() {
        String message = "OOPS!!! I'm sorry, but I don't know what that means :-(\nPlease try again:";
        return printMessage(message);
    }

    /**
     * Prints the feedback footer, designed for feedback after handling a user command
     * @param message message to be printed
     * @param targetTask Task object that is involved in the user command
     * @param tasks List of Tasks for looping through to be printed
     * @return custom string representing the feedback footer
     */
    public static String printFeedbackFooter(String message, Task targetTask, List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(TEXT_BAR_LINE);
        sb.append("\n");
        sb.append(message);
        sb.append("\n");
        sb.append(targetTask.getTaskIcon()).append(" [").append(targetTask.isDone()).append("]").append(targetTask);
        sb.append("\n");
        sb.append(printNoOfTasks(tasks));
        sb.append("\n");
        sb.append(TEXT_BAR_LINE);

        return sb.toString();
    }

    /**
     * Prints details of a Duke Exception, along with a hint to resolve the error
     *  @param e    Exception object
     * @param hint string message containing hint to resolve the error
     * @return Exception message with hint
     */
    public static String printDukeException(Exception e, String hint) {
        StringBuilder sb = new StringBuilder();
        sb.append(TEXT_BAR_LINE).append("\n");
        sb.append(e.getMessage()).append("\n");
        sb.append(hint).append("\n");
        sb.append(TEXT_BAR_LINE).append("\n");

        return sb.toString();
    }

    /**
     * @param tasks List of tasks to be printed
     */
    public static String printList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return MESSAGE_EMPTY_TASK_LIST;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(TEXT_BAR_LINE).append("\n");
        sb.append("Here are the tasks in your list:").append("\n");

        return loopList(tasks, sb);
    }

    /**
     * @param tasks List of tasks to be printed
     * @return list of found Tasks
     */
    public static String printFoundList(List<Task> tasks, String key) {
        StringBuilder sb = new StringBuilder();
        sb.append(TEXT_BAR_LINE).append("\n");
        sb.append("Here are the tasks I have found matching \"").append(key).append("\":").append("\n");

        return loopList(tasks, sb);
    }

    private static String loopList(List<Task> tasks, StringBuilder sb) {
        for (int i = 1; i <= tasks.size(); i++) {
            Task curr = tasks.get(i - 1);
            sb.append(i).append(". ");
            sb.append(printTask(curr)).append("\n");
        }
        sb.append(TEXT_BAR_LINE);
        return sb.toString();
    }

    /**
     * @param curr Task object to be printed
     */
    private static String printTask(Task curr) {
        return curr.getTaskIcon() + " [" + curr.isDone() + "] " + curr;
    }

    /**
     * Prints tasks in custom format without TEXT_BAR_LINEs
     * Should be called directly outside of this class as this is intended as an
     * internal helper class
     * with no extra TEXT_BAR_LINE formatting pre and post output
     *
     * @param tasks List of Task objects to be printed
     * @return custom string of number of tasks in the list
     */
    private static String printNoOfTasks(List<Task> tasks) {
        return "Now you have " + tasks.size() + " tasks in the list";
    }
}
