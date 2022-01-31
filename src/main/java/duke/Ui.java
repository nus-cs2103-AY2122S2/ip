package duke;

import java.util.Scanner;

/**
 * An interface to interact with the user, take in command input
 * and output results of commands.
 */
public class Ui {
    private static final String INPUT_NAME = "You:";
    private static final String OUTPUT_NAME = "Duke:";
    private Scanner scanner;
    private TaskList taskList;

    /**
     * Create an instance of Ui.
     *
     * @param taskList to receive information of tasks.
     */
    public Ui(TaskList taskList) {
        this.scanner = new Scanner(System.in);
        this.taskList = taskList;
    }

    /**
     * Returns welcome message.
     *
     * @return string of welcome message.
     */
    public String startMessage() {
        String msg = "Hello! I am Duke.\n"
                + "Your wish is my command.\n\n";
        return OUTPUT_NAME + "\n" + msg + "\n";
    }

    /**
     * returns the list of tasks.
     *
     * @return string of a list of tasks.
     */
    public String listMessage() {
        String msg = "Here are the tasks in your list-\n" + taskList;
        return OUTPUT_NAME + "\n" + msg;
    }

    /**
     * Returns a goodbye message.
     *
     * @return string of a goodbye message.
     */
    public String endMessage() {
        String msg = "don't leave me don't leave me.";
        return OUTPUT_NAME + "\n" + msg + "\n";
    }

    /**
     * Returns newly added task information.
     *
     * @return string of a newly added task information.
     */
    public String addTaskMessage() {
        String msg = "Got it. I have added this task-\n" + taskList.getTaskDescription(taskList.size()) + "\n";
        msg += "Now you have " + taskList.size() + " tasks.";
        return OUTPUT_NAME + "\n" + msg + "\n";
    }

    /**
     * Returns latest deleted task information.
     *
     * @return string of latest deleted task information.
     */
    public String deleteMessage(String toDelete) {
        String msg = "Noted. I've removed this task-\n" + toDelete + "\n";
        msg += "Now you have " + taskList.size() + " tasks.";
        return OUTPUT_NAME + "\n" + msg + "\n";
    }

    /**
     * Returns task information that is just marked.
     *
     * @return string of task information that is just marked.
     */
    public String markMessage(int taskId) {
        String msg = "I have marked this as done.\n" + taskList.getTaskDescription(taskId);
        return OUTPUT_NAME + "\n" + msg + "\n";
    }

    /**
     * Returns task information that is just unmarked.
     *
     * @return string of task information that is just unmarked.
     */
    public String unMarkMessage(int taskId) {
        String msg = "I have unmarked this task.\n" + taskList.getTaskDescription(taskId);
        return OUTPUT_NAME + "\n" + msg + "\n";
    }

    /**
     * Reads user input.
     *
     * @return user input.
     */
    public String readUserInput() {
        System.out.println(INPUT_NAME);
        return scanner.nextLine();
    }

    /**
     * Returns the tasks that were found.
     *
     * @return string of tasks that were found.
     */
    public String findMessage(String tasks) {
        if (tasks == null) {
            return OUTPUT_NAME + "\n" + "Unable to find anything :(" + "\n";
        } else {
            String msg = "Here are the matching tasks in your list:\n" + tasks;
            return OUTPUT_NAME + "\n" + msg;
        }
    }

    /**
     * Returns error message in Ui format.
     *
     * @param error message.
     * @return error message.
     */
    public String showErrorMessage(String error) {
        return OUTPUT_NAME + "\n" + error + "\n";
    }

}
