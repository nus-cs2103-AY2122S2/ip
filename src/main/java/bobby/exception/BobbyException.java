package bobby.exception;

public abstract class BobbyException extends IllegalArgumentException {

    public BobbyException(String message) {
        super(message);
    }

    @Override
    public abstract String toString();
}
