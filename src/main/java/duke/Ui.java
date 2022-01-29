package duke;

/**
 * The UI class, containing various methods relating to the UI.
 *
 * @author Jet Tan
 */
public class Ui {
    private static final String BORDER = "________________________________\n";

    /**
     * Returns the border for wrapping bot messages.
     *
     * @return border for wrapping bot messages
     */
    public static String getBorder() {
        return BORDER;
    }

    /**
     * Greets the user.
     */
    public static void greet() {
        String LOGO = "   ___      _  ______       _   \n" +
                "  |_  |    | | | ___ \\     | |  \n" +
                "    | | ___| |_| |_/ / ___ | |_ \n" +
                "    | |/ _ \\ __| ___ \\/ _ \\| __|\n" +
                "/\\__/ /  __/ |_| |_/ / (_) | |_ \n" +
                "\\____/ \\___|\\__\\____/ \\___/ \\__|\n";
        System.out.println(getBorder() + LOGO + getBorder());
        System.out.println("How may I help you today? Please enter your command:");
    }

    /**
     * Exits the program.
     */
    public static void exit() {
        System.out.println(BORDER + "Bye!\n" + BORDER);
        System.exit(0);
    }

    /**
     * Returns the string to be printed on a successful add operation.
     *
     * @param t task that was added
     * @return string to be printed on a successful add operation
     */
    public static String successMessage(Task t) {
        return getBorder() + "Got it. I've added this task:\n" + t + "\n" +
                "Now you have " + TaskList.getTasks().size() + " task(s) in the list.\n" + getBorder();
    }
}