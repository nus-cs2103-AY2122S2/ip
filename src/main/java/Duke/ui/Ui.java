package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Task;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints greeting message when user run the program.
     */
    public void showWelcome() {
        String greet = "Hello! I'm Duke\n" + "What can I do for you?";
        System.out.println(greet);
    }

    /**
     * Reads the user command and return the list commands.
     * Command list includes the keyword and description.
     */
    public String[] readCommand() {
        String[] commandList = new String[]{sc.next(),sc.nextLine()};
        return commandList;
    }

    /**
     * Prints dash lines. For format purpose.
     */
    public void showLine() {
        System.out.println("----------------------------------");
    }

    /**
     * Prints loading error message.
     */
    public void showLoadingError() {
        System.out.println("Opps! There is loading error :<");
    }

    /**
     * Prints error message that was thrown in other class.
     */
    public void showError(String errorMsg) {
        System.out.println(errorMsg);
    }

    /**
     * Prints goodbye message when user exit the program.
     */
    public void showGoodBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints message to inform the users that task is added into the list.
     *
     * @param task task to be added into the list.
     * @param taskList the list of task.
     */
    public void showTaskAdded(Task task, ArrayList<Task> taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task + "\n" + "Now you have " + taskList.size()
                + " tasks in the list.");
    }

    /**
     * Prints message to inform the users that task is deleted from the list.
     *
     * @param task task to be deleted from the list.
     * @param taskList the list of task.
     */
    public void showTaskDeleted(Task task, ArrayList<Task> taskList) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task + "\n" + "Now you have " + taskList.size()
                + " tasks in the list.");
    }

    /**
     * Displays the list of tasks the user has.
     */
    public void showList(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            System.out.println("No task for now");
        } else {
            System.out.println("Here are the tasks in your list:");
            for(int i = 1; i < taskList.size() + 1; i++){
                Task currTask = taskList.get(i - 1);
                System.out.println(i + ". " + currTask);
            }
        }
    }

    /**
     * Prints message to inform the users that task is mark as complete.
     *
     * @param currTask task to be mark.
     */
    public void showTaskMarked(Task currTask) {
        System.out.println("Nice! I've marked this task as done:\n"
                + currTask);
    }

    /**
     * Prints message to inform the users that task is unmarked as not complete.
     *
     * @param currTask task to be unmarked.
     */
    public void showTaskUnmarked(Task currTask) {
        System.out.println("OK, I've marked this task as not done yet:\n"
                + currTask);
    }

}
