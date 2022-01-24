import java.util.Scanner;

/**
 * Ui represents the user interface object that deals with user interaction
 *
 * @author Jan
 * @version 1.0
 */
public class Ui {
    /** The scanner used to read from system input */
    private Scanner in;

    /**
     * Constructor for the Ui object
     */
    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Returns the next line from the user input
     *
     * @return  the next user input
     */
    public String nextLine() {
        return in.nextLine();
    }

    /**
     * Closes the scanner used to read inputs
     */
    public void closeScanner() {
        in.close();
    }

}
