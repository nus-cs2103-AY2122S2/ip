package duke;

/**
 * Deals with interactions with the user.
 *
 * @author sibinhho99-nus
 */
public class Ui {
    /**
     * Prints a String and then terminate the line.
     * @param message the message to print.
     */
    public void println(String message) {
        System.out.println(message);
    }

    /**
     * Prints the formatted version of given message to console.
     * @param message the message to print.
     */
    public void printMessage(String message) {
        System.out.printf("____________________________________________________________\n"
                        + "%s\n"
                        + "____________________________________________________________%n",
                message
        );
    }
}
