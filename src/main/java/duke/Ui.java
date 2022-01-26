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
     * Prints welcome message.
     */
    public void startMessage() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String msg = "Hello! I am duke.Duke.\n"
                + "Your wish is my command.\n\n";
        System.out.print(OUTPUT_NAME + "\n" + logo + msg + "\n");
    }

    /**
     * Prints the list of tasks.
     */
    public void listMessage() {
        String msg = "Here are the tasks in your list-\n" + taskList;
        System.out.println(OUTPUT_NAME + "\n" + msg);
    }

    /**
     * Prints a goodbye message.
     */
    public void endMessage() {
        String msg = "don't leave me don't leave me.";
        System.out.println(OUTPUT_NAME + "\n" + msg + "\n");
    }

    /**
     * Prints newly added task information.
     */
    public void addTaskMessage() {
        String msg = "Got it. I have added this task-\n" + taskList.getTaskDescription(taskList.size()) + "\n";
        msg += "Now you have " + taskList.size() + " tasks.";
        System.out.println(OUTPUT_NAME + "\n" + msg + "\n");
    }

    /**
     * Prints deleted task information.
     */
    public void deleteMessage(String toDelete) {
        String msg = "Noted. I've removed this task-\n" + toDelete + "\n";
        msg += "Now you have " + taskList.size() + " tasks.";
        System.out.println(OUTPUT_NAME + "\n" + msg + "\n");
    }

    /**
     * Prints task information that is just marked
     */
    public void markMessage(int taskId) {
        String msg = "I have marked this as done.\n" + taskList.getTaskDescription(taskId);
        System.out.println(OUTPUT_NAME + "\n" + msg + "\n");
    }

    /**
     * Prints task information that is just unmarked
     */
    public void unMarkMessage(int taskId) {
        String msg = "I have unmarked this task.\n" + taskList.getTaskDescription(taskId);
        System.out.println(OUTPUT_NAME + "\n" + msg + "\n");
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
     * Prints the tasks that were found.
     */
    public void findMessage(String tasks) {
        if (tasks == null) {
            System.out.println(OUTPUT_NAME + "\n" + "Unable to find anything :(" + "\n");
        } else {
            String msg = "Here are the matching tasks in your list:\n" + tasks;
            System.out.println(OUTPUT_NAME + "\n" + msg);
        }
    }

    /**
     * Prints error message in Ui format.
     *
     * @param error message.
     */
    public void showErrorMessage(String error) {
        System.out.println(OUTPUT_NAME + "\n" + error + "\n");
    }

}
