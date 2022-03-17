package duke.exceptions;

/**
 * Exception class when command detail is wrongly formatted
 */
public class IncorrectFormatException extends DukeException{
    public IncorrectFormatException() {}

    /**
     * returns error message from exception
     *
     * @return String containing details of error
     */
    @Override
    public String getMessage() {
        return "OOPS!!! The input format is wrong or time is missing";
    }
}
