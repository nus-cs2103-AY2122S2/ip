package exceptions;

public class DeadlineException extends TaskException {
    public DeadlineException() {
        super();
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "The description of a deadline cannot be empty.";
    }
}
