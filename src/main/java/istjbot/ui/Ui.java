package istjbot.ui;

import java.util.Scanner;

/**
 * Encapsulates the concept of text-based Ui representation of the bot.
 * Responsible for printing out all user messages after the command is executed.
 */
public class Ui {
    /** Scanner to read user input. */
    private Scanner sc = new Scanner(System.in);

    /**
     * Prints out a line.
     */
    public void showLine() {
        String line = "*_______________________________________________________________*";
        System.out.println(line);
    }

    /**
     * Prints out a welcome message.
     */
    public void showWelcome() {
        this.showLine();
        String welcomeMessage = "Hello! I'm IstjBot. \n" + "What can I do for you?";
        System.out.println(welcomeMessage);
        this.showLine();
    }

    /**
     * Reads and returns the command user has typed for input.
     *
     * @return String of user input.
     */
    public String readCommand() {
        String fullCommand = sc.nextLine();
        return fullCommand;
    }

    /**
     * Prints out a confirmation message that the task has been successfully added.
     *
     * @param tasksSize Number of tasks used whether to pluralize "task."
     * @param addedTask String of the task added.
     */
    public void showTaskAdded(int tasksSize, String addedTask) {
        String messageStart = "As an IstjBot, I will add this task right now. \n";
        // May refactor messageLast?
        String messageLast = "Now you have " + tasksSize + " ";
        String plural = tasksSize > 1 ? "tasks" : "task";
        messageLast += plural + " in the list.";
        System.out.println(messageStart + addedTask + "\n" + messageLast);
    }

    /**
     * Prints out a confirmation that the task has been successfully marked as done.
     *
     * @param task String of the task marked.
     */
    public void showTaskMarked(String task) {
        String message = "As an IstjBot, I've marked this task as done: \n" + task;
        System.out.println(message);
    }

    /**
     * Prints out a confirmation that the task has been successfully unmarked as not done.
     *
     * @param task String of the task unmarked.
     */
    public void showTaskUnmarked(String task) {
        String message = "As an IstjBot, I've unmarked this task: \n" + task;
        System.out.println(message);
    }

    /**
     * Prints out a confirmation message that the task has been successfully deleted.
     *
     * @param tasksSize Number of tasks used whether to pluralize "task."
     * @param deletedTask String of the task that has just been deleted.
     */
    public void showTaskDeleted(int tasksSize, String deletedTask) {
        String messageStart = "As an IstjBot, I've removed this task: \n";
        String messageLast = "Now you have " + tasksSize + " ";
        String plural = tasksSize > 1 ? "tasks" : "task";
        messageLast += plural + " in the list.";
        System.out.println(messageStart + deletedTask + "\n" + messageLast);
    }

    /**
     * Prints out messages and tasks that have been filtered with user's input date.
     *
     * @param searchList String of all tasks that are filtered with user's input date.
     */
    public void showTasksByDate(String searchList) {
        String messageStart = "As an IstjBot, I present you the task(s) with that date.";
        messageStart += searchList.isEmpty() ? "" : "\n";
        System.out.println(messageStart + searchList);
    }

    /**
     * Prints out messages and tasks that have been filtered with user's input keyword.
     *
     * @param searchList String of all tasks that are filtered with user's input keyword.
     */
    public void showTasksByKeyword(String searchList) {
        String messageStart = "As an IstjBot, I present you the task(s) with that keyword.";
        messageStart += searchList.isEmpty() ? "" : "\n";
        System.out.println(messageStart + searchList);
    }

    /**
     * Prints out all tasks that are stored.
     *
     * @param list String of all tasks.
     */
    public void showTasks(String list) {
        String messageStart = "As an IstjBot, I present you the task(s) in your list:";
        messageStart += list.isEmpty() ? "" : "\n";
        System.out.println(messageStart + list);
    }

    /**
     * Prints out a bye message to the user.
     */
    public void showBye() {
        System.out.println("Bye, I, IstjBot, will be organizing your tasks until you come back.");
        sc.close();
    }

    /**
     * Prints out the error message.
     *
     * @param errorMessage String of the error message.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
