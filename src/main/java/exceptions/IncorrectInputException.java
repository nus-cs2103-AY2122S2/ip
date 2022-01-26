package exceptions;

public class IncorrectInputException extends TaskException {
    public IncorrectInputException() {
        super();
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " I'm sorry, but I don't know what that means :-(";
    }
}
