package SparkExceptions.FormatExceptions;

public class UnrecognisedCommandException extends FormatException {
    public UnrecognisedCommandException() {
        super("I'm sorry, but I don't know what that means.");
    }
}
