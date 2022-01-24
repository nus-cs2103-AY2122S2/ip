package duke;

/**
 * UI class that handles the printing of startup and miscellaneous lines
 */
public class Ui {
    final static String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    final static String WELCOME = "Howdy and welcome to\n" + LOGO + "\n" + "Feel free to tell duke any tasks you'd like!";
    final static String FORMAT_NOTE = "NOTE: For events and deadlines do remember to include a date in the format of YYYY-MM-DD.\n" +
            "Adding time for events and deadlines are optional, but the format is in the form HH:mm";
    final static String LINE = "-----------------------------------";

    /**
     * Empty Constructor
     */
    public Ui() {}

    /**
     * prints Welcome message and notes on how to format input
     */
    public void startUp() {
        System.out.println(WELCOME);
        System.out.println(FORMAT_NOTE);
        printSeparator();
    }

    /**
     * prints a separator line to be used between inputs and outputs
     */
    public static void printSeparator() {
        System.out.println(LINE);
    }
}
