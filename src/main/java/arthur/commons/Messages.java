package arthur.commons;

/**
 * Stores all the user visible messages.
 */
public class Messages {
    // Reply templates
    public static final String LIST_OUT_EMPTY_LIST_TEMPLATE = "You have no tasks in your list!";
    public static final String LIST_OUT_REPLY_TEMPLATE = "Here are the tasks in your list: \n";
    public static final String MARK_REPLY_TEMPLATE = "Good job! Task.Task Completed \n";
    public static final String UNMARK_REPLY_TEMPLATE = "Alright, I will unmark this \n";
    public static final String NEW_TODO_REPLY_TEMPLATE = "Added a new Task. Todo task: \n";
    public static final String NEW_DEADLINE_REPLY_TEMPLATE = "Added a new Task. Deadline task: \n";
    public static final String NEW_EVENT_REPLY_TEMPLATE = "Added a new Task. Event task: \n";
    public static final String DELETE_REPLY_TEMPLATE = "Successfully removed this task: \n";
    public static final String FIND_REPLY_TEMPLATE = "Here are the matching tasks in your list: \n";
    public static final String BYE_REPLY_TEMPLATE = "Bye! \n" + "Have a great day!";

    // Missing information messages
    public static final String DEADLINE_MISSING_DATE_MESSAGE = "Please add the deadline date";
    public static final String EVENT_MISSING_DATE_TIME_MESSAGE = "Please add the event Date and/or Time";

    // Error messages
    public static final String INVALID_TASK_NUMBER_MESSAGE = "Invalid task number. Please try again";
    public static final String INCORRECT_COMMAND_MESSAGE = "Please enter the correct command";
    public static final String INVALID_COMMAND_ERROR_MESSAGE = "Invalid command is executed";
    public static final String DATE_TIME_ERROR_MESSAGE = "Please enter the date/time in format: "
            + "yyyy-mm-dd HH:mm \n" + "You can also enter time or date only";
    public static final String VALID_NUM_ERROR = "Please enter a valid number!";
    public static final String IO_ISSUE_MESSAGE = "Sorry, there seems to be an Issue. \n"
            + "Please restart and try again";
    public static final String FILE_NOT_FOUND_ERROR_MESSAGE = "The file can't be found. \n"
            + "Please restart and try again.";
    public static final String IO_FILE_ISSUE_MESSAGE = "Sorry, there seems to be an Issue "
            + "with adding the task to the file. \n" + "Please restart and try again";
}
