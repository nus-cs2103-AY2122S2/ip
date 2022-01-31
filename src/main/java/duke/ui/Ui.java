package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;


/**
 * Responsible for interacting with the user by receiving input and displaying output.
 */
public class Ui {

    /**
     * Reads one line of input from the user.
     *
     * @return The user's input.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Stylises Duke output to be preceded by a divider. All Duke output is printed with this method.
     *
     * @param str The output to be stylised.
     */
    public void printWithStyle(String str) {
        System.out.println("________\n" + str);
    }

    /**
     * Displays a divider line for style purposes.
     */
    public void displayLine() {
        System.out.println("________\n");
    }

    /**
     * Displays a welcome message to the user with the Ace logo.
     */
    public void displayWelcome() {
        String logo = "\n"
                + "    ___   ____________\n"
                + "   /   | / ____/ ____/\n"
                + "  / /| |/ /   / __/   \n"
                + " / ___ / /___/ /___   \n"
                + "/_/  |_\\____/_____/   \n"
                + "                      \n";
        printWithStyle(logo + "\nHi, I'm Ace. What can I do for you?");
    }

    /**
     * Displays an exit message to the user.
     */
    public void displayGoodbye() {
        printWithStyle("See you later!");
    }

    /**
     * Displays all the user's current tasks in a numbered list form beginning at 1.
     *
     * @param tasks The ArrayList containing all user tasks.
     */
    public void displayAllTasks(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            printWithStyle("There are no tasks in your list.");
        } else {
            StringBuilder strBuilder = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                String line = i + 1 + ". " + tasks.get(i) + '\n';
                strBuilder.append(line);
            }
            printWithStyle("Here are your tasks:\n" + strBuilder.toString());
        }
    }

    /**
     * Displays the user's tasks that match a keyword in a number list form beginning at 1.
     *
     * @param tasks The ArrayList containing all matching tasks.
     */
    public void displayMatchingTasks(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            printWithStyle("There are no matching tasks in your list.");
        } else {
            StringBuilder strBuilder = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                String line = i + 1 + ". " + tasks.get(i) + '\n';
                strBuilder.append(line);
            }
            printWithStyle("Here are the matching tasks:\n" + strBuilder.toString());
        }
    }

    /**
     * Displays error messages with the stylePrint() style.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void displayError(String errorMessage) {
        printWithStyle(errorMessage);
    }

    /**
     * Displays that a task has been marked as complete.
     *
     * @param task The marked task.
     */
    public void displayMarkedTask(Task task) {
        printWithStyle("The following task has been marked as complete:\n" + task.toString());
    }

    /**
     * Displays that a task has been marked as incomplete.
     *
     * @param task The unmarked task.
     */
    public void displayUnmarkedTask(Task task) {
        printWithStyle("The following task has been marked as incomplete:\n" + task.toString());
    }

    /**
     * Displays that a task has been deleted.
     *
     * @param task The deleted task.
     */
    public void displayDeletedTask(Task task) {
        printWithStyle("The following task has been deleted:\n" + task.toString());
    }

    /**
     * Displays that a task has been added.
     *
     * @param task The added task.
     */
    public void displayAddedTask(Task task) {
        printWithStyle("The following task has been added:\n" + task.toString());
    }

    /**
     * Displays how many tasks are currently in the user's list in sentence form.
     *
     * @param tasks The ArrayList of tasks.
     */
    public void displayNumberOfTasks(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println("You have 0 tasks in your list");
        } else if (tasks.size() == 1) {
            System.out.println("You have 1 task in your list");
        } else {
            System.out.println("You have " + tasks.size() + " tasks in your list");
        }
    }
}
