package duke.ui;
import java.util.Scanner;

//Deals with interactions with users
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

    /**
     * Returns a string read from the user's input
     * into Duke.
     * @return user input
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Prints out the inputted error message.
     * @param error error message
     */
    public void showError(String error) {
        System.out.println("ERROR: " + error);
    }
}
