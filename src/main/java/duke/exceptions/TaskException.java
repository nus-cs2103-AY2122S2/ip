package duke.exceptions;

/**
 * Represents an exception that is thrown when there are errors when processing tasks
 * that the users have keyed into Duke
 */
public class TaskException extends DukeException {
    private final String errorCode;

    /**
     * Initializes a Task exception
     * @param errorCode Takes in an errorCode that will determine the message
     *                  that gets printed out.
     */
    public TaskException(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Method that returns the error message to show to the user
     * @return Error Message given a particular error code
     */
    @Override
    public String getMessage() {
        String message = "";
        switch (errorCode) {
        case "INVALID_TASKID":
            message = "Task ID has to be an integer!";
            break;
        case "UNFOUND_TASK":
            message = "Task cannot be found within the task list! Please fix your machigai!";
            break;
        case "UNDONE":
            message = "This task has yet to be done!";
            break;
        case "DONE":
            message = "This task has already been marked done!";
            break;
        default:
            assert false : errorCode;
        }
        return message;
    }
}
