package Cleese;

public class InvalidInputException extends Exception{
    InvalidInputException(String errorMessage) {
        super(errorMessage);
    }
}
