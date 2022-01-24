package alfred.ui;

import alfred.exceptions.AlfredException;
import java.util.Scanner;

/**
 * Encapsulates the logic and handling of interactions between the internal
 * workings of Alfred and the input/output with the user.
 */
public class AlfredUserInterface {
    // class constants
    private static final String GREETING = "Hello! My name is Alfred.\n"
            + "How can I be of service?";
    private static final String BYE = "Bye! Hope I was of service.";
    private static final int BREAK_CHAR_LENGTH = 100;
    private static final String BREAK_LINE = AlfredUserInterface.line();

    // instance variables
    Scanner io;

    /**
     * Creates an AlfredUserInterface object, and begins reading from
     * console inputs.
     */
    public AlfredUserInterface() {
        io = new Scanner(System.in);
    }

    private static String line() {
        String out = "";
        for (int i = 0; i < AlfredUserInterface.BREAK_CHAR_LENGTH; i++) {
            out += "-";
        }
        out += "\n";
        return out;
    }

    /**
     * Wraps a given text input between break lines (e.g. ===)
     * and prints them to console.
     *
     * @param text Text to be wrapped.
     */
    public void sandwichAndPrint(String text) {
        String out = "";
        out += AlfredUserInterface.BREAK_LINE;
        out += text + "\n";
        out += AlfredUserInterface.BREAK_LINE;
        System.out.println(out);
    }

    /**
     * Prints a predetermined greeting message to the console.
     */
    public void greeting() {
        this.sandwichAndPrint(AlfredUserInterface.GREETING);
    }

    /**
     * Prints a predetermined goodbye message to the console.
     */
    public void bye() {
        this.sandwichAndPrint(AlfredUserInterface.BYE);
    }

    /**
     * Handles AlfredExceptions by printing its internal message
     * to the console.
     *
     * @param e AlfredException thrown by other methods in the Alfred package.
     */
    public void handleAlfredException(AlfredException e) {
        this.sandwichAndPrint(e.getMessage());
    }

    /**
     * Returns console input.
     *
     * @return String input from the console.
     */
    public String readInput() {
        return this.io.nextLine();
    }

}
