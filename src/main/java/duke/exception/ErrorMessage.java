package duke.exception;

/**
 * An enumeration that defines different error messages.
 */
public enum ErrorMessage {
    ERROR_ADD_DEADLINE_INCOMPLETE_COMMAND_DATETIME_EMPTY("INCOMPLETE COMMAND"
            + System.lineSeparator() + "\t"
            + "The date/time of a deadline cannot be empty!"
            + System.lineSeparator() + "\t"
            + "[Note: Enter /by before specifying the date/time]"),

    ERROR_ADD_DEADLINE_INCOMPLETE_COMMAND_DESC_AND_DATETIME_EMPTY("INCOMPLETE COMMAND"
            + System.lineSeparator() + "\t"
            + "The description and date/time of a deadline cannot be empty!"
            + System.lineSeparator() + "\t"
            + "[Note: Enter /by before specifying the date/time]"),

    ERROR_ADD_DEADLINE_INCOMPLETE_COMMAND_DESC_EMPTY("INCOMPLETE COMMAND"
            + System.lineSeparator() + "\t"
            + "The description of a deadline cannot be empty!"),

    ERROR_ADD_DEADLINE_INCOMPLETE_COMMAND_DESC_EMPTY_AND_WRONG_COMMAND_DATETIME("INCOMPLETE & WRONG COMMAND"
            + System.lineSeparator() + "\t"
            + "The description of a deadline cannot be empty!"
            + System.lineSeparator() + "\t"
            + "Enter /by before specifying the date/time!"),

    ERROR_ADD_DEADLINE_WRONG_COMMAND_DATETIME("WRONG COMMAND"
            + System.lineSeparator() + "\t"
            + "Enter /by before specifying the date/time!"),

    ERROR_ADD_EVENT_INCOMPLETE_COMMAND_DATETIME_EMPTY("INCOMPLETE COMMAND"
            + System.lineSeparator() + "\t"
            + "The date/time of an event cannot be empty!"
            + System.lineSeparator() + "\t"
            + "[Note: Enter /at before specifying the date/time]"),

    ERROR_ADD_EVENT_INCOMPLETE_COMMAND_DESC_AND_DATETIME_EMPTY("INCOMPLETE COMMAND"
            + System.lineSeparator() + "\t"
            + "The description and date/time of an event cannot be empty!"
            + System.lineSeparator() + "\t"
            + "[Note: Enter /at before specifying the date/time]"),

    ERROR_ADD_EVENT_INCOMPLETE_COMMAND_DESC_EMPTY("INCOMPLETE COMMAND"
            + System.lineSeparator() + "\t"
            + "The description of an event cannot be empty!"),
    ERROR_ADD_EVENT_INCOMPLETE_COMMAND_DESC_EMPTY_AND_WRONG_COMMAND_DATETIME("INCOMPLETE & WRONG COMMAND"
            + System.lineSeparator() + "\t"
            + "The description of an event cannot be empty!"
            + System.lineSeparator() + "\t"
            + "Enter /at before specifying the date/time!"),

    ERROR_ADD_EVENT_WRONG_COMMAND_DATETIME("WRONG COMMAND"
            + System.lineSeparator() + "\t"
            + "Enter /at before specifying the date/time!"),

    ERROR_ADD_TODO_DESC_EMPTY("The description of a todo cannot be empty!"),

    ERROR_DELETE_TASK_NUM_EMPTY("Please enter a valid task number to be deleted!"),

    ERROR_DELETE_TASK_NUM_INVALID("Please enter a task number to be deleted!"),

    ERROR_FIND_KEYWORD_EMPTY("Please enter the keyword to search for matching tasks!"),

    ERROR_INVALID_COMMAND("INVALID COMMAND. Please try again!"),

    ERROR_INVALID_TYPE_OF_TASK("INVALID TYPE OF TASK FOUND"),

    ERROR_MARK_TASK_NUM_EMPTY("Please enter a task number to be marked as done!"),

    ERROR_MARK_TASK_NUM_INVALID("Please enter a valid task number to be marked as done!"),

    ERROR_NO_MATCHING_TASKS_IN_LIST("There are no matching tasks in your list!"),

    ERROR_NO_TASKS_IN_LIST("There are no tasks in your list!"),

    ERROR_NO_TASKS_ON_DATE("There are no tasks on this date!"),

    ERROR_PRINT_INCOMPLETE_COMMAND("INCOMPLETE COMMAND"
            + System.lineSeparator() + "\t"
            + "Enter /on before specifying the date!"),

    ERROR_PRINT_INCOMPLETE_COMMAND_DATE_EMPTY("INCOMPLETE COMMAND"
            + System.lineSeparator() + "\t"
            + "The date is not specified!"
            + System.lineSeparator() + "\t"
            + "[Note: Enter /on before specifying the date]"),

    ERROR_PRINT_WRONG_COMMAND_DATE("WRONG COMMAND"
            + System.lineSeparator() + "\t"
            + "Enter /on before specifying the date!"),

    ERROR_TASK_NOT_FOUND("Task not found. Please try again!"),

    ERROR_UNABLE_INITIALISE_DIRECTORY("Unable to initialise directory"),

    ERROR_UNABLE_INITIALISE_FILE("Unable to initialise file"),

    ERROR_UNMARK_TASK_NUM_EMPTY("Please enter a task number to be marked as not done yet!"),

    ERROR_UNMARK_TASK_NUM_INVALID("Please enter a valid task number to be marked as not done yet!");

    private final String errorMessage;

    /**
     * Constructor to initialize an instance of ErrorMessage enumeration
     * with error message field.
     *
     * @param errorMessage Error message
     */
    ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Returns the string representation of the error message.
     *
     * @return The string representation of the error message
     */
    @Override
    public String toString() {
        return errorMessage;
    }
}
