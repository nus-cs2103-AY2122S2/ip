import java.util.Scanner;

public class UI {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String SEPARATOR = "____________________________________________________________";
    private static final String EXIT_MSG = "Bye. Hope to see you again soon!";
    private static final String GREET_MSG = "Hello! I'm Jarvis\nWhat can I do for you?";

    private final Scanner sc;

    public UI() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Get the next command.
     *
     * @return the next command
     */
    public String nextCmd() {
        return sc.nextLine();
    }

    /**
     * Checks if there are still any commands.
     *
     * @return whether there is a next line
     */
    public boolean hasNextCmd() {
        return sc.hasNextLine();
    }

    /**
     * Formats and prints the given message.
     *
     * @param msg formatted message
     */
    public void printMsg(String msg) {
        System.out.println(SEPARATOR);
        System.out.println(msg);
        System.out.println(SEPARATOR);
        System.out.println();
    }

    /**
     * Prints the greeting message.
     */
    public void greet() {
        printMsg(GREET_MSG);
    }

    public String getGoodbyeMsg() {
        return EXIT_MSG;
    }
}
