package duke.ui;

import java.util.Scanner;

import duke.exceptions.DukeException;

/**
 * UserInterface is an ui class that represents the user interface
 * in which the user interacts with.
 */
public class UserInterface {
    private static final String INTRO = "Hello! I'm YQ, your personal assistant\n" + "What can I do for you?\n";
    private static final String BYE = "\nBye. Hope to see you again soon!\n";

    /**
     * Prints the introduction message.
     */
    public static String introMessage() {
        return INTRO;
    }

    /**
     * Prints the bye message.
     */
    public static String byeMessage() {
        return BYE;
    }

    /**
     * Prints the duke error message.
     * @param error The error message description.
     */
    public static String errorMessage(DukeException error) {
        return error.getMessage();
    }
}
