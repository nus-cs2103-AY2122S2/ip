package duke;

import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Encapsulates the user interface of Duke. It deals
 * with all interactions between Duke and the user.
 */
public class Ui {

    /**
     * Intialises a Ui object and shows the welcome message.
     */
    public Ui() {
        this.showWelcome();
    }

    private void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n");
    }

    /**
     * Displays an error message when saved data cannot be retrieved.
     *
     * @param errorMessage the details of the error.
     */
    public void showLoadingError(String errorMessage) {
        System.out.println("There was an error in retrieving saved data:");
        System.out.println(errorMessage);
        System.out.println("I couldn't find any data to load, so I've created an empty task list.\n");
    }

    /**
     * Displays the latest retrieved task data.
     *
     * @param taskList the task list containing the loaded task data.
     */
    public void showLoadingSuccess(TaskList taskList) {
        if (taskList.getLength() != 0) {
            System.out.println("I've retrieved your latest task list data");
            this.showTaskList(taskList);
        }
    }

    /**
     * Displays an error message when data cannot be saved into a txt file.
     *
     * @param errorMessage the details of the error.
     */
    public void showSavingError(String errorMessage) {
        System.out.println("There was an error in saving the data:");
        System.out.println(errorMessage);
        System.out.println();
    }

    /**
     * Prompts users for their next command.
     */
    public String readCommand() {
        System.out.println("What can I do for you next?");
        Scanner userInput = new Scanner(System.in);
        return userInput.nextLine();
    }

    /**
     * Displays an error message when the user entered an invalid command.
     *
     * @param errorMessage the details of the error.
     */
    public void showCommandError(String errorMessage) {
        System.out.println("Oops, there might be an error in the command entered:");
        System.out.println(errorMessage);
        System.out.println();
    }

    /**
     * Displays the contents of the task list.
     *
     * @param taskList the task list to be shown.
     */
    public void showTaskList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(taskList);
    }

    /**
     * Displays a message to show a task was
     * added to the task list.
     *
     * @param task the task that was added.
     * @param taskList the task list to which the task was added.
     */
    public void showTaskAdded(Task task, TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.getLength() + " tasks in the list.\n");
    }

    /**
     * Displays a message to show a task was marked as complete.
     *
     * @param task the task that was marked as complete.
     */
    public void showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println();
    }

    /**
     * Displays a message to show a task was marked as incomplete.
     *
     * @param task the task that was marked as incomplete.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        System.out.println();
    }

    /**
     * Displays a message to show a task was deleted.
     *
     * @param task the task that was deleted.
     * @param taskList the task list from which the task was deleted.
     */
    public void showTaskDeleted(Task task, TaskList taskList) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.getLength() + " tasks in the list.\n");
    }

    /**
     * Displays the tasks found from a FindCommand execution.
     *
     * @param findResults the tasks found, in the form of a task list.
     */
    public void showFindResult(TaskList findResults) {
        System.out.println("Here are the matching tasks in your task list:");
        System.out.println(findResults);
    }

    /**
     * Displays a goodbye message.
     */
    public void showGoodbye() {
        System.out.println("Bye! Hope to see you again!");
    }
}
