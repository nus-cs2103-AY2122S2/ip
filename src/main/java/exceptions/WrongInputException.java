package exceptions;

public class WrongInputException extends TaskException {
    public WrongInputException() {
        super();
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "The input is wrong.";
    }
}
