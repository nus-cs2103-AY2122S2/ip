package duke.exceptions;

/**
 * Exception class when command detail is empty
 */
public class EmptyDescriptionException extends DukeException {
    private String type;

    public EmptyDescriptionException(String type) {
        this.type = type;
    }

    /**
     * Error messsge from exception
     * @return String containing details of error
     */
    @Override
    public String getMessage() {
        return "☹ OOPS!!! The description of a " + type + " cannot be empty.";
    }
}
