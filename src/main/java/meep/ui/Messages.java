package meep.ui;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_LOGO = ""
            + "**       **  ******  ******    *****\n"
            + "* *     * *  *       *         *    *\n"
            + "*  *   *  *  ******  ******    *****\n"
            + "*   * *   *  *       *         *\n"
            + "*    *    *  ******  ******    *\n";

    public static final String MESSAGE_HI = "Hello! I'm Meep\n" + "What can I do for you?";
    public static final String MESSAGE_FILE_MISSING = "Meep was unable to save all the data for the file! "
            + "Please try again.";

    public static final String MESSAGE_EMPTY_TASK = "Task description can not be empty!";
    public static final String MESSAGE_EMPTY_INPUT = "Command can not be empty!";
    public static final String MESSAGE_INVALID_NUMBER = "Invalid task number. Please enter a valid integer.";
    public static final String MESSAGE_OUTBOUND_NUMBER = "Task number out of range.";
    public static final String MESSAGE_INVALID_FORMAT = "This is not a valid command. See 'help'";
    public static final String MESSAGE_INVALID_COMMAND_LENGTH = "arr length should be 2";
    public static final String MESSAGE_EMPTY_TASK_LIST = "You have no task currently. Please try add one.";
    public static final String MESSAGE_INVALID_DATE_FORMAT = " can't be formatted!"
            + "Please format the date/time as dd/MM/yyyy HH:mm";
    public static final String MESSAGE_INVALID_DEADLINE_PREPOSITION = "Invalid Prepositions for deadline command. "
            + "eg. '/by'.";
    public static final String MESSAGE_INVALID_EVENT_PREPOSITION = "Invalid Prepositions for event command. "
            + "eg. '/on' or '/at' .";

}
