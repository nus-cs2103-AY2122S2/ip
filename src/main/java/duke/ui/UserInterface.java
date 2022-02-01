package duke.ui;

import java.util.Scanner;

import duke.exceptions.DukeException;

/**
 * UserInterface is an ui class that represents the user interface
 * in which the user interacts with.
 */
public class UserInterface {
    private static final String LINE = "_______________________________________________";
    private static final String INTRO = "\nHello! I'm YQ, your assistant\n" + "What can I do for you?\n";
    private static final String BYE = "\nBye. Hope to see you again soon!\n";
    protected Scanner sc;

    /**
     * Creates a user interface object, constructor.
     */
    public UserInterface() {
        sc = new Scanner(System.in);
    }

    /**
     * Returns the input by the user.
     *
     * @return The String input from the user.
     */
    public String readInput() {
        return this.sc.nextLine();
    }

    /**
     * Prints the introduction message.
     */
    public void introMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String intro = LINE + INTRO + logo + LINE;
        System.out.println(intro);
    }

    /**
     * Prints the bye message.
     */
    public void byeMessage() {
        System.out.println(LINE + BYE + LINE);
    }

    /**
     * Prints the duke error message.
     * @param error The error message description.
     */
    public void errorMessage(DukeException error) {
        System.out.println(error.getMessage() + "\n" + LINE);
    }

    /**
     * Prints the line divider.
     */
    public void lineDivider() {
        System.out.println(LINE);
    }
}
