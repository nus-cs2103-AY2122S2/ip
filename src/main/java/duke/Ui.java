package duke;

import java.util.List;
import duke.task.Task;

/**
 * Class for printing UI elements.
 */
public class Ui {
    private static final String MESSAGE_INTRO = "Hello! I'm Dusk\n     What can I do for you?";
    private static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";

    /**
     * Prints the set welcome message with the borders.
     */
    public void showWelcome() {
        printContent(MESSAGE_INTRO);
    }

    /**
     * Prints the set exit message with the borders.
     */
    public void showExitMessage() {
        printContent(MESSAGE_BYE);
    }

    /**
     * Prints the top and bottom borders.
     */
    public void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints any text together with the top and bottom borders.
     *
     * @param text Text to print
     */
    public void printContent(String text) {
        String spacing = "     ";
        printLine();
        System.out.println(spacing + text);
        printLine();
        System.out.println();
    }

    /**
     * Prints the format when adding and deleting tasks.
     *
     * @param tasks List of tasks
     * @param task Task that just been added/deleted
     * @param message Template text for addition or deletion
     */
    public void printAddDeleteTaskSuccess(List<Task> tasks, Task task, String message) {
        String content = taskLine(task, message) + "\n";
        content += listSizeLine(tasks);
        printContent(content);
    }

    /**
     * Concatenates a line of a task.
     *
     * @param task Task to print
     * @param message Template text for the printed task
     * @return Combined string mainly used for printing
     */
    public String taskLine(Task task, String message) {
        return message + "\n       [" + task.getType() + "][" + task.getStatusIcon() + "] " + task.toString();
    }

    /**
     * Concatenates final line for how many tasks in the list.
     *
     * @param tasks List of tasks
     * @return String for how many tasks in the list
     */
    public String listSizeLine(List<Task> tasks) {
        return "     Now you have " + tasks.size() + " task"
                + (tasks.size() != 1 ? "s" : "") + " in the list.";
    }
}
