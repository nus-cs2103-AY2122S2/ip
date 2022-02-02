package duke.ui;

/**
 * A class that consists of messages that the Ui will show to the user as needed.
 */
public class Messages {

    public static final String LINE_SEPARATOR = System.lineSeparator();

    public static final String EXCLAMATION = "!";

    public static final String BOT_NAME = "Feline";

    public static final String WELCOME_MESSAGE = "Yoooo! My name is "
            + BOT_NAME + EXCLAMATION + LINE_SEPARATOR + "How can i help you bro?";

    public static final String FAREWELL_MESSAGE = "See you next time" + EXCLAMATION;

    public static final String UNKNOWN_COMMAND = "Unknown command! the follow are the commands: ";

    public static final String UNKNOWN_TODO = "todo.. todo what?";

    public static final String UNKNOWN_DEADLINE = "Sorry but.. deadline of what??";

    public static final String UNKNOWN_DATETIME = "Invalid date-time format! Format: <dd/MM/yyyy HHMM>."
            + System.lineSeparator() + "An example: 12/12/2222 0800";

    public static final String UNKNOWN_EVENT = "What event? No event stated.";

    public static final String UNKNOWN_LOCATION = "At where? Please specify again";

    public static final String DELETE_ERROR = "Error saving.";

    public static final String UNKNOWN_MARK = "Please specify what task number to mark clearly.";

    public static final String MARK_SUCCESS = "Nice! I've marked this task as done:";

    public static final String UNKNOWN_UNMARK = "Please specify what task number to unmark clearly.";

    public static final String UNMARK_SUCCESS = "OK, I've marked this task as not done yet:";

    public static final String UNKNOWN_DELETE = "Please specify what task number to delete clearly.";

    public static final String DELETE_SUCCESS = "Noted. I've removed this task:";

    public static final String SAVE_ERROR = "Error saving file";

    public static final String LIST_MSG = "Here are the tasks in your list:";

    /**
     * Returns the message indicating the task number does not exist.
     * @param taskNumber Task number that does not exist.
     *
     * @return The message indicating that that task number does not exist.
     */
    public static final String OUT_OF_BOUNDS_MSG (int taskNumber) {
        return String.format("The task %d does not exist!", taskNumber);
    }

}
