package duke.ui;

/**
 * Class which handles all printing and formatting for messages
 */
public class Ui {
    public static final String LINE = "    ____________________________________________________________\n";
    public static final String INDENT = "     ";

    public Ui() {
    }

    /**
     * Prints out a line for formatting
     */
    public void printLine() {
        System.out.print(LINE);
    }

    /**
     * prints welcome message when duke.Duke is run
     */
    public void welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLine();
        printFormatted(new String[]{"Hello! I'm duke.Duke", "What can I do for you?"});
        printLine();
    }

    /**
     * Takes in a error message and prints it out in the formatted format
     *
     * @param msg Error message
     */
    public void showError(String msg) {
        printFormatted(new String[]{msg});
    }

    /**
     * Prints out the input msg array in the correct format
     *
     * @param msg Output or message to be printed
     */
    public void printFormatted(String[] msg) {
        for (int i = 0; i < msg.length; i++) {
            if (msg[i] != null) {
                System.out.println(INDENT + msg[i]);
            }
        }
    }
}
