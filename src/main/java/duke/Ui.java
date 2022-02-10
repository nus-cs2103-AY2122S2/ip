package duke;
import java.util.Scanner;

public class Ui {

    private Scanner input = new Scanner(System.in);

    /**
     * Show welcome message
     */
    public String showWelcomeMessage() {
        String welcomeMsg = "Hello! I'm Sumikko :)\n" + "What can I do for you?";
        return welcomeMsg;
    }

    /**
     * Read user input
     *
     * @return
     */
    public String readCommand() {
        String userInput = input.nextLine();
        return userInput;
    }

    /**
     * Show error in finding file
     */
    public String showFileError() {
        String errorMsg = "No task list file found! Creating a new file for you :)";
        return errorMsg;
    }

    /**
     * Show that index keyed in is out of list
     */
    public String showOutOfArray() {
        String outOfArrayMsg = "Number of task is out of list!";
        return outOfArrayMsg;
    }

    /**
     * Show task added
     *
     * @param task
     * @param size
     */
    public String showAddTask(Task task, int size) {
        String addTaskMsg = "Got it. I've added this task:\n" + task + "\n"
                + "Now you have " + size + " tasks in the list.";
        return addTaskMsg;
    }

    /**
     * Show task marked as done
     *
     * @param task
     */
    public String showMarkTask(Task task) {
        String markTaskMsg = "Nice! I've marked this task as done: \n" + task;
        return markTaskMsg;
    }

    /**
     * Show task marked as undone
     *
     * @param task
     */
    public String showUnmarkTask(Task task) {
        String unmarkTaskMsg = "OK, I've marked this task as not done yet!\n" + task;
        return unmarkTaskMsg;

    }

    /**
     * Show task deleted
     *
     * @param task
     * @param size
     */
    public String showDeleteTask(Task task, int size) {
        String deleteMsg = "Noted. I've removed this task:\n" + task
                + "\nNow you have " + size + " tasks in the list.";
        return deleteMsg;
    }

    /**
     * Show all task in list
     *
     * @param tasks
     */
    public String showTaskList(TaskList tasks) {

        return tasks.toString();
    }

    /**
     * Show exit message
     *
     */
    public String showBye() {
        String byeMsg = "Bye bye. See you next time!\n"
                + "Closing application in 3 seconds.....";
        return byeMsg;
    }

    /**
     * Show error message
     *
     * @param message
     */
    public String showError(String message) {
        String errorMsg = "Error: " + message;
        return errorMsg;
    }

    /**
     *
     * @param tasks list of tasks in the data
     * @return tasks found
     */
    public String showFindTask(TaskList tasks) {
        String findTaskMsg = "Here are the matching tasks in your list:\n" + tasks;
        return findTaskMsg;
    }

    /**
     *
     * @param tasks list of task that are approaching the deadline
     * @return reminder message
     */
    public String showReminder(TaskList tasks, String date) {
        String showReminderMsg = "These are the task that will be due by " + date + "\n" + tasks;
        return showReminderMsg;
    }

}
