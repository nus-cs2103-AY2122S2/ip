package duke.operations;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private static final String WELCOME_MESSAGE_ONE = "Tell me... have you seen a RED imposter among us?";
    private static final String WELCOME_MESSAGE_TWO = "If you have seen this SUSSY imposter,"
            + " please let me know immediately... otherwise how may I be of assistance?";
    private static final String GOODBYE_MESSAGE = "Better watch out for the imposter AMONG US!";

    /**
     * Prints a welcome message.
     *
     * @return the welcome message for Duke.
     */
    public static String showWelcome() {
        return WELCOME_MESSAGE_ONE + "\n" + WELCOME_MESSAGE_TWO;
    }

    /**
     * Prints a goodbye message.
     *
     * @return the goodbye message for Duke.
     */
    public static String showGoodbye() {
        return GOODBYE_MESSAGE;
    }

    /**
     * Prints out the duke error.
     *
     * @param msg the string to be printed.
     * @return the Duke error.
     */
    public String showDukeError(String msg) {
        return msg;
    }
}
