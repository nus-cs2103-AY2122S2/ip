package ultoi.util;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Represents a user interface of the Ultoi bot.
 *
 * @author snoidetx
 * @version 0.0.0
 */
public class UltoiUi {
    public static final String INDENT = "    ";
    private static final String LINE_BREAK = "======U======L======T======O======I======";
    private static final String WELCOME_MESSAGE = "Hello! I am Ultoi [ uhl-twah ].\n"
            + "What can I do for you? <O_O>";
    private static final String LOADING_MESSAGE = "Congrats! Ultoi loads your past tasks!";
    private static final String LOADING_ERROR = "Ultoi fails to load your past tasks. <OoO>";


    private final Scanner in;
    private final PrintStream out;


    /**
     * Creates a default user interface.
     */
    public UltoiUi() {
        this(System.in, System.out);
    }

    /**
     * Creates a user interface from given input stream and print stream.
     *
     * @param in Given input stream.
     * @param out Given print stream.
     */
    public UltoiUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Reads a line of input from users.
     *
     * @return String representation of the user input.
     */
    public String readInput() {
        return in.nextLine();
    }

    /**
     * Displays a piece of message.
     *
     * @param msg Message shown.
     * @return Response message.
     */
    public String showMsg(String msg) {
        String fullMsg = msg;
        return fullMsg;
    }

    /**
     * Displays a piece of welcome message.
     *
     * @return Welcome message.
     */
    public String showWelcomeMsg() {
        return showMsg(WELCOME_MESSAGE);
    }

    /**
     * Displays a piece of loading success message.
     *
     * @return Loading success message.
     */
    public String showLoadingSuccess() {
        return showMsg(LOADING_MESSAGE);
    }

    /**
     * Displays a piece of loading error message.
     *
     * @return Loading error message.
     */
    public String showLoadingError() {
        return showMsg(LOADING_ERROR);
    }

    /**
     * Displays a piece of error message.
     *
     * @return Error message.
     */
    public String showError(UltoiException e) {
        return showMsg(e.toString() + " " + UltoiException.EXCEPTION_FACE);
    }

}
