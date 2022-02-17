package apollo.messages;

/**
 * Class containing all messages for program.
 * Future add-on: Help messages.
 */
public class Messages {

    public static final String LINE_FEED = System.lineSeparator();

    public static final String OUT_OF_BOUNDS = "Index is not on the list.";
    public static final String INVALID_INDEX = "Index entered is invalid, please enter a valid index.";
    public static final String INSUFFICIENT_ARGUMENTS = "Insufficient Arguments.";
    public static final String INVALID_DATETIME_FORMAT = "Please add date and time in this format: ";
    public static final String SAVE_CREATE_ERROR = "Save file cannot be created.";
    public static final String SAVE_WRITE_ERROR = "Cannot write to save file.";
    public static final String LOAD_ERROR = "Save file cannot be loaded.";
    public static final String MISSING_GUI_IMAGE = "GUI image cannot be found.";

    public static final String EXIT_MESSAGE = "See you next time. \nI am always available when you need me.";
    public static final String NO_TASK_FOUND = "I could not find any related tasks.";
    public static final String INVALID_COMMAND = "Apologies, I do not understand that.\nFor list of commands: help";
    public static final String EMPTY_TASKLIST = "You currently have no tasks.";
}
