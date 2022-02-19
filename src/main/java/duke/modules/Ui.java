package duke.modules;

import java.util.Scanner;

/**
 * The Ui class is responsible for dealing with all user interactions.
 */
public class Ui {

    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";

    private static final String LOGO  = "BEARD MAN\n";
    private static final String INITIALIZATION_MESSAGE = "Hello I'm\n" + LOGO + "what can I do for you?\n" +
                "use command 'help' to see list of commands";

    public Ui() {

    }

    /**
     * Initialise the Parser that will be used to parse all user inputs.
     *
     * @param parser The Parser object.
     */
    public static String getResponse(String userInput, Parser parser) {
            return parser.parse(userInput);
    }

    public static String getInitializationMessage() {
        return INITIALIZATION_MESSAGE;
    }

    public static void print(String message) {
        System.out.print(ANSI_BLUE + message + ANSI_RESET);
    }
}
