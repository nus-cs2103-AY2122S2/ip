package duke;

/**
 * Deals with interactions with the user
 */
public class Ui {
    /**
     * Prints a String and then terminate the line.
     *
     */
    public void println(String message) {
        System.out.println(message);
    }

    /**
     *
     * Prints the formatted version of given message to console
     *
     */
    public void printMessage(String message) {
        System.out.println(
                String.format("""
                        ____________________________________________________________
                        %s
                        ____________________________________________________________""",
                        message
                )
        );
    }
}