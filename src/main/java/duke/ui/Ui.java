package duke.ui;
import java.util.Scanner;

/**
 * Deals with interactions with users.
 */
public class Ui {

    /**
     * Prints out the statement "LOADING ERROR".
     */
    public void showLoadingError() {
        System.out.println("LOADING ERROR");
    }

    /**
     * Prints out a welcome statement upon
     * starting up Duke.
     */
    public void showWelcome() {
        System.out.println("Hello!! I am Duke, your humble personal chatbot.\n"
                + "What can I do for you?");
    }

}
