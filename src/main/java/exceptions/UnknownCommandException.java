package exceptions;

/**
 * Exception class when command is unkown
 */
public class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
    }

    /**
     * Error messsge from exception
     * @return String containing details of error
     */
    @Override
    public String getMessage() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
