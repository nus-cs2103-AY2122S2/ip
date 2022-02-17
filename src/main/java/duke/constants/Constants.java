package duke.constants;

/**
 * Holds all the immutable strings used for displaying to the user.
 */
public final class Constants {
    public static final String BOT_ART =
        "     /\\   | |   | |          \n"
        + "    /  \\  | |__ | |__  _   _ \n"
        + "   / /\\ \\ | '_ \\| '_ \\| | | |\n"
        + "  / ____ \\| |_) | |_) | |_| |\n"
        + " /_/    \\_\\_.__/|_.__/ \\__, |\n"
        + "                        __/ |\n"
        + "                       |___/ ";
    public static final String BOT_NAME = "Abby";
    public static final String GREET =
            Constants.BOT_ART + "\nHello! I'm "
            + Constants.BOT_NAME + "\nWhat can I do for you?\n";
    public static final String BYE = "Bye. Hope to see you again soon!";
    public static final String UNKNOWN_MSG =
            "OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String INVALID_INDEX_MSG =
            "OOPS!!! I can't find that in your task list :-(";
    public static final String BROKEN_MSG =
            "YOU BROKE ME :-(";
    public static final String INVALID_MARK_MSG =
            "OOPS!!! I need a number to update that task :-(";
    public static final String INVALID_DELETE_MSG =
            "OOPS!!! I need a number to delete that task :-(";
    public static final String INVALID_DATE_MSG =
            "OOPS!!! I cannot recognise that date format. :-("
            + "\nAlso, in this date format yyyy-mm-dd please!";
    public static final String NO_TASK_SEARCH_MSG =
            "I can't find any tasks of that date. :-(";
    public static final String STORAGE_ADD_MSG =
        "OOPS!!! Facing some issues in saving your task to disk. :-(";
    public static final String STORAGE_READ_MSG =
            "OOPS!!! Facing some issues in reading your tasks from disk. :-(";
    public static final String STORAGE_UPDATE_MSG =
            "OOPS!!! Facing some issues in updating your task to disk. :-(";
    public static final String STORAGE_DELETE_MSG =
            "OOPS!!! Facing some issues in deleting your task to disk. :-(";

    public static final String FILE_PATH = "data/";
    public static final String FILE_NAME = "duke.txt";

    private Constants() {

    }
}
