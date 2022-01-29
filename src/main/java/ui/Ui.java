package ui;

import java.util.Scanner;

import exception.DukeException;

/**
 * Ui interface that the user interacts with.
 * Involves a scanner to read user inputs.
 */
public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Outputs the message according to a particular format.
     * @param message Text that must be formatted.
     */
    public void outputMessage(String message) {
        System.out.println("____________________________________________________________\n"
                + message + "\n____________________________________________________________");
    }

    /** Outputs the hello message when the bot starts. */
    public void sayHello() {
        outputMessage("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Returns input message from the user.
     * @return String that the user outputted.
     */
    public String getCommand() {
        return sc.nextLine();
    }

    /**
     * Outputs the error message of a DukeException.
     * @param e DukeException whose error message must be outputted.
     */
    public void showError(DukeException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Outputs the goodbye message when the bot session ends.
     * Also closes the Scanner associated with the Ui.
     */
    public void exit() {
        outputMessage("Bye. Hope to see you again soon!");
        sc.close();
    }
}
