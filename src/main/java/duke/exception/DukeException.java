package duke.exception;

public class DukeException extends Exception {
    public static final String ERROR_NO_COMMAND = "Charizard does not know this move. Try a different command.";
    public static final String ERROR_PARSE_INT = "Charizard's can only remember numbers. Try specifying a task number."
            + "\n(Use \"list\" command to see the tasks and their corresponding task number).";
    public static final String ERROR_INVALID_INDEX = "duke.task.Task number does not exist. Charizard is confused..";
    public static final String ERROR_TODO_NO_NAME = "Please specify the name of new task to be burnt";
    public static final String ERROR_WRONG_FORMAT = "Charizard remembers this move but cannot recognize the format.";
    public static final String ERROR_IO_Input = "Unable to read from input.";
    public static final String FORMAT_DEADLINE = "Try using \"deadline <task_name> /by <deadline>\".";
    public static final String FORMAT_EVENT = "Try using \"event <task_name> /by <event_time>\".";
    public static final String FORMAT_DATE = "Date and time must be in yyyy/MM/dd HHmm format.";

    public DukeException(String displayMessage) {
        super(displayMessage);
    }
}
