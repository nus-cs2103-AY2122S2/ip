package duke.ui;

import duke.exceptions.DukeException;

/**
 * UserInterface is an ui class that represents the user interface
 * in which the user interacts with.
 */
public class UserInterface {
    private static final String INTRO = "Hello! I'm YQ, your personal assistant chatbot\n" + "What can I do for you?\n";

    /**
     * Returns a String containing the introduction message.
     *
     * @return A String containing the introduction message.
     */
    public static String introMessage() {
        return INTRO;
    }

    /**
     * Prints the duke error message.
     *
     * @param error The error message description.
     * @return AString containing the error message.
     */
    public static String errorMessage(DukeException error) {
        return error.getMessage();
    }
}
