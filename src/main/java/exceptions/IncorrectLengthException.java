package exceptions;

public class IncorrectLengthException extends TaskException {
    public IncorrectLengthException() {
        super();
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "You are missing an index input.";
    }
}
