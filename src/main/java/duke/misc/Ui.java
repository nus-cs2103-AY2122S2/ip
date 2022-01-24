package duke.misc;

public class Ui {
    private static final String BOT_NAME = "KoroBot";
    private static final String WELCOME_MESSAGE = "    ____________________________________________________________\n"
            + "     Hi! I'm " + BOT_NAME + "~\n"
            + "    ____________________________________________________________";
    private static final String EXIT_MESSAGE = "    ____________________________________________________________\n"
            + "     Bye! Hope to see you again soon :D\n"
            + "    ____________________________________________________________";

    /**
     * Greets the user by printing the default welcome message.
     */
    public static void greet() {
        System.out.println(WELCOME_MESSAGE);
    }

    /**
     * Prompts the user to enter a command.
     */
    public static void welcome() {
        System.out.println("    ____________________________________________________________\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________\n");
    }

    /**
     * Exits the program when user inputs "bye".
     * Default exit message is printed as well.
     */
    public static void exit() {
        System.out.println(EXIT_MESSAGE);
        System.exit(0);
    }
}
