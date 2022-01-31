package duke.helper;

import java.util.Scanner;

/**
 * Represents an Ui object which deals with interaction with user.
 */
public class Ui {
    Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns the next line of input and output as a String.
     * @return
     */
    public String nextLine() {
        return scanner.nextLine();
    }

    /**
     * Prints out the greeting message to welcome the user.
     */
    public void greet() {
        System.out.println("Nice to see you again, What can I do for you?");
    }

    public void print(String message) {
        System.out.println(message);
    }

    /**
     * Says goodbye to the user.
     */
    public void sayBye() {
        System.out.println("bye !!!");
    }
}
