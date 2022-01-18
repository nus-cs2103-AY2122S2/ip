public abstract class BobbyException extends IllegalArgumentException{
    String errorType;
    public BobbyException(String message) {
        super(message);
        errorType = message;
    }

    @Override
    public abstract String toString();
}
