package duke;

/** Represents a TextUI Object that contains methods
 * that alters the text display of the GUI
 */
public class TextUi {
    private static final String DIVIDER = "===================================================";
    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Instantiates a ui object
     */
    public TextUi() {}

    /**
     * Method that greets the user when the file first runs
     */
    public String greeting() {
        return "Konnichiwassup from\n" + LOGO + showDivider() + "\nWhat do you need help with?\n";
    }

    /**
     * Method that shows a divider to the user
     */
    public String showDivider() {
        return DIVIDER;
    }

    /**
     * Method that says bye to the user
     */
    public String sayBye() {
        return "Sayonara! Hope to see you again soon!";
    }

    /**
     * Method that shows loading error if there is something wrong the program
     */
    public String showLoadingError() {
        return "Something went wrong with the program!";
    }
}
