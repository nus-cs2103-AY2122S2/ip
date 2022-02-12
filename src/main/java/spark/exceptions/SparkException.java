package spark.exceptions;

/**
 * All exceptions in the application are sub-classes of SparkException and
 * should minimally have a human-readable error message to inform the user
 * of what went wrong and what can be done about the exception.
 */
public class SparkException extends Exception {
    public SparkException(String errorMessage) {
        super(errorMessage);
    }
}
