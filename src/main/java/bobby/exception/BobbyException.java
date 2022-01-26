package bobby.exception;

public abstract class BobbyException extends IllegalArgumentException{
    private String errorType;

    public BobbyException(String message) {
        super(message);
        errorType = message;
    }

    @Override
    public abstract String toString();
}
