package duke.exceptions;

public class InvalidParameterException extends Exception{
    public InvalidParameterException(String errorMessage) {
        super(errorMessage);
    }
}
