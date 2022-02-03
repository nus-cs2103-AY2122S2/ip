package src.main.java.duke;

import java.util.Scanner;

import src.main.java.duke.task.Task;

/**
 * Ui is a class that manages the bulk of the user interaction required by the
 * program.
 */
public class Ui {

    Scanner scanner = new Scanner(System.in);

    /**
     * welcome method prints out a welcome message when the user boots the program.
     */
    public void welcome() {
        System.out.println("Welcome to Duke, your friendly task manager!\n What do you want to do today?");
    }

    /**
     * goodBye method prints out a farewell message when the user leaves the
     * program.
     */
    public void goodBye() {
        System.out.println("Sayonara!! Hope to see you again soon hehe! :-)");
    }

    /**
     * addMessage method prints out a message to let the user know what task has
     * been added and how many tasks there are currently in the task list.
     * 
     * @param task the task that has just been added into the task list
     * @param size the size of the task list
     */
    public void addMessage(Task task, int size) {
        System.out.println("Got it. I've added this task:\n" + task.toString() +
                "\nNow you have " + size + " tasks in the list.");
    }

    /**
     * deleteMessage method prints out a message to let the user know what task has
     * been deleted and how many tasks there are remaining in the task list.
     * 
     * @param task the task that has just been deleted into the task list
     * @param size the size of the task list
     */
    public void deleteMessage(Task task, int size) {
        System.out.println("Noted. I've removed this task:\n" + task.toString() +
                "\nNow you have " + size + " tasks in the list.");
    }

    /**
     * markMessage method prints out a message to let the user know which task has
     * just been marked as done.
     * 
     * @param task the task that has just been marked as done
     */
    public void markMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task.toString());
    }

    /**
     * unmarkMessage method prints out a message to let the user know which task has
     * just been marked as not yet done.
     * 
     * @param task the task that has just been marked as not yet done
     */
    public void unmarkMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n" + task.toString());
    }

    /**
     * showLine method prints out a line to separate different interaction instances
     * for better visibility for users.
     */
    public void showLine() {
        System.out.println("---------------------------------------------------------------");
    }

    /**
     * readCommand method reads for the user's input and reflects that input to the
     * program to be processed.
     * 
     * @return a command of type String that is the user's input
     */
    public String readCommand() {
        String command = scanner.nextLine();
        return command;
    }

    /**
     * showError method prints out the error message to the user to let the user
     * know why the program cannot run as intended
     * 
     * @param errorMessage a message describing the fault
     */
    public void showError(String errorMessage) {
        System.out.println("Uh oh... We ran into an error: " + errorMessage);
    }
}
