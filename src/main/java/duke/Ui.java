package duke;

import task.Task;

import java.util.Scanner;

public class Ui {

    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints welcome.
     */
    public void showWelcome() {
        System.out.println("Welcome!");
    }

    /**
     * Prints Good Bye.
     */
    public void showGoodBye() {
        System.out.println("Good Bye!");
    }

    /**
     * Prints error message.
     */
    public void showError(String err) {
        System.out.println("\t" + err + "\n");
    }

    /**
     * Prints task that is marked.
     */
    public void showMarked(Task task) {
        System.out.println("\t" + task.toString() + " marked!\n");
    }

    /**
     * Prints task that is unmarked.
     */
    public void showUnmarked(Task task) {
        System.out.println("\t" + task.toString() + " unmarked!\n");
    }

    /**
     * Prints task that about to be deleted.
     */
    public void showDeleted(Task task) {
        System.out.println("\t" + task.toString() + " deleted!\n");
    }

    /**
     * Prints newly created task that is added to the program
     * @param task object
     */
    public void showAdded(Task task) {
        System.out.println("\t" + task.toString() + " added!\n");
    }

    /**
     * gets and returns the user input
     * @return user input
     */
    public String getCommand() {
        return sc.nextLine();
    }
}
