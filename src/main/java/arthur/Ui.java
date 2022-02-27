package arthur;

/**
 * Handles interactions with the user.
 */
public class Ui {
    private static final String LOGO = "  / \\    _ |_ |_  | |  _  \n"
            + "/----\\ |  |_ | | |_| |  ";
    private static final String WELCOME_MESSAGE = "Greetings from \n" + LOGO
            + "\n" + "How may I assist you today?";

    /**
     * Constructor for Ui object.
     */
    public Ui() {
    }


    /**
     * Displays the logo and greetings.
     *
     * @return A string version of the logo and welcome message.
     */
    public String showWelcome() {
        return WELCOME_MESSAGE;
    }
}
