package taskManagerExceptions;

public class InvalidParameterException extends Exception{
    public InvalidParameterException(String errorMessage) {
        super(errorMessage);
    }
}
