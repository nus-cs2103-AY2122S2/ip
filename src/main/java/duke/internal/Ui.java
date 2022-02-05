package duke.internal;

/**
 * Represents the manager that deals with user interaction.
 * Makes use of an external io program.
 */
public class Ui {
    /**
     * Prints a greeting message to the user.
     */
    public String getGreet() {
        return "Hello! I'm Duke\n"
                + "What can I do for you?";
    }

    /**
     * Formats a String according to the programs display format.
     * @param input the String to be formatted.
     * @return the formatted String.
     */
    public String getFormattedMessage(String input) {
        return input;
    }
}
