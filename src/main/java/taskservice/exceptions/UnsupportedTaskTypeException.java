package taskservice.exceptions;

public class UnsupportedTaskTypeException extends TaskServiceException{
    private static final String MESSAGE = "Task type is not supported";

    public UnsupportedTaskTypeException() {
        super(UnsupportedTaskTypeException.MESSAGE);
    }
}
