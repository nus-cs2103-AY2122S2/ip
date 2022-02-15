package duke.exception;

/**
 * ErrorString is an enum that contains error strings for Duke.
 *
 * @author yl-ang
 */
public enum ErrorString {

    ERROR_INDEX_OUT_OF_BOUND_TASK("OOPS!!! The task number you specify is invalid.\n"),
    ERROR_EMPTY_TODO_DESC("OOPS!!! The description of a todo cannot be empty.\n"),
    ERROR_EMPTY_DEADLINE_DESC("OOPS!!! The description of a deadline cannot be empty.\n"),
    ERROR_EMPTY_DEADLINE_DATE("OOPS!!! The date of a deadline cannot be empty.\n"),
    ERROR_EMPTY_EVENT_DESC("OOPS!!! The description of an event cannot be empty.\n"),
    ERROR_EMPTY_EVENT_DATE("OOPS!!! The date of an event cannot be empty.\n"),
    ERROR_INVALID_COMMAND("OOPS!!! I'm sorry, but I don't know what that means.\n"),
    ERROR_EMPTY_MARK_INT("OOPS!!! Please specify a task number for me to mark.\n"),
    ERROR_EMPTY_UNMARKED_INT("OOPS!!! Please specify a duke.task number for me to unmark.\n"),
    ERROR_NOT_INT_MARK("OOPS!!! The task number you specify for me to mark is invalid.\n"),
    ERROR_NOT_INT_UNMARKED("OOPS!!! The task number you specify for me to unmark is invalid.\n"),
    ERROR_EMPTY_DELETE("OOPS!!! Please specify a task number for me to delete.\n"),
    ERROR_TASK_MARKED_ALREADY("OOPS!!! Task is already marked.\n"),
    ERROR_TASK_UNMARKED_ALREADY("OOPS!!! Task is already unmarked.\n"),
    ERROR_FILE_IO_ERROR("OOPS!!! FILE IO EXCEPTIONS\n"),
    ERROR_INVALID_DEADLINE_DATE_FORMAT("OOPS!!! The date format provided is not in yyyy-mm-dd.\n"),
    ERROR_LOADING_ERROR("OOPS!!! There is error in loading the tasks from duke.txt\n"),
    ERROR_NOT_NUMBER_STRING("OOPS!!! Please provide number and not letters.\n"),
    ERROR_EMPTY_FIND_KEYWORD("OOPS!!! Please provide a search term for find.\n"),
    ERROR_EMPTY_UPDATE("OOPS!!! Please provide information to update.\n"),
    ERROR_NOT_INT_UPDATE("OOPS!!! Please provide a valid task number to update.\n"),
    ERROR_TODO_HAS_NO_DATE("OOPS!!! todo tasks does not have date, please do not supply date to update todo tasks.\n"),
    ERROR_STORAGE_INVALID_TASK("OOPS!!!! The tasks to be loaded from duke.txt has invalid format.\n"),
    ERROR_INVALID_EVENT_DATE_FORMAT("OOPS!!! The date format provided is not in yyyy-mm-dd.\n");

    private String errorString;

    ErrorString(String errorString) {
        this.errorString = errorString;
    }

    @Override
    public String toString() {
        return errorString;
    }
}
