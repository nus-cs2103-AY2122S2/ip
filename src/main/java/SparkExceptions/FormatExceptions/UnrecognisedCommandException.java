package SparkExceptions.FormatExceptions;

public class UnrecognisedCommandException extends FormatException {
    public UnrecognisedCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means.");
    }
}
