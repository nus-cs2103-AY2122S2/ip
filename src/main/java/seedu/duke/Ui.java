package seedu.duke;

import java.util.Scanner;

public class Ui {

    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showError(String err) {
        System.out.println("\tError found: " + err + "\n");
    }

    public void printCompleted(String resp) {
        System.out.println("\t" + resp + "\n");
    }

    /**
     * Gets the user input.
     * @return User input.
     */
    public String readCommand() {
        System.out.print("Command: ");
        return sc.nextLine();
    }
}
