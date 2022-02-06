package duke.exception;

public class DukeException extends Exception {
    public static final String ERROR_NO_COMMAND = "Charizard does not know this move. Try a different command.";
    public static final String ERROR_PARSE_INT = "Charizard's can only remember numbers. Try specifying a task number."
            + "(Use \"list\" command to see the tasks and their corresponding task number).";
    public static final String ERROR_INVALID_INDEX = "Task number does not exist. Charizard is confused..";
    public static final String ERROR_TODO_NO_NAME = "Please specify the name of new task to be burnt";
    public static final String ERROR_FIND_NO_NAME = "Please specify the name of task to find";
    public static final String ERROR_WRONG_FORMAT = "Charizard remembers this move but cannot recognize the format.";
    public static final String ERROR_IO_INPUT = "Error: File input cannot be read";
    public static final String ERROR_INTERNAL = "Oops. Some internal error has occurred";
    public static final String ERROR_INPUT_FILE_FORMAT_SPLIT = "Error: Input file is not formatted correctly using |";
    public static final String ERROR_INPUT_FILE_FORMAT_DONE = "Error: isDone field is not indicated by 0 or 1 in file";
    public static final String ERROR_INPUT_FILE_FORMAT_TASK = "Error: Task type is not T,D or E in file";
    public static final String ERROR_WRITE_FILE = "Unable to write to file";
    public static final String FORMAT_DEADLINE = "Try using:\"deadline <task_name> /by <deadline>\".";
    public static final String FORMAT_EVENT = "Try using:\"event <task_name> /by <event_time>\".";
    public static final String FORMAT_DATE = "Date and time must be in yyyy/MM/dd HHmm format.";
    public static final String FORMAT_PRIORITY = "Try using:\"priority <task_number> <priority>\".";
    public static final String FORMAT_PRIORITY_LEVEL = "Priority must be \"HIGH\", \"MEDIUM\" or \"LOW\"";

    public DukeException(String displayMessage) {
        super(displayMessage);
    }
}
