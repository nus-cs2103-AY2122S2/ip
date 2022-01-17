package SparkExceptions;

public class SparkException extends Exception {
    public SparkException(String errorMessage) {
        super("OOPS!!! " + errorMessage);
    }
}
