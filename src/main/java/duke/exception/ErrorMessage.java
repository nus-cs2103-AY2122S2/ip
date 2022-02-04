package duke.exception;

/**
 * Stores error messages to be shown to the user.
 */
public class ErrorMessage {
    public static final String MESSAGE_LOADING_ERROR = "Meow! The app cannot be loaded!";
    public static final String MESSAGE_INVALID_INDEX = "Meow! Enter a valid task index!";
    public static final String MESSAGE_INVALID_DATE_FORMAT = "Meow! A date should be in yyyy-mm-dd format!";
    public static final String MESSAGE_INVALID_DATE_VALUE = "Meow! Valid values for month and day are "
            + "1 - 12 and 1 - 28/31 respectively!";
    public static final String MESSAGE_INVALID_INPUT = "Meow! Enter a valid command!";
    public static final String MESSAGE_UNKNOWN_INDEX = "Meow! Enter a task index!";
    public static final String MESSAGE_UNKNOWN_DESC = "Meow! A task needs a description!";
    public static final String MESSAGE_UNKNOWN_DATE = "Meow! A date (yyyy-mm-dd) is required!";
    public static final String MESSAGE_UNKNOWN_KEYWORD = "Meow! Enter a keyword to find!";
    public static final String MESSAGE_FILE_SAVE_ERROR = "Error writing to file: ";
    public static final String MESSAGE_FILE_LOAD_ERROR = "Error loading from file: ";
}
